package org.tour_booking.merchant_service.service;

import org.springframework.data.domain.Pageable;
import org.tour_booking.merchant_service.model.request.BusinessPolicyCreationRequest;
import org.tour_booking.merchant_service.model.request.BusinessPolicyUpdateRequest;
import org.tour_booking.merchant_service.model.response.BusinessPolicyDTO;
import utils.SimplePage;

public interface PolicyService {

    SimplePage<BusinessPolicyDTO> getAll(Pageable pageable);

    BusinessPolicyDTO create(BusinessPolicyCreationRequest request);

    BusinessPolicyDTO update(BusinessPolicyUpdateRequest request);

    Boolean delete(Long policyId);

}
