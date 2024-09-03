package org.tour_booking.merchant_service.repository.filter;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/1/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MerchantByFilter {

    String verificationStatus;

    Boolean isActive;

    LocalDateTime fromDate;

    LocalDateTime toDate;

}
