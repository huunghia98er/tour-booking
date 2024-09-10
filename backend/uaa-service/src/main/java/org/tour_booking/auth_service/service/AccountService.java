package org.tour_booking.auth_service.service;

import exception.AppException;
import exception.ERROR_CODE;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tour_booking.auth_service.constant.PERMISSION;
import org.tour_booking.auth_service.mapper.AccountMapper;
import org.tour_booking.auth_service.model.entity.AccountEntity;
import org.tour_booking.auth_service.model.entity.MerchantEntity;
import org.tour_booking.auth_service.model.entity.PermissionEntity;
import org.tour_booking.auth_service.model.entity.VerificationTokenEntity;
import org.tour_booking.auth_service.model.request.AccountCreationRequest;
import org.tour_booking.auth_service.model.request.AccountUpdateRequest;
import org.tour_booking.auth_service.model.request.ConfirmAccountRequest;
import org.tour_booking.auth_service.model.request.CreateMerchantActorRequest;
import org.tour_booking.auth_service.model.response.AccountResponse;
import org.tour_booking.auth_service.model.response.ConfirmAccountResponse;
import org.tour_booking.auth_service.repository.AccountRepository;
import org.tour_booking.auth_service.repository.MerchantRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Transactional
public class AccountService {

    AccountRepository accountRepo;
    MerchantRepository merchantRepo;

    PermissionService permissionService;
    TokenService tokenService;

    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;

