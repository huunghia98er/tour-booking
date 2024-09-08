package org.tour_booking.merchant_service.service;

import org.springframework.data.domain.Pageable;
import org.tour_booking.merchant_service.model.response.AdminListMerchantResponse;
import org.tour_booking.merchant_service.model.PolicyDTO;
import utils.SimplePage;

public interface PolicyService {

    SimplePage<AdminListMerchantResponse> getAll(Pageable pageable);

    PolicyDTO create(PolicyDTO request);

    PolicyDTO edit(PolicyDTO request);

    PolicyDTO delete(PolicyDTO request);

}
