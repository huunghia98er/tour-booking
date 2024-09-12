package org.tour_booking.payment_service.constant;

public enum PAYMENT_STATUS {

    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    CANCEL("CANCEL");

    public final String val;

    PAYMENT_STATUS(String val) {
        this.val = val;
    }

}
