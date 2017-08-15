package com.keypr.marryat.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validates {@link java.time.LocalDate} field is today or future.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = FutureOrTodayValidator.class)
public @interface FutureOrToday {

    String message() default "{start.date.after.today}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
