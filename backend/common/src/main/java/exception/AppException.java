package exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppException extends RuntimeException {

    ERROR_CODE errorCode;

    public AppException(ERROR_CODE errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public AppException(ERROR_CODE errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
