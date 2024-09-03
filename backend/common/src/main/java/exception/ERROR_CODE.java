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
    USER_EXISTED(1001, "User existed"),
    USER_NOT_EXISTED(1002, "User not existed"),
    UNAUTHENTICATED(1003, "Unauthenticated"),
    UNAUTHORIZED(1004, "You do not have permission"),
    INVALID_USERNAME(1005, "Username must be at least {min} characters"),
    INVALID_PASSWORD(1006, "Password must be at least {min} characters"),
    INVALID_TOKEN(1007, "Invalid token"),

    /**
     * Other Exception
     **/
    INVALID_PAGE_SIZE(4002, "Invalid page size"),
    INVALID_PAGE_NUMBER(4003, "Invalid page number"),
    MERCHANT_EXISTED(4004, "Merchant existed"),
    MERCHANT_NOT_EXISTED(4005, "Merchant not existed"),
    ;

    private final int code;
    private final String message;

}
