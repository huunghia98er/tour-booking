package org.tour_booking.merchant_service.model.request;

import lombok.*;
import org.springframework.data.domain.Pageable;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/31/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminListMerchantRequest {

    String verificationStatus;

    Boolean isActive;

    String fromDate;

    String toDate;

    Pageable pageable;

}
