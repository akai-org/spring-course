package pl.org.akai.projectreactor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.org.akai.projectreactor.service.MyException;
import pl.org.akai.projectreactor.service.ReactiveService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ProjectReactorApplicationTests {

    private ReactiveService service;

    @BeforeEach
    void setUp() {
        service = new ReactiveService();
    }

    @Test
    void monoExample() {
        Mono<Integer> mono = Mono.just(1);
        Mono<Integer> monoEmpty = Mono.empty();
        Mono<Integer> monoEmpty2 = Mono.justOrEmpty(null);

        System.out.println("Mono");
        mono.subscribe(System.out::println);

        System.out.println("MonoEmpty");
        monoEmpty.subscribe(System.out::println);

        System.out.println("MonoEmpty2");
        monoEmpty2.subscribe(System.out::println);
    }

    @Test
    void fluxExample() {
        Flux<Integer> flux = Flux.just(5, 4, 3, 2, 1);
        Flux<Integer> fluxEmpty = Flux.empty();
//        Flux<Integer> fluxEmpty2 = Flux.just(null);
//        Flux<Integer> fluxEmpty2 = Flux.just(null, null);
        Flux<Integer> fluxEmpty2 = Flux.just(1, 2, null);


        System.out.println("Flux");
        flux.subscribe(System.out::println);

        System.out.println("Flux empty");
        fluxEmpty.subscribe(System.out::println);

        System.out.println("Flux empty 2");
        fluxEmpty2.subscribe(System.out::println);
    }

    @Test
    void subscribe() {
        Flux<Integer> flux = Flux.just(5, 4, 3, 2, 1)
                                 .map(x -> x + 1)
                                 .doOnNext(System.out::println);

//        flux.subscribe();
    }

    @Test
    void multipleSubscribe() {
        Mono<Long> mono = Mono.just(System.currentTimeMillis());

        mono.subscribe(System.out::println);

        for (int i = 0; i < 10000000; i++) {
            // wait
        }
        System.out.println(System.currentTimeMillis());
        mono.subscribe(System.out::println);
    }

    @Test
    void monoDefer() {
        Mono<Long> mono = Mono.defer(() -> Mono.just(System.currentTimeMillis()));

        mono.subscribe(System.out::println);

        for (int i = 0; i < 10000000; i++) {
            // wait
        }
        System.out.println(System.currentTimeMillis());
        mono.subscribe(System.out::println);
    }

    @Test
    void flatMapExample() {

        Mono.just(1000)
            .flatMapMany(service::getFlux)
            .flatMap(service::getFlux, 10)
            .collectList()
            .subscribe(System.out::println);
    }

    @Test
    void doOn() {
        Mono.just(5)
            .map(x -> x + 1)
            .doOnSuccess(x -> System.out.printf("On success: %s%n", x))
            .doOnNext(x -> System.out.printf("On next: %s%n", x))
            .doFinally(x -> System.out.printf("On finally: %s%n", x))
            .doOnError(RuntimeException.class, e -> System.out.printf("On error: %s%n", e))
            .doOnSubscribe(x -> System.out.printf("On Subscribe: %s%n", x))
            .subscribe();
    }

    @Test
    void switchIfEmpty() {
        Mono.justOrEmpty(generateIntegerNull())
            .map(x -> x + 1)
            .switchIfEmpty(Mono.just(5))
            .subscribe(System.out::println);
    }

    private Integer generateIntegerNull() {
        return null;
    }

    @Test
    void errorHanding() {
        Mono.just(5)
            .map(x -> x + 1)
            .map(x -> generateError())
            .doOnNext(System.out::println)
            .doOnError(System.out::println)
            .doOnError(RuntimeException.class, System.out::println)
            .onErrorResume(e -> Mono.just(10))
            .subscribe(System.out::println);
    }

    private Integer generateError() {
        throw new RuntimeException();
    }

    @Test
    void errorHandlingMono() {
        Mono.just(5)
            .map(x -> x + 1)
            .flatMap(x -> generateErrorMono())
            .doOnNext(x -> System.out.println("On next"))
            .then(Mono.just(10))
            .doOnError(e -> System.out.println("On error"))
            .onErrorMap(e -> new MyException())
            .doOnError(NoSuchElementException.class, e -> System.out.println("On error NoSuchElementException"))
            .doOnError(MyException.class, e -> System.out.println("On error MyException"))
            .onErrorResume(e -> Mono.just(15))
            .subscribe(System.out::println);
    }

    private Mono<Integer> generateErrorMono() {
        return Mono.error(new RuntimeException());
    }

    @Test
    void mockExampleWithCall() {
        ReactiveService mock = mock(ReactiveService.class);
        when(mock.getTime()).thenReturn(Mono.just(500L));

        Mono<Long> result = Mono.just(10)
                                .map(x -> {
                                    throw new RuntimeException();
                                })
                                .then(mock.getTime());

        StepVerifier.create(result)
                    .expectError(RuntimeException.class)
                    .verify();
        verify(mock, times(1)).getTime();
    }

    @Test
    void mockExampleWithCallPractice() {
        ReactiveService mock = mock(ReactiveService.class);
        when(mock.getTime()).thenReturn(Mono.error(new IllegalStateException()));

        Mono<Long> result = Mono.just(10)
                                .map(x -> {
                                    // return x;
                                    throw new MyException();
                                })
                                .then(mock.getTime());

        StepVerifier.create(result)
                    .expectError(MyException.class)
                    .verify();
        verify(mock, times(1)).getTime();
    }

    @Test
    void mockExampleWithoutCall() {
        ReactiveService mock = mock(ReactiveService.class);

        Mono<List<Integer>> result = Mono.just(10)
                                         .flatMapMany(x -> mock.getFluxError(x))
                                         .collectList();

        StepVerifier.create(result)
                    .expectError(RuntimeException.class)
                    .verify();
        verify(mock, times(0)).getTime();
    }

    @Test
    void subscribeVsBlock() {
        Integer x = service.getFlux(10).blockFirst();
        Integer y = service.getFlux(10).blockLast();
        Optional<Long> z = service.getTime().blockOptional();
        Long w = service.getTime().block();
    }


}
