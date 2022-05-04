package pl.org.akai.springwebflux.book.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.org.akai.springwebflux.security.AuthFilter;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static pl.org.akai.springwebflux.common.web.CommonVariable.BOOK_ID_PATH_PARAM;

@Configuration
public class BookRouter {

    public static final String BOOK_BASE_URL = "/api/books";
    public static final String BOOK_DETAILS_URL = format("/api/books/{%s}", BOOK_ID_PATH_PARAM);
    public static final String BOOK_DETAILS_REQUEST_URL = format("/api/books/{%s}/request", BOOK_ID_PATH_PARAM);


    @Bean
    RouterFunction<ServerResponse> bookRoutes(BookController bookController, AuthFilter authFilter) {
        return route(GET(BOOK_BASE_URL).and(accept(APPLICATION_JSON)), bookController::listBooks)
                .andRoute(GET(BOOK_DETAILS_URL).and(accept(APPLICATION_JSON)), bookController::getBookById)
                .andRoute(GET(BOOK_DETAILS_REQUEST_URL).and(accept(APPLICATION_JSON)), bookController::getBookByIdUsingRequest)
                .andRoute(POST(BOOK_BASE_URL).and(contentType(APPLICATION_JSON)), bookController::addBook)
                .andRoute(PUT(BOOK_DETAILS_URL).and(contentType(APPLICATION_JSON)), bookController::updateBook)
                .filter(authFilter);
    }
}
