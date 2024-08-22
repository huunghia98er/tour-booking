package util;

import constant.ApplicationConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;

@Slf4j
@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    public static final String LOCAL_ZONE_ID = "GMT+7";
    public static final String UTC_ZONE_ID = "UTC";

    public static LocalDateTime convertUtcToLocal(LocalDateTime utcDateTime, ZoneId targetZoneId) {
        ZonedDateTime utcZonedDateTime = utcDateTime.atZone(ZoneId.of(UTC_ZONE_ID));
        ZonedDateTime targetZonedDateTime = utcZonedDateTime.withZoneSameInstant(ZoneId.of(LOCAL_ZONE_ID));
        return targetZonedDateTime.toLocalDateTime();
    }

    public static boolean isValidDate(String input) {
        SimpleDateFormat format;
        try {
            format = new SimpleDateFormat(ApplicationConstant.FORMAT_DATE);
            format.setLenient(false);
            format.parse(input);
            return true;
        } catch (ParseException | IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValidDateFormat(String dateFormatPattern, String input) {
        SimpleDateFormat dateFormat;
        try {
            dateFormat = new SimpleDateFormat(dateFormatPattern);
            dateFormat.setLenient(false);
            dateFormat.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
