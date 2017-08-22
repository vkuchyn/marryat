package com.keypr.marryat.web.validation;

import com.keypr.marryat.commons.Clock;
import com.keypr.marryat.web.commons.FakeClock;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static com.keypr.marryat.web.commons.FakeClock.FIXED_CURRENT_TIME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Unit test for {@link FutureOrTodayValidator}.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public final class FutureOrTodayValidatorTest {

    private Clock clock;

    @Before
    public void setUp() {
        this.clock = new FakeClock();
    }

    @Test
    public void validatesFutureLocalDate() throws Exception {
        final LocalDate future = FIXED_CURRENT_TIME.toLocalDate().plusDays(1L);
        final boolean valid = new FutureOrTodayValidator(this.clock).isValid(future, null);
        assertThat(valid, is(true));
    }

    @Test
    public void validatesToday() throws Exception {
        final LocalDate today = FIXED_CURRENT_TIME.toLocalDate();
        final boolean valid = new FutureOrTodayValidator(this.clock).isValid(today, null);
        assertThat(valid, is(true));
    }

    @Test
    public void invalidatesYesterday() throws Exception {
        final LocalDate today = FIXED_CURRENT_TIME.toLocalDate().minusDays(1L);
        final boolean valid = new FutureOrTodayValidator(this.clock).isValid(today, null);
        assertThat(valid, is(false));
    }

    @Test
    public void returnsTrueIfInputNull() throws Exception {
        final boolean valid = new FutureOrTodayValidator(this.clock).isValid(null, null);
        assertThat(valid, is(true));
    }
}