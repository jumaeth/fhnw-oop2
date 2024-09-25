package multithreading;

import java.util.ArrayList;
import java.util.List;

public class FactorizationStream {

    private static final List<Long> NUMBERS = List.of(
            6428733687135L,
            2260079974487L,
            2129626076673L,
            4203871542091L,
            66657941655L,
            2994185890964L,
            2717339902199L,
            8653241755480L,
            9461135731579L,
            3334043042571L,
            7200654964407L,
            2747561899166L,
            1962521678743L,
            1365944011056L,
            6198491414655L,
            6689508176080L);

    public static void main(String[] args) {
        var start = System.currentTimeMillis();

        NUMBERS.parallelStream().forEach(num -> {
            var factors = factorize(num);
            System.out.println(num + ": " + factors);
        });

        var time = (System.currentTimeMillis() - start) / 1000.0;
        System.out.printf("\n%.1f seconds\n", time);
    }

    private static List<Long> factorize(long num) {
        var factors = new ArrayList<Long>();
        long factor = 2;
        while (factor <= num) {
            while (num % factor == 0) {
                factors.add(factor);
                num /= factor;
                if (num == 1) {
                    return factors;
                }
            }
            factor++;
        }
        throw new AssertionError();
    }
}
