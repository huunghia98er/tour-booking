package org.tour_booking.merchant_service.service.impl;

import exception.AppException;
import exception.ERROR_CODE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tour_booking.merchant_service.mapper.BusinessPolicyMapper;
import org.tour_booking.merchant_service.model.entity.BusinessPolicyEntity;
import org.tour_booking.merchant_service.model.request.BusinessPolicyCreationRequest;
import org.tour_booking.merchant_service.model.request.BusinessPolicyUpdateRequest;
import org.tour_booking.merchant_service.model.response.BusinessPolicyDTO;
import org.tour_booking.merchant_service.repository.BusinessPolicyRepository;
import org.tour_booking.merchant_service.service.PolicyService;
import utils.SimplePage;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/10/2024
 */
@Service
@Slf4j
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PolicyServiceImpl implements PolicyService {

    BusinessPolicyRepository businessPolicyRepo;

    BusinessPolicyMapper businessPolicyMapper;

    @Override
    public SimplePage<BusinessPolicyDTO> getAll(Pageable pageable) {
        var data = businessPolicyRepo.findAllByIsActive(true, pageable);
        var response = data.getContent().stream()
                .map(businessPolicyMapper::toDto)
                .toList();
        return new SimplePage<>(response, data.getTotalElements(), data.getPageable());
    }

    @Override
    public BusinessPolicyDTO create(BusinessPolicyCreationRequest request) {
        var entity = businessPolicyMapper.createReqToEntity(request);
        return businessPolicyMapper.toDto(businessPolicyRepo.save(entity));
    }

    @Override
    public BusinessPolicyDTO update(BusinessPolicyUpdateRequest request) {
        var entity = this.findById(request.getId());
        entity = businessPolicyMapper.updateReqToEntity(request);
        return businessPolicyMapper.toDto(businessPolicyRepo.save(entity));
    }

    @Override
    public Boolean delete(Long policyId) {
        var entity = this.findById(policyId);
        entity.setIsActive(false);
        businessPolicyRepo.save(entity);
        return true;
    }

    public BusinessPolicyEntity findById(Long policyId) {
        return businessPolicyRepo.findById(policyId)
                .orElseThrow(() -> new AppException(ERROR_CODE.MERCHANT_NOT_EXISTED));
    }

}
