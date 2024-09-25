package multithreading;

public class OrderProblem {

    public static void main(String[] args) throws InterruptedException {
        new OrderProblem().run();
    }

    private volatile int value = 0;

    private void run() throws InterruptedException {
        Runnable incrementer = () -> {
            for (int i = 1; i <= 10000; i++) {
                value++;
            }
        };

        var t1 = new Thread(incrementer);
        var t2 = new Thread(incrementer);
        t1.start();
        t2.start();

        System.out.println(value);
    }
}
