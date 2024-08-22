package util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommonUtils {

    static ObjectMapper objectMapper = new ObjectMapper();

    static Pattern NAME_PATTERN = Pattern
            .compile("^[\\p{L}'][ \\p{L}'-]*[\\p{L}]$", Pattern.MULTILINE);
    static Pattern SPECIAL_CHARACTER = Pattern
            .compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]", Pattern.MULTILINE);

    static String REQUEST_ID_REGEX = "^[0-9a-zA-z_-]{1,}$";
    static String NUMBER_REGEX = "^[0-9]{1,}$";

    public static boolean validateName(String name) {
        Matcher matcher = NAME_PATTERN.matcher(name);
        return matcher.find();
    }

    public static boolean hasSpecialChar(String check) {
        Matcher matcher = SPECIAL_CHARACTER.matcher(check);
        return matcher.find();
    }

    public static String toString(Object object) {
        try {
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException var2) {
            log.warn("Cannot convert java object to json string", var2);
            return null;
        }
    }

}
