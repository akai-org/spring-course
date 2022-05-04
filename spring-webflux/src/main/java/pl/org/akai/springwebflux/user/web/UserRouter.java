package pl.org.akai.springwebflux.user.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.org.akai.springwebflux.security.AuthFilter;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    public static final String LOGIN_URL = "/api/user/login";

    @Bean
    RouterFunction<ServerResponse> loginRoutes(UserController bookController, AuthFilter authFilter) {
        return route(POST(LOGIN_URL).and(contentType(APPLICATION_JSON)), bookController::createToken)
                .filter(authFilter);
    }
}
