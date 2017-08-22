package com.keypr.marryat.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * JSR303-JSR349 validator to check if room exists in hotel. Should be used only for room field in json model.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = RoomExistsValidator.class)
public @interface RoomExists {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
