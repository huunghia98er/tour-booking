package org.tour_booking.merchant_service.model.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.tour_booking.merchant_service.validator.BankNameConstrain;
import org.tour_booking.merchant_service.validator.EmailConstrain;
import org.tour_booking.merchant_service.validator.PhoneNumberConstrain;

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
public class RegisterMerchantRequest {

    @Size(max = 255)
    String name;

    @EmailConstrain(message = "INVALID_EMAIL")
    @Size(max = 255)
    String contactEmail;

    @PhoneNumberConstrain(message = "INVALID_PHONE_NUMBER")
    String contactPhone;

    String address;

    @Size(min = 6, max = 15)
    String bankAccountNumber;

    @BankNameConstrain(message = "BANK_NOT_FOUND")
    @Size(max = 255)
    String bankName;

    @Size(max = 255)
    String bankAccountHolderName;

    Map<String, String> businessLicenseMap;

}
