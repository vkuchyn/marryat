package com.keypr.marryat;

import com.keypr.marryat.commons.Clock;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@Configuration
public class MarryatConfiguration {

    @Bean
    public Clock clock() {
        return Clock.REAL_CLOCK;
    }

    @Bean
    public SpringLiquibase liquibase(final DataSource dataSource) {
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/liquibase/changelog.xml");
        liquibase.setShouldRun(true);
        return liquibase;
    }
}
