package org.tour_booking.merchant_service.utils;

import constant.BankCodes;
import exception.AppException;
import exception.ERROR_CODE;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/9/2024
 */
public final class BankUtils {

    public static void isValidBankNumber(final String bankName, final String bankNumber) {
        String bankNumberPrefix = BankCodes.bankMap.get(bankName);
        if (!bankNumber.startsWith(bankNumberPrefix)) {
            throw new AppException(ERROR_CODE.INVALID_BANK_NUMBER);
        }
    }

}
