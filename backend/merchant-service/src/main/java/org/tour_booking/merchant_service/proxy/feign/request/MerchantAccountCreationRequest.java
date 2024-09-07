package org.tour_booking.merchant_service.proxy.feign.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MerchantAccountCreationRequest {

    Long merchantId;
    String username;
    String password;
    String email;
    String phone;

}
