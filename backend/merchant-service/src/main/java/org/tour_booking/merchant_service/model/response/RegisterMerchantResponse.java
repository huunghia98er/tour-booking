package org.tour_booking.merchant_service.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;
import org.tour_booking.merchant_service.constant.VERIFICATION_STATUS;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Basic information about the agency
 *
 * @Author: luunguyen301297
 * @LastModified: 8/19/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterMerchantResponse {

    Long id;

    String name;

    String contactEmail;

    String contactPhone;

    String address;

    String bankAccountNumber;

    String bankName;

    String bankAccountHolderName;

    LocalDateTime registrationDate;

    VERIFICATION_STATUS verificationStatus;

    LocalDateTime approvalDate;

    Boolean isActive;

    String rejectionReason;

    Map<String, MultipartFile> businessLicenseMap;

}
