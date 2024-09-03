package org.tour_booking.merchant_service.constant;

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
    CREATE_TOUR("CREATE_TOUR"),
    EDIT_TOUR("EDIT_TOUR"),
    DELETE_TOUR("DELETE_TOUR"),
    TOUR_GUIDE("TOUR_GUIDE"),
    ;

    public final String val;

    PERMISSION(String val) {
        this.val = val;
    }
}
