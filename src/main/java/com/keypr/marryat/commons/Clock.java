package com.keypr.marryat.commons;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Interface responsible for current date/time. Is useful for testing purposes, provides ability to mock in tests.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public interface Clock {

    Clock REAL_CLOCK = new Clock() {
    };

    /**
     * @return current time.
     */
    default LocalDateTime time() {
        return LocalDateTime.now();
    }

    /**
     * @return current date.
     */
    default LocalDate date() {
        return this.time().toLocalDate();
    }

}
