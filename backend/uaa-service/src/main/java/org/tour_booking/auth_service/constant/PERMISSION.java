package org.tour_booking.auth_service.constant;

public enum PERMISSION {

    /**
     * Admin Permissions
     */
    APPROVE_MERCHANT("APPROVE_MERCHANT"),
    REJECT_MERCHANT("REJECT_MERCHANT"),
    APPROVE_TOUR("APPROVE_TOUR"),

    /**
     * Merchant Permissions
     */
    MERCHANT_CREATE_TOUR("MERCHANT_CREATE_TOUR"),
    MERCHANT_EDIT_TOUR("MERCHANT_EDIT_TOUR"),
    MERCHANT_DELETE_TOUR("MERCHANT_DELETE_TOUR"),
    MERCHANT_TOUR_GUIDE("MERCHANT_TOUR_GUIDE"),

    /**
     * Customer permission
     */
    CUSTOMER("CUSTOMER");

    public final String val;

    PERMISSION(String val) {
        this.val = val;
    }

}
