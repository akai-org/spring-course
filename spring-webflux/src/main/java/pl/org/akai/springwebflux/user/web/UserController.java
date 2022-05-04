package pl.org.akai.springwebflux.user.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.org.akai.springwebflux.common.web.ServerResponseFactory;
import pl.org.akai.springwebflux.user.dto.UsernameDto;
import pl.org.akai.springwebflux.user.service.JwtBuilder;
import reactor.core.publisher.Mono;

import static pl.org.akai.springwebflux.common.web.CommonVariable.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtBuilder jwtBuilder;

    public Mono<ServerResponse> createToken(ServerRequest serverRequest) {
        return Mono.deferContextual(
                ctx -> serverRequest.bodyToMono(UsernameDto.class)
                                    .map(usernameDto ->jwtBuilder.generateToken(usernameDto.username()))
                                    .flatMap(ServerResponseFactory::createHttpSuccessResponse)
                                    .doOnError(e -> log.error(e.getMessage(), e))
                                    .onErrorResume(e -> ServerResponseFactory.createHttpInternalServerErrorResponse())
                                    .doOnSuccess(r -> log.info(LOG_MESSAGE, ctx.getOrDefault(PATH, UNKNOWN), ctx.getOrDefault(USER, UNKNOWN))));
    }
}
