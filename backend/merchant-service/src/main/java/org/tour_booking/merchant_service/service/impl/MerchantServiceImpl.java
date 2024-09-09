package org.tour_booking.merchant_service.service.impl;

import exception.AppException;
import exception.ERROR_CODE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tour_booking.merchant_service.constant.VERIFICATION_STATUS;
import org.tour_booking.merchant_service.mapper.MerchantMapper;
import org.tour_booking.merchant_service.model.entity.BusinessLicenseEntity;
import org.tour_booking.merchant_service.model.entity.MerchantEntity;
import org.tour_booking.merchant_service.model.request.AdminListMerchantRequest;
import org.tour_booking.merchant_service.model.request.ApproveMerchantRequest;
import org.tour_booking.merchant_service.model.request.CreateMerchantActorRequest;
import org.tour_booking.merchant_service.model.request.RegisterMerchantRequest;
import org.tour_booking.merchant_service.model.response.AdminListMerchantResponse;
import org.tour_booking.merchant_service.model.response.ApproveMerchantResponse;
import org.tour_booking.merchant_service.model.response.CreateMerchantActorResponse;
import org.tour_booking.merchant_service.model.response.RegisterMerchantResponse;
import org.tour_booking.merchant_service.proxy.feign.UaaServiceClient;
import org.tour_booking.merchant_service.proxy.feign.request.MerchantAccountCreationRequest;
import org.tour_booking.merchant_service.repository.BusinessLicenseRepository;
import org.tour_booking.merchant_service.repository.MerchantRepository;
import org.tour_booking.merchant_service.repository.filter.MerchantByFilter;
import org.tour_booking.merchant_service.service.MerchantService;
import utils.DateUtils;
import utils.SimplePage;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/31/2024
 */
@Service
@Slf4j
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MerchantServiceImpl implements MerchantService {

    MerchantRepository merchantRepo;
    BusinessLicenseRepository businessLicenseRepo;

    UaaServiceClient uaaServiceClient;

    MerchantMapper merchantMapper;

    /**
     * get list merchant ở admin page
     */
    @Override
    public SimplePage<AdminListMerchantResponse> getAllByFilters(AdminListMerchantRequest request) {
        var filter = MerchantByFilter.builder()
                .verificationStatus(request.getVerificationStatus())
                .isActive(request.getIsActive())
                .fromDate(DateUtils.stringToLocalDateTime(request.getFromDate()))
                .toDate(DateUtils.stringToLocalDateTime(request.getToDate()))
                .build();

        Page<MerchantEntity> merchantEntities = merchantRepo
                .findAllByVerificationStatusAndIsActive(filter, request.getPageable());

        List<Long> merchantIds = merchantEntities.stream()
                .map(MerchantEntity::getId).toList();
        List<BusinessLicenseEntity> businessLicenseEntities = businessLicenseRepo
                .findByMerchantIds(merchantIds);

        Map<Long, Map<String, LocalDateTime>> merchantBusinessLicenseMap = businessLicenseEntities.stream()
                .collect(Collectors.groupingBy(
                        BusinessLicenseEntity::getMerchantId,
                        Collectors.toMap(
                                BusinessLicenseEntity::getBusinessLicenseImageUrl,
                                BusinessLicenseEntity::getLicenseExpiryDate
                        )
                ));

        List<AdminListMerchantResponse> responses = merchantEntities.stream()
                .map(merchant -> {
                    AdminListMerchantResponse response = merchantMapper.toAdminListMerchantResponse(merchant);
                    response.setBusinessLicenseMap(merchantBusinessLicenseMap.get(merchant.getId()));
                    return response;
                })
                .toList();

        return new SimplePage<>(responses, merchantEntities.getTotalElements(), request.getPageable());
    }

    @Override
    public RegisterMerchantResponse register(RegisterMerchantRequest request) {
        if (merchantRepo.existsByContactEmail(request.getContactEmail())) {
            throw new AppException(ERROR_CODE.EMAIL_EXISTED);
        }
        MerchantEntity merchant = merchantRepo.save(merchantMapper.toEntity(request));

        List<BusinessLicenseEntity> businessLicenses = request.getBusinessLicenseMap().keySet()
                .stream()
                .map(licenseExpiryDate -> BusinessLicenseEntity.builder()
                        .merchantId(merchant.getId())
                        .licenseExpiryDate(DateUtils.stringToLocalDateTime(licenseExpiryDate))
                        .businessLicenseImageUrl("url")
                        .build())
                .toList();
        businessLicenseRepo.saveAll(businessLicenses);

        RegisterMerchantResponse response = merchantMapper.toRegisterMerchantResponse(merchant);
        response.setBusinessLicenseMap(new HashMap<>());
        return response;
    }

    @Override
    public ApproveMerchantResponse approve(ApproveMerchantRequest request) {
        MerchantEntity merchant = merchantRepo.findById(request.getMerchantId())
                .orElseThrow(() -> new AppException(ERROR_CODE.MERCHANT_NOT_EXISTED));

        String verificationStatus = request.getIsApproved() ?
                VERIFICATION_STATUS.VERIFIED.val : VERIFICATION_STATUS.REJECTED.val;
        if (!request.getIsApproved() && StringUtils.isNotBlank(request.getRejectReason())) {
            merchant.setRejectionReason(request.getRejectReason());
        }
        merchant.setApprovalDate(LocalDateTime.now());
        merchant.setIsActive(true);
        merchant.setVerificationStatus(verificationStatus);

        if (request.getIsApproved()) {
            MerchantAccountCreationRequest accountCreationRequest = MerchantAccountCreationRequest.builder()
                    .merchantId(merchant.getId())
                    .username(utils.StringUtils.generateUniqueString(10))
                    .password(utils.StringUtils.generateUniqueString(10))
                    .email(merchant.getContactEmail())
                    .phone(merchant.getContactPhone())
                    .build();
            uaaServiceClient.createAccountMerchant(accountCreationRequest);
        }

        return ApproveMerchantResponse.builder()
                .isApproved(true)
                .build();
    }

    @Override
    public CreateMerchantActorResponse createActor(CreateMerchantActorRequest request) {
        var accounts = uaaServiceClient.createMerchantActorAccount(request);
        return CreateMerchantActorResponse.builder()
                .merchantId(request.getMerchantId())
                .accounts(accounts.getData())
                .build();
    }

}
