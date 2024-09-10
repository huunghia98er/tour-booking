package org.tour_booking.merchant_service.constant;

public enum VERIFICATION_STATUS {

    PENDING("PENDING"),
    VERIFIED("VERIFIED"),
    REJECTED("REJECTED");

    public final String val;

    VERIFICATION_STATUS(String val) {
        this.val = val;
    }

}
