package com.keypr.marryat.web.validation;

import com.keypr.marryat.web.ReservationView;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
        return reservation.getEnd().isAfter(reservation.getStart());
    }
}
