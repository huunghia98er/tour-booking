package org.tour_booking.merchant_service.service.impl;

import exception.AppException;
import exception.ERROR_CODE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.tour_booking.merchant_service.constant.VERIFICATION_STATUS;
import org.tour_booking.merchant_service.mapper.MerchantMapper;
import org.tour_booking.merchant_service.model.request.AdminListMerchantRequest;
import org.tour_booking.merchant_service.model.response.AdminListMerchantResponse;
import org.tour_booking.merchant_service.model.entity.BusinessLicenseEntity;
import org.tour_booking.merchant_service.model.entity.MerchantEntity;
import org.tour_booking.merchant_service.model.request.ApproveMerchantRequest;
import org.tour_booking.merchant_service.model.request.CreateMerchantActorRequest;
import org.tour_booking.merchant_service.model.request.RegisterMerchantRequest;
import org.tour_booking.merchant_service.model.response.ApproveMerchantResponse;
import org.tour_booking.merchant_service.model.response.CreateMerchantActorResponse;
import org.tour_booking.merchant_service.model.response.RegisterMerchantResponse;
import org.tour_booking.merchant_service.repository.BusinessLicenseRepository;
import org.tour_booking.merchant_service.repository.MerchantRepository;
import org.tour_booking.merchant_service.repository.filter.MerchantByFilter;
import org.tour_booking.merchant_service.service.MerchantService;
import util.DateUtils;
import util.SimplePage;

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
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MerchantServiceImpl implements MerchantService {

    MerchantRepository merchantRepo;
    BusinessLicenseRepository businessLicenseRepo;

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
        MerchantEntity merchant = merchantRepo.saveAndFlush(merchantMapper.toEntity(request));

        Map<String, String> businessLicenseMap = request.getBusinessLicenseMap();

        List<BusinessLicenseEntity> businessLicenses = businessLicenseMap.keySet()
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
        MerchantEntity entity = merchantRepo.findById(request.getMerchantId())
                .orElseThrow(() -> new AppException(ERROR_CODE.MERCHANT_NOT_EXISTED));

        String verificationStatus = request.getIsApproved() ?
                VERIFICATION_STATUS.VERIFIED.val : VERIFICATION_STATUS.REJECTED.val;
        if (!request.getIsApproved() && StringUtils.isNotBlank(request.getRejectReason())) {
            entity.setRejectionReason(request.getRejectReason());
        }
        entity.setApprovalDate(LocalDateTime.now());
        entity.setVerificationStatus(verificationStatus);

        merchantRepo.saveAndFlush(entity);

        return ApproveMerchantResponse.builder()
                .isApproved(true)
                .build();
    }

    @Override
    public CreateMerchantActorResponse createActor(CreateMerchantActorRequest request) {
        List<CreateMerchantActorRequest.Account> accounts = request.getAccounts();
        return null;
    }

}
