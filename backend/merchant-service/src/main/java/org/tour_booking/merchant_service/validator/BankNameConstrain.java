package org.tour_booking.merchant_service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.tour_booking.merchant_service.validator.impl.BankNameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {BankNameValidator.class})
public @interface BankNameConstrain {

    String message() default "Bank not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
