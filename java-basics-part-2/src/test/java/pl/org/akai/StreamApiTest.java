package pl.org.akai;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;

class StreamApiTest {

    @Test
    void functionalTest() {
        // Supplier  () - > x
        Supplier<Long> supplier = () -> System.currentTimeMillis();

        // Consumer   (x, ...) -> void
        Consumer<Long> consumer = (x) -> System.out.println(x);

        // Predicate  (x, ...) -> boolean
        Predicate<Long> predicate = (x) -> x % 2 == 0;

        // Function   (x, ...) -> y
        Function<Long, Long> function = (x) -> x + 1;
        UnaryOperator<Long> functionUnary = (x) -> x + 1;
        BiFunction<Long, Long, Long> biFunction = (x, y) -> x + y;
    }

    @Test
    void withoutOptionalTest() {
        Long x = random();
        if (x != null) {
            System.out.println(x);
        } else {
            System.out.println(2021);
        }
    }

    @Test
    void optionalTest() {
        Optional<Long> x = Optional.ofNullable(random());
        Long y = x.orElse(2021L);
        Long z = x.orElseGet(() -> 2021L);
        System.out.println(y);
        System.out.println(z);
        x.orElseThrow(() -> new RuntimeException("x cannot be null"));
    }

    @Test
    void streamTest() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        List<Integer> result = list.stream()
                                   .map(x -> x + 1)
                                   .filter(x -> x % 2 == 0)
                                   .map(x -> {
                                       List<Integer> newList = new LinkedList<>();
                                       for (int i = 0; i < x; i++) {
                                           newList.add(x);
                                       }
                                       return newList;
                                   })
                                   .flatMap(x -> x.stream())
                                   .collect(Collectors.toList());
    }

    private Long random() {
        return System.currentTimeMillis() % 2 == 0 ? System.currentTimeMillis() : null;
    }
}
