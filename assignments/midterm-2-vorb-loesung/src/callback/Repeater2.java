package callback;

import java.util.function.Consumer;

public class Repeater2 {
    private final Consumer<Integer> iteration;

    public Repeater2(Consumer<Integer> iteration) {
        this.iteration = iteration;
    }

    public void repeat(int times) {
        for (int i = 0; i < times; i++) {
            iteration.accept(i);
        }
    }
}
