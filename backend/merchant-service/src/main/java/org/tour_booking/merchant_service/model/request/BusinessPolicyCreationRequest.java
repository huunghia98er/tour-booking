package org.tour_booking.merchant_service.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/10/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusinessPolicyCreationRequest {

    String name;
    String description;
    BigDecimal commissionRate;
    Integer paymentTerms;

}
