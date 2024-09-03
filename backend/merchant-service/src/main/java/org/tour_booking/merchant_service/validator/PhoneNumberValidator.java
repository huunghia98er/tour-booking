package org.tour_booking.merchant_service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/19/2024
 */
public final class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstrain, String> {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(
            "^\\+?[1-9]\\d{1,14}$"
    );

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }
}
