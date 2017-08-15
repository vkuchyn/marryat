package com.keypr.marryat.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * JSR303-JSR349 class-level validation annotation. Validates reservation has end date after start date.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
public @interface DateRange {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
