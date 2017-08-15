package com.keypr.marryat.web.validation;

import com.keypr.marryat.web.ReservationView;
import org.junit.Test;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Unit test for reservation dates validation.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public final class DateRangeValidatorTest {

    @Test
    public void validatesCorrectDateRange() throws Exception {
        final ReservationView reservation = new ReservationView(
                "first", "last", "room",
                LocalDate.of(2017, 8, 14), LocalDate.of(2017, 8, 15)
        );
        final boolean valid = new DateRangeValidator().isValid(reservation, mock(ConstraintValidatorContext.class));
        assertThat(valid, is(true));
    }

    @Test
    public void invalidatesEndDateBeforeStartDate() throws Exception {
        final ReservationView reservation = new ReservationView(
                "first", "last", "room",
                LocalDate.of(2017, 8, 15), LocalDate.of(2017, 8, 14)
        );
        final boolean valid = new DateRangeValidator().isValid(reservation, mock(ConstraintValidatorContext.class));
        assertThat(valid, is(false));
    }

    @Test
    public void invalidatesEndDateIsequalStartDate() throws Exception {
        final ReservationView reservation = new ReservationView(
                "first", "last", "room",
                LocalDate.of(2017, 8, 14), LocalDate.of(2017, 8, 14)
        );
        final boolean valid = new DateRangeValidator().isValid(reservation, mock(ConstraintValidatorContext.class));
        assertThat(valid, is(false));
    }
}