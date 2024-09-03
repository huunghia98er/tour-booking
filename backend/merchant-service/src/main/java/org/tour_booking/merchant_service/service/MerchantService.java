package org.tour_booking.merchant_service.service;

import org.tour_booking.merchant_service.model.request.AdminListMerchantRequest;
import org.tour_booking.merchant_service.model.response.AdminListMerchantResponse;
import org.tour_booking.merchant_service.model.request.ApproveMerchantRequest;
import org.tour_booking.merchant_service.model.request.CreateMerchantActorRequest;
import org.tour_booking.merchant_service.model.request.RegisterMerchantRequest;
import org.tour_booking.merchant_service.model.response.ApproveMerchantResponse;
import org.tour_booking.merchant_service.model.response.CreateMerchantActorResponse;
import org.tour_booking.merchant_service.model.response.RegisterMerchantResponse;
import util.SimplePage;

public interface MerchantService {

    SimplePage<AdminListMerchantResponse> getAllByFilters(AdminListMerchantRequest request);

    RegisterMerchantResponse register(RegisterMerchantRequest request);

    ApproveMerchantResponse approve(ApproveMerchantRequest request);

    CreateMerchantActorResponse createActor(CreateMerchantActorRequest request);

}
