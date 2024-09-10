package org.tour_booking.auth_service.validator.impl;

import constant.BankCodes;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.tour_booking.auth_service.validator.BankNameConstrain;

public final class BankNameValidator implements ConstraintValidator<BankNameConstrain, String> {

    @Override
    public boolean isValid(String bankName, ConstraintValidatorContext context) {
        if (bankName == null || bankName.isEmpty()) {
            return false;
        }
        return BankCodes.bankMap.containsKey(bankName);
    }

}
