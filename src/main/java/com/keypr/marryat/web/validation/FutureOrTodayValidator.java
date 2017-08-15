package com.keypr.marryat.web.validation;

import com.keypr.marryat.commons.Clock;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Validates date is today or future.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@AllArgsConstructor
public final class FutureOrTodayValidator implements ConstraintValidator<FutureOrToday, LocalDate> {

    private static final long ONE = 1L;
    private final Clock clock;

    @Override
    public void initialize(final FutureOrToday annotation) {
    }

    @Override
    public boolean isValid(final LocalDate localDate, final ConstraintValidatorContext validatorContext) {
        return localDate.plusDays(ONE).isAfter(this.clock.date());
    }
}
