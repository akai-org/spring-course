package pl.org.akai.springwebflux.security;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {
    @Override
    public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> handlerFunction) {
        return handlerFunction.handle(request)
                              .contextWrite(ctx -> ctx.put("path", request.path()))
                              .contextWrite(ctx -> ctx.put("user", request.remoteAddress()));
    }
}
