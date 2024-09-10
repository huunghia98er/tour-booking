package exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERROR_CODE {

    /**
     * Global Exception
    **/
    UNCATEGORIZED_EXCEPTION(9000, "Uncategorized error"),
    INVALID_PARAMETER(9001, "Invalid parameter"),
    INVALID_KEY(9002, "Uncategorized error"),

    /**
     * Auth Exception
     **/
    UNAUTHENTICATED(1001, "Unauthenticated"),
    UNAUTHORIZED(1002, "You do not have permission"),
    INVALID_TOKEN(1003, "Invalid token"),
    USER_EXISTED(1004, "User existed"),
    USER_NOT_EXISTED(1005, "User not existed"),
    EMAIL_EXISTED(1006, "Email existed"),
    EMAIL_NOT_EXISTED(1007, "Email not existed"),

    /**
     * Valid Exception
     **/
    INVALID_PAGE_SIZE(2001, "Invalid page size"),
    INVALID_PAGE_NUMBER(2002, "Invalid page number"),
    INVALID_USERNAME(2003, "Username must be at least {min} characters"),
    INVALID_PASSWORD(2004, "Password must be at least {min} characters"),
    INVALID_PHONE_NUMBER(2005, "Wrong phone number format"),
    INVALID_EMAIL(2005, "Wrong email format"),
    BANK_NOT_FOUND(2006, "Bank not found"),
    INVALID_BANK_NUMBER(2007, "Invalid bank number"),

    /**
     * Other Exception
     **/
    MERCHANT_EXISTED(4001, "Merchant existed"),
    MERCHANT_NOT_EXISTED(4002, "Merchant not existed"),
    PERMISSION_NOT_FOUND(4003, "Permission not found"),
    ;

    private final int code;
    private final String message;

}
