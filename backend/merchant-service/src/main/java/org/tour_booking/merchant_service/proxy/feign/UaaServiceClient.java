package org.tour_booking.merchant_service.proxy.feign;

import api.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.tour_booking.merchant_service.config.AuthenticationRequestInterceptor;
import org.tour_booking.merchant_service.model.request.CreateMerchantActorRequest;
import org.tour_booking.merchant_service.proxy.feign.request.MerchantAccountCreationRequest;
import org.tour_booking.merchant_service.proxy.feign.response.AccountResponse;

import java.util.List;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/3/2024
 */
@FeignClient(name = "${feign.client.name.uaa}",
        url = "${feign.client.url.uaa}",
        configuration = AuthenticationRequestInterceptor.class
)
public interface UaaServiceClient {

    @PostMapping(value = "/account/merchant/create",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<AccountResponse> createAccountMerchant(@RequestBody MerchantAccountCreationRequest request);

    @PostMapping(value = "/account/merchant/actors/create",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<List<AccountResponse>> createMerchantActorAccount(@RequestBody CreateMerchantActorRequest request);

}