    public AccountResponse createAccount(AccountCreationRequest request, Boolean isMerchant) {
        if (accountRepo.existsByUsername(request.getUsername()))
            throw new AppException(ERROR_CODE.USER_EXISTED);
        if (accountRepo.existsByEmail(request.getEmail()))
            throw new AppException(ERROR_CODE.EMAIL_EXISTED);

        AccountEntity account = accountMapper.toEntity(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<String> permissions = new HashSet<>();
        if (isMerchant) {
            permissions.addAll(List.of(PERMISSION.MERCHANT_CREATE_TOUR.val,
                    PERMISSION.MERCHANT_EDIT_TOUR.val,
                    PERMISSION.MERCHANT_DELETE_TOUR.val));
            MerchantEntity merchant = merchantRepo.findById(request.getMerchantId())
                    .orElseThrow(() -> new AppException(ERROR_CODE.MERCHANT_NOT_EXISTED));
            account.setMerchant(merchant);
            account.setIsActive(true);
        } else {
            permissions.add(PERMISSION.CUSTOMER.val);
        }

        Set<PermissionEntity> existingPermissions = permissionService.findAllByNames(permissions);
        Set<String> existingPermissionNames = existingPermissions.stream()
                .map(PermissionEntity::getName)
                .collect(Collectors.toSet());

        Set<String> missingPermissions = permissions.stream()
                .filter(permission -> !existingPermissionNames.contains(permission))
                .collect(Collectors.toSet());

        if (!missingPermissions.isEmpty()) {
            throw new AppException(ERROR_CODE.PERMISSION_NOT_FOUND, String.join(", ", missingPermissions));
        }

        account.setPermissions(existingPermissions);
        account = accountRepo.save(account);
        if (!isMerchant) {
            VerificationTokenEntity verificationToken = tokenService.createVerificationToken(account);
            // todo: send mail to confirm active, url với token param
        }
        return accountMapper.toDto(account);
    }

    public List<AccountResponse> createMerchantActorAccount(CreateMerchantActorRequest request) {
        MerchantEntity merchant = merchantRepo.findById(request.getMerchantId()).orElseThrow(()
                -> new AppException(ERROR_CODE.MERCHANT_NOT_EXISTED));

        List<CreateMerchantActorRequest.Account> requestAccounts = request.getAccounts();
        Set<String> usernames = requestAccounts.stream()
                .map(CreateMerchantActorRequest.Account::getUsername)
                .collect(Collectors.toSet());
        Set<String> emails = requestAccounts.stream()
                .map(CreateMerchantActorRequest.Account::getEmail)
                .collect(Collectors.toSet());

        List<AccountEntity> existedAccounts = accountRepo.findByUsernamesOrEmails(usernames, emails);

        if (!existedAccounts.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (AccountEntity account : existedAccounts) {
                if (usernames.contains(account.getUsername())) {
                    errorMessage.append(account.getUsername()).append(", ");
                }
                if (emails.contains(account.getEmail())) {
                    errorMessage.append(account.getEmail()).append(", ");
                }
            }
            throw new AppException(ERROR_CODE.USER_EXISTED, StringUtils.removeEnd(errorMessage.toString(), ", "));
        }

        Set<String> permissionNamesRequest = requestAccounts.stream()
                .flatMap(account -> account.getPermissions().stream())
                .collect(Collectors.toSet());

        Set<PermissionEntity> existingPermissions = permissionService.findAllByNames(permissionNamesRequest);
        Map<String, PermissionEntity> permissionMap = existingPermissions.stream()
                .collect(Collectors.toMap(PermissionEntity::getName, permission -> permission));

        Set<String> missingPermissionNames = permissionNamesRequest.stream()
                .filter(permissionName -> !permissionMap.containsKey(permissionName))
                .collect(Collectors.toSet());

        if (!missingPermissionNames.isEmpty()) {
            throw new AppException(ERROR_CODE.PERMISSION_NOT_FOUND, String.join(", ", missingPermissionNames));
        }

        Set<AccountEntity> accounts = requestAccounts.stream()
                .map(item -> {
                    Set<PermissionEntity> permissions = item.getPermissions().stream()
                            .map(permissionMap::get)
                            .collect(Collectors.toSet());

                    return AccountEntity.builder()
                            .username(item.getUsername())
                            .password(passwordEncoder.encode(item.getPassword()))
                            .email(item.getEmail())
                            .phone(item.getPhone())
                            .permissions(permissions)
                            .isActive(true)
                            .merchant(merchant)
                            .build();
                })
                .collect(Collectors.toSet());

        return accountRepo.saveAll(accounts).stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    public ConfirmAccountResponse confirmAccountToActive(ConfirmAccountRequest request) {
        Optional<VerificationTokenEntity> verificationToken = tokenService.getToken(request.getToken());
        if (verificationToken.isEmpty()) {
            throw new AppException(ERROR_CODE.INVALID_TOKEN);
        }

        AccountEntity account = verificationToken.get().getAccount();
        account.setIsActive(true);
        accountRepo.save(account);

        return ConfirmAccountResponse.builder()
                .isConfirmed(true)
                .build();
    }

    public AccountResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        AccountEntity account = accountRepo.findByUsername(name).orElseThrow(()
                -> new AppException(ERROR_CODE.USER_NOT_EXISTED));

        return accountMapper.toDto(account);
    }

    public AccountResponse updateAccount(HttpServletRequest servletRequest, AccountUpdateRequest request) {
        String token = tokenService.extractToken(servletRequest);
        Long accountId = tokenService.extractAccountIdFromToken(token);
        AccountEntity account = accountRepo.findById(accountId).orElseThrow(()
                -> new AppException(ERROR_CODE.USER_NOT_EXISTED));

        accountMapper.updateAccount(account, request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        var permissions = permissionService.findAllByNames(request.getPermissions());
        account.setPermissions(new HashSet<>(permissions));

        return accountMapper.toDto(accountRepo.save(account));
    }

    // todo: valid
    public void deleteAccount(Long accountId) {
        AccountEntity account = accountRepo.findById(accountId).orElseThrow(()
                -> new AppException(ERROR_CODE.USER_NOT_EXISTED));
        account.setIsActive(false);
        accountRepo.save(account);
    }

    /**
     * todo: phân trang, thêm filter
     * todo: trả về đúng accounts mà user login hiện tại có quyền xem
     **/
    public List<AccountResponse> getAccounts(HttpServletRequest servletRequest) {
        return accountRepo.findAll().stream()
                .map(accountMapper::toDto)
                .toList();
    }

}
