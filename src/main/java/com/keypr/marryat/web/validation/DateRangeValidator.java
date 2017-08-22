package com.keypr.marryat.web.validation;

import com.keypr.marryat.web.ReservationView;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Validator implementation. Validates reservation start date is before end date.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public final class DateRangeValidator implements ConstraintValidator<DateRange, ReservationView> {

    @Override
    public void initialize(final DateRange dateRange) {
    }

    @Override
    public boolean isValid(
            final ReservationView reservation, final ConstraintValidatorContext validatorContext
    ) {
        LocalDate start = reservation.getStart();
        final LocalDate end = reservation.getEnd();
        return start == null || end == null || end.isAfter(start);
    }
}
