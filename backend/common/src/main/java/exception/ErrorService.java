package exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ErrorService {

    private final MessageSource messageSource;

    public AppException exception(ERROR_CODE code, Object[] args) {
        return new AppException(code, getMessage(code, args));
    }

    public AppException exceptionMap(ERROR_CODE code, Map<String, Object> params) {
        return new AppException(code, getMessage(code, params));
    }

    private String getMessage(ERROR_CODE code, Object[] args) {
        return messageSource.getMessage(code.getMessage(), args, LocaleContextHolder.getLocale());
    }

    private String getMessage(ERROR_CODE code, Map<String, Object> params) {
        String messageTemplate = messageSource
                .getMessage(code.getMessage(), null, LocaleContextHolder.getLocale());
        if (params == null || params.isEmpty()) {
            return messageTemplate;
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            messageTemplate = messageTemplate.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return messageTemplate;
    }

}
