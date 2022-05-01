package pl.org.akai.projectreactor;

import org.junit.jupiter.api.Test;
import reactor.function.TupleUtils;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

class TuplesTest {

    @Test
    void tuples() {
        Tuple3<Integer, Integer, String> tuples = Tuples.of(1, 2, "str");

        String a = myFunction(tuples.getT1(), tuples.getT2(), tuples.getT3());
        System.out.println(a);


        String b = TupleUtils.function(this::myFunction).apply(tuples);
        System.out.println(b);
    }

    private String myFunction(Integer x, Integer y, String z) {
        return String.format("Result: %s%s", x * y, z);
    }
}
