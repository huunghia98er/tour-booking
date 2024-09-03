package org.tour_booking.merchant_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.tour_booking.merchant_service.feign.request.AccountCreationRequest;
import org.tour_booking.merchant_service.feign.response.AccountResponse;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/3/2024
 */
@FeignClient(name = "uaa-service",
        url = "${feign.client.name.uaa}",
        path = "${feign.client.path.uaa}"
)
public interface UAAServiceFeignClient {

    @PostMapping(value = "/account/register", produces = MediaType.APPLICATION_JSON_VALUE)
    AccountResponse createProfile(@RequestBody AccountCreationRequest request);

}
