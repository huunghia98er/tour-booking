package org.tour_booking.auth_service.constant;

public enum PERMISSION {
    APPROVE_MERCHANT("APPROVE_MERCHANT"),
    REJECT_MERCHANT("REJECT_MERCHANT");

    public final String val;

    PERMISSION(String val) {
        this.val = val;
    }
}
