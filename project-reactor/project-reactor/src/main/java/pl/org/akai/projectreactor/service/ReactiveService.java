package pl.org.akai.projectreactor.service;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;

public class ReactiveService {

    public Mono<Long>  getTime() {
        return Mono.just(System.currentTimeMillis());
    }

    public Mono<Long>  getTimeWithError() {
        return Mono.error(new RuntimeException());
    }

    public Flux<Integer>  getFlux(int size) {
        return Flux.fromStream(IntStream.range(0, size).boxed());
    }

    public Flux<Integer> getFluxError(Integer x) {
        return Flux.error(new RuntimeException());
    }
}
