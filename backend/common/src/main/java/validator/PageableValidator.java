package validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.data.domain.Pageable;

public final class PageableValidator implements ConstraintValidator<PageableConstrain, Pageable> {

    @Override
    public boolean isValid(Pageable pageable, ConstraintValidatorContext context) {
        if (pageable == null) {
            return true;
        }
        if (pageable.getPageSize() < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Page size must be at least 1")
                    .addConstraintViolation();
            return false;
        }
        if (pageable.getPageNumber() < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Page number cannot be negative")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
