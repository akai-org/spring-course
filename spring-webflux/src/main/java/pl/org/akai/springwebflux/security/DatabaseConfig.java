package pl.org.akai.springwebflux.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
@EnableR2dbcAuditing
public class DatabaseConfig {

    @Bean
    ReactiveAuditorAware<String> auditorAware() {
        return () -> ReactiveSecurityContextHolder.getContext()
                                                  .map(SecurityContext::getAuthentication)
                                                  .filter(JwtAuthenticationToken.class::isInstance)
                                                  .map(JwtAuthenticationToken.class::cast)
                                                  .map(JwtAuthenticationToken::getName);
    }
}

