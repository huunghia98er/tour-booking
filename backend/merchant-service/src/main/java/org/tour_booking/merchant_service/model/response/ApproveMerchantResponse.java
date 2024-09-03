package org.tour_booking.merchant_service.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class ApproveMerchantResponse {

    Boolean isApproved;

}
