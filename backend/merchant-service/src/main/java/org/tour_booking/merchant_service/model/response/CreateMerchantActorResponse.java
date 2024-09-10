package org.tour_booking.merchant_service.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.tour_booking.merchant_service.proxy.feign.response.AccountResponse;

import java.util.List;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/31/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMerchantActorResponse {

    Long merchantId;
    List<AccountResponse> accounts;

}
