package pl.org.akai.projectreactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class ContextTest {
    @Test
    void context1() {
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                             .flatMap(s -> Mono.deferContextual(ctx -> Mono.just(s + " " + ctx.get(key)))) //(2)
                             .contextWrite(ctx -> ctx.put(key, "World")); //(1)

        StepVerifier.create(r)
                    .expectNext("Hello World") //(3)
                    .verifyComplete();
    }

    @Test
    void context2() {
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                             .contextWrite(ctx -> ctx.put(key, "World")) //(1)
                             .flatMap(s -> Mono.deferContextual(ctx -> Mono.just(s + " " + ctx.getOrDefault(key, "Stranger")))); //(2)

        StepVerifier.create(r)
                    .expectNext("Hello Stranger") //(3)
                    .verifyComplete();
    }

    @Test
    void context3() {
        String key = "message";
        Mono<String> r = Mono
                .deferContextual(ctx -> Mono.just("Hello " + ctx.get(key)))
                .contextWrite(ctx -> ctx.put(key, "Reactor")) //(1)
                .contextWrite(ctx -> ctx.put(key, "World")); //(2)

        StepVerifier.create(r)
                    .expectNext("Hello Reactor") //(3)
                    .verifyComplete();
    }

    @Test
    void context4() {
        String key = "message";
        Mono<String> r = Mono
                .deferContextual(ctx -> Mono.just("Hello " + ctx.get(key))) //(3)
                .contextWrite(ctx -> ctx.put(key, "Reactor")) //(2)
                .flatMap(s -> Mono.deferContextual(ctx -> Mono.just(s + " " + ctx.get(key)))) //(4)
                .contextWrite(ctx -> ctx.put(key, "World")); //(1)

        StepVerifier.create(r)
                    .expectNext("Hello Reactor World") //(5)
                    .verifyComplete();
    }

    @Test
    void context5() {
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                             .flatMap(s -> Mono
                                     .deferContextual(ctxView -> Mono.just(s + " " + ctxView.get(key))))
                             .flatMap(s -> Mono
                                     .deferContextual(ctxView -> Mono.just(s + " " + ctxView.get(key)))
                                     .contextWrite(ctx -> ctx.put(key, "Reactor")) //(2)
                             )
                             .contextWrite(ctx -> ctx.put(key, "World")); // (1)

        StepVerifier.create(r)
                    .expectNext("Hello World Reactor")
                    .verifyComplete();
    }
}
