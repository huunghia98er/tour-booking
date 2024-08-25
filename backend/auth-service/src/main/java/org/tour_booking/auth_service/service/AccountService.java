package org.tour_booking.auth_service.service;

import exception.AppException;
import exception.ERROR_CODE;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tour_booking.auth_service.mapper.AccountMapper;
import org.tour_booking.auth_service.model.entity.Account;
import org.tour_booking.auth_service.model.request.AccountCreationRequest;
import org.tour_booking.auth_service.model.request.AccountUpdateRequest;
import org.tour_booking.auth_service.model.response.AccountResponse;
import org.tour_booking.auth_service.repository.AccountRepository;
import org.tour_booking.auth_service.repository.PermissionRepository;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService {

    AccountRepository accountRepo;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;
    PermissionRepository permissionRepo;

    public AccountResponse createAccount(AccountCreationRequest request) {
        if (accountRepo.existsByUsername(request.getUsername()))
            throw new AppException(ERROR_CODE.USER_EXISTED);

        Account account = accountMapper.toEntity(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account = accountRepo.save(account);

        return accountMapper.toDto(account);
    }

    public AccountResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Account account = accountRepo.findByUsername(name).orElseThrow(()
                -> new AppException(ERROR_CODE.USER_NOT_EXISTED));

        return accountMapper.toDto(account);
    }

    public AccountResponse updateAccount(String userId, AccountUpdateRequest request) {
        Account account = accountRepo.findById(userId).orElseThrow(()
                -> new AppException(ERROR_CODE.USER_NOT_EXISTED));

        accountMapper.updateAccount(account, request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        var permissions = permissionRepo.findAllById(request.getPermissions());
        account.setPermissions(new HashSet<>(permissions));

        return accountMapper.toDto(accountRepo.save(account));
    }

    public void deleteAccount(String accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(()
                -> new AppException(ERROR_CODE.USER_NOT_EXISTED));
        account.setActive(false);
        accountRepo.save(account);
    }

    public List<AccountResponse> getAccounts() {
        return accountRepo.findAll().stream().map(accountMapper::toDto).toList();
    }

    public AccountResponse getAccount(String id) {
        Account account = accountRepo.findById(id).orElseThrow(()
                -> new AppException(ERROR_CODE.USER_NOT_EXISTED));

        return accountMapper.toDto(account);
    }

}
