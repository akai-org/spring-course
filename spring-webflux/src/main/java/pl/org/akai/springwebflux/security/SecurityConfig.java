package pl.org.akai.springwebflux.security;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import pl.org.akai.springwebflux.properties.ProjectProperties;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    private final ProjectProperties properties;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.formLogin().disable()
                   .httpBasic().disable()
                   .csrf().disable()

                   // Paths Matcher and Access
                   .authorizeExchange()
                   .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                   .pathMatchers("/api/user/login").permitAll()
                   .anyExchange().authenticated()

                   // JWT spec
                   .and()
                   .oauth2ResourceServer()
                   .jwt()
                   .jwtDecoder(createJwtDecoder())
                   .and().and().build();
    }

    @Bean
    CorsWebFilter corsWebFilter() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "DELETE", "PATCH", "OPTIONS", "PUT"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return new CorsWebFilter(source);
    }

    private ReactiveJwtDecoder createJwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(properties.getSecret().getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
        return NimbusReactiveJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }


}
