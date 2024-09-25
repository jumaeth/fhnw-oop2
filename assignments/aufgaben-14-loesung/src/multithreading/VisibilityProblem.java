package multithreading;

public class VisibilityProblem {

    public static void main(String[] args) throws InterruptedException {
        new VisibilityProblem().run();
    }

    private volatile boolean done = false; // !!!

    private void run() throws InterruptedException {
        var reader = new Thread(() -> {
            while(!done) {
                // nothin'...
            }
            System.out.println("reader is done");
        });
        reader.start();

        // wait a little...
        Thread.sleep(1000);

        done = true;
        System.out.println("writer is done");
    }
}
