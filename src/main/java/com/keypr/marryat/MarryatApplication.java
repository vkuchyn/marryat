package com.keypr.marryat;

import com.keypr.marryat.commons.Clock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MarryatApplication {

    public static void main(final String... args) {
        SpringApplication.run(MarryatApplication.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.REAL_CLOCK;
    }
}
