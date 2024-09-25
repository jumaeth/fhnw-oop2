package callback;

public class Repeater1 {
    private final Iteration iteration;

    public Repeater1(Iteration iteration) {
        this.iteration = iteration;
    }

    public void repeat(int times) {
        for (int i = 0; i < times; i++) {
            iteration.perform(i);
        }
    }
}
