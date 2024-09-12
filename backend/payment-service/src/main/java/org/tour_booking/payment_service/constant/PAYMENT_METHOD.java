package org.tour_booking.payment_service.constant;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/12/2024
 */
public enum PAYMENT_METHOD {


    VNPAY("VN Pay");

    public final String val;

    PAYMENT_METHOD(String type) {
        this.val = type;
    }

}