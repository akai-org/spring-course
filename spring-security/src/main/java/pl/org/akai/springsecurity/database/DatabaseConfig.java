package pl.org.akai.springsecurity.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@Configuration
@EnableJdbcAuditing
public class DatabaseConfig {

    @Bean
    AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
