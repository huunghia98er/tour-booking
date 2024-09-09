package org.tour_booking.merchant_service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.tour_booking.merchant_service.validator.impl.PhoneNumberValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/19/2024
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumberConstrain {
    String message() default "Wrong phone number format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
