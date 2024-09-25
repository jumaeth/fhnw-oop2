package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FactorizationThreadPool {

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

    private static final int NUM_THREADS = 8;

    public static void main(String[] args) throws InterruptedException {
        var start = System.currentTimeMillis();

        var threadPool = Executors.newFixedThreadPool(NUM_THREADS);
        for (var num : NUMBERS) {
            threadPool.submit(() -> {
                var factors = factorize(num);
                System.out.println(num + ": " + factors);
            });
        }

        threadPool.shutdown();
        while (!threadPool.awaitTermination(1, TimeUnit.SECONDS)) {
            // repeat
        }
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
