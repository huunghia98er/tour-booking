package org.tour_booking.merchant_service.model.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.tour_booking.merchant_service.constant.VERIFICATION_STATUS;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/31/2024
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminListMerchantResponse {

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
    boolean isActive = false;
    String rejectionReason;
    Map<String, LocalDateTime> businessLicenseMap;

}
