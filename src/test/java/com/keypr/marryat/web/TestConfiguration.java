package com.keypr.marryat.web;

import com.keypr.marryat.commons.Clock;
import com.keypr.marryat.web.commons.FakeClock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Defines some test beans to replace production ones, such as {@link Clock}.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@Configuration
public class TestConfiguration {

    // Assuming for all tests with spring context we have fixed current time.
    @Bean
    @Primary
    public Clock fakeClock() {
        return new FakeClock();
    }
}
