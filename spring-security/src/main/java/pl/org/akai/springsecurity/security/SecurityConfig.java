package pl.org.akai.springsecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final CustomOAuth2UserService oAuth2UserService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
//        HTTP Basic
//        http.authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic();

        http.authorizeRequests()
            .antMatchers("/public/**", "/webjars/**", "/h2-console/**", "/oauth2/authorization/google").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
//                .loginPage()
            .loginPage("/login.html")
            .failureUrl("/login-error.html")
            .permitAll()
            .and()
            .oauth2Login()
            .loginPage("/login.html")
            .userInfoEndpoint().userService(oAuth2UserService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
