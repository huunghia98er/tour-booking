package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

    public static String convertCamelToSnake(String camelCase) {
        StringBuilder snakeCase = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char currentChar = camelCase.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                if (i > 0 && camelCase.charAt(i - 1) != '_') {
                    snakeCase.append('_');
                }
                snakeCase.append(Character.toLowerCase(currentChar));
            } else {
                snakeCase.append(currentChar);
            }
        }
        return snakeCase.toString();
    }

    public static String convertSnakeToCamel(String snakeCase) {
        StringBuilder camelCase = new StringBuilder();
        String[] parts = snakeCase.split("_");

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (i == 0) {
                camelCase.append(part);
            } else {
                camelCase.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    camelCase.append(part.substring(1));
                }
            }
        }
        return camelCase.toString();
    }
}
