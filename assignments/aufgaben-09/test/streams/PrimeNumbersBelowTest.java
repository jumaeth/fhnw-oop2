package streams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

@Timeout(value = 5, threadMode = SEPARATE_THREAD)
public class PrimeNumbersBelowTest {

    @Test
    void below2() {
        var primes = StreamTasks2.primeNumbersBelow(2);
        assertEquals(List.of(), primes);
    }

    @Test
    void below10() {
        var primes = StreamTasks2.primeNumbersBelow(10);
        assertEquals(List.of(2, 3, 5, 7), primes);
    }

    @Test
    void below20() {
        var primes = StreamTasks2.primeNumbersBelow(20);
        assertEquals(List.of(2, 3, 5, 7, 11, 13, 17, 19), primes);
    }

    @Test
    void below29() {
        var primes = StreamTasks2.primeNumbersBelow(29);
        assertEquals(List.of(2, 3, 5, 7, 11, 13, 17, 19, 23), primes);
    }
}
