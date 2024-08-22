package constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConstant {

    public static final String RULE_INPUT_KEY_VALUE_DETERMINER = "\\|";

    public static final String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static final String FORMAT_DATE_SHORT_DDMMYYYY = "dd/MM/yyyy";

    public static final String DEFAULT_MAX_DATE = "2099-01-01T00:00:00+0000";

    public static final String SLASH = "/";

}
