package com.keypr.marryat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarryatApplication {

    //TODO(vkuchyn) cleanup unnecessary auto configurations like Thymeleaf, etc.
    public static void main(final String... args) {
        SpringApplication.run(MarryatApplication.class, args);
    }

}
