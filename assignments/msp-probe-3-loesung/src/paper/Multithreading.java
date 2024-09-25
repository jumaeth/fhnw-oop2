package paper;

import java.util.concurrent.atomic.AtomicInteger;

public class Multithreading {

    public static class A {
        public static void main(String[] args) {
            new A().run();
        }

        void run() {
            new Thread(() -> {
                System.out.println("Hello 1");
                System.out.println("Hello 2");
            }).start();
            System.out.println("Bye 1");
            System.out.println("Bye 2");
        }
    }

    public static class B {
        public static void main(String[] args) {
            new B().run();
        }

        int count = 0;
        boolean done = false;
        void run() {
            var t = new Thread(() -> {
                count++;
                count++;
                done = true;
            });
            t.start();
            while (!done) { /* wait */ }
            System.out.println(count);
        }
    }

    public static class C {
        public static void main(String[] args) throws InterruptedException {
            new C().run();
        }

        volatile int count = 0;
        void run() throws InterruptedException {
            var t1 = new Thread(() -> {
                count++;
            });
            t1.start();
            var t2 = new Thread(() -> {
                count++;
            });
            t2.start();
            t1.join();
            t2.join();
            System.out.println(count);
        }
    }
}
