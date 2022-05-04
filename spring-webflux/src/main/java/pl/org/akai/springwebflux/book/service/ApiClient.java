package pl.org.akai.springwebflux.book.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import pl.org.akai.springwebflux.book.dto.BookDetailsDto;
import pl.org.akai.springwebflux.common.web.ResponseTemplate;
import pl.org.akai.springwebflux.properties.ProjectProperties;
import pl.org.akai.springwebflux.user.service.JwtBuilder;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ApiClient {

    private final WebClient webClient;
    private final JwtBuilder jwtBuilder;

    public ApiClient(ProjectProperties properties, JwtBuilder jwtBuilder) {
        this.webClient = WebClient.builder()
                                  .baseUrl(UriComponentsBuilder.fromHttpUrl(properties.getApiBaseUrl()).toUriString())
                                  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                  .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                                  .build();
        this.jwtBuilder = jwtBuilder;
    }

    public Mono<BookDetailsDto> doRequest(Integer bookId) {
        return webClient.get()
                        .uri("/api/books/{bookId}", bookId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtBuilder.generateToken("api"))
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<ResponseTemplate<BookDetailsDto>>() {})
                        .doOnError(WebClientResponseException.NotFound.class, e -> log.info("Book with id {} not found", bookId))
                        .onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.empty())
                        .doOnError(e -> log.error("Error during call to API"))
                        .map(ResponseTemplate::payload);
    }
}
