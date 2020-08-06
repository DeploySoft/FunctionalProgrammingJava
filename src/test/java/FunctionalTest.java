import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FunctionalTest {

    Function<String, String> firstClassFunction = (name) -> String.format("Hello %s from FP", name);

    Predicate<String> anonymousFunction = s -> s.startsWith("A");

    Function<String[], String> pureFunction = (names) -> {
        String result = Arrays.stream(names)
                .filter(anonymousFunction)
                .findFirst()
                .orElse("Chucky");
        return "Hello " + result;
    };

    Function<Integer, Integer> recursiveFunction;

    Function<Function<String, String>, String> higherOrderFunction = (function) -> function.apply("Andres").toUpperCase();

    @Before
    public void init() {
        recursiveFunction = num -> {
            if (num >= 1) {
                return recursiveFunction.apply(num - 1) + num;
            }
            return num;
        };
    }

    @Test
    public void firstClassFunction() {
        assertEquals("Hello Andres from FP", firstClassFunction.apply("Andres"));
    }

    @Test
    public void pureFunction() {
        assertEquals("Hello Andres", pureFunction.apply(new String[]{"Andres", "Juan", "Pedro"}));
    }

    @Test
    public void recursiveFunction() {
        assertEquals(new Integer(6), recursiveFunction.apply(3));
    }

    @Test
    public void higherOrderFunction() {
        assertEquals("HELLO ANDRES FROM FP", higherOrderFunction.apply(firstClassFunction));
    }
}
