package multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderProblem {

    public static void main(String[] args) throws InterruptedException {
        new OrderProblem().run();
    }

    private final AtomicInteger value = new AtomicInteger(0);

    private void run() throws InterruptedException {
        Runnable incrementer = () -> {
            for (int i = 1; i <= 10000; i++) {
                value.incrementAndGet();
            }
        };

        var t1 = new Thread(incrementer);
        var t2 = new Thread(incrementer);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(value.get());
    }
}
