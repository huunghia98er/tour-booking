package validator;

import constant.TimeUnit;
import exception.ERROR_CODE;
import exception.ErrorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import util.DateUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@SuppressWarnings({"Duplicates"})
public final class BasicRequestValidator {

    static String FIELD = "field";
    ErrorService errorService;

    public void validBlankParam(String param, Object[] paramNames) {
        if (StringUtils.isBlank(param) || StringUtils.trimToEmpty(param).equalsIgnoreCase("null")) {
            throw errorService.exception(ERROR_CODE.INVALID_PARAMETER, paramNames);
        }
    }

    public void validBlankParam(String paramName, String paramValue) {
        if (StringUtils.isBlank(paramValue) || StringUtils.trimToEmpty(paramValue).equalsIgnoreCase("null")) {
            throw errorService
                    .exceptionMap(ERROR_CODE.INVALID_PARAMETER, Map.of(FIELD, paramName));
        }
    }

    public void validRegexParam(String paramName, String paramValue, String regexString) {
        if (StringUtils.isNotBlank(regexString) && ! Pattern.matches(regexString, paramValue)) {
            throw errorService
                    .exceptionMap(ERROR_CODE.INVALID_PARAMETER, Map.of(FIELD, paramName));
        }
    }

    public void validLengthParam(String paramName, String paramValue, Integer paramLen) {
        if (paramLen != null && paramValue.length() > paramLen) {
            throw errorService
                    .exceptionMap(ERROR_CODE.INVALID_PARAMETER, Map.of(FIELD, paramName));
        }
    }

    public void validDateFormat(String dateFormatString, String dateString, String paramName) {
        if (!DateUtils.isValidDateFormat(dateFormatString, dateString)) {
            throw errorService
                    .exceptionMap(ERROR_CODE.INVALID_PARAMETER, Map.of(FIELD, paramName));
        }
    }

    public void validFromTimeBeforeToTime(Date fromTime, Date toTime, Object[] paramNames) {
        Map<String, Object> mapFields = new HashMap<>();
        if (paramNames != null) {
            for (int i = 0; i < paramNames.length; i++) {
                if (paramNames[i] != null) {
                    mapFields.put(FIELD + (i + 1), paramNames[i]);
                }
            }
        }
        if (fromTime.after(toTime)) {
            throw errorService
                    .exceptionMap(ERROR_CODE.INVALID_PARAMETER, mapFields);
        }
    }

    public void validTimeRange(Date fromTime, Date toTime, TimeUnit timeUnit, Integer maxRange, Object[] paramNames) {
        Map<String, Object> mapFields = new HashMap<>();
        if (paramNames != null) {
            for (int i = 0; i < paramNames.length; i++) {
                if (paramNames[i] != null) {
                    mapFields.put(FIELD + (i + 1), paramNames[i]);
                }
            }
        }

        LocalDate fromDate = fromTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate toDate = toTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        switch (timeUnit) {
            case DAYS -> {
                long diffDays = ChronoUnit.DAYS.between(fromDate, toDate);
                if (diffDays > maxRange) {
                    throw errorService.exceptionMap(ERROR_CODE.INVALID_PARAMETER, mapFields);
                }
            }
            case MONTHS -> {
                Period periodMonths = Period.between(fromDate, toDate);
                int totalMonths = periodMonths.getYears() * 12 + periodMonths.getMonths();
                if (totalMonths > maxRange || (totalMonths == maxRange && periodMonths.getDays() > 0)) {
                    throw errorService.exceptionMap(ERROR_CODE.INVALID_PARAMETER, mapFields);
                }
            }
            case YEARS -> {
                Period periodYears = Period.between(fromDate, toDate);
                if (periodYears.getYears() > maxRange || (periodYears.getYears() == maxRange && (periodYears.getMonths() > 0 || periodYears.getDays() > 0))) {
                    throw errorService.exceptionMap(ERROR_CODE.INVALID_PARAMETER, mapFields);
                }
            }
            default -> {}
        }
    }

}
