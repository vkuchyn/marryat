package com.keypr.marryat.web.commons;

import com.keypr.marryat.commons.Clock;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Fake implementation of clock. Returns fixed date and time provided in constructor.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@AllArgsConstructor
public final class FakeClock implements Clock {

    public static final LocalDateTime FIXED_CURRENT_TIME = LocalDate.of(2017, 8, 14).atStartOfDay();

    private final LocalDateTime now;

    public FakeClock() {
        this(FIXED_CURRENT_TIME);
    }

    @Override
    public LocalDateTime time() {
        return this.now;
    }
}
