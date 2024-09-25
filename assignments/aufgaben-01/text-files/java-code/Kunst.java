import static ch.trick17.turtle4j.TurtleGraphics.*;

public class Kunst {

    public static void main(String[] args) {
        setSpeed(20000);
        setPenWidth(2);
        for (int i = 0; i < 550; i++) {
            setPenColor("hsl(" + 1.5 * i + ",100%,100%)");
            forward(50 + 0.5 * i);
            right(118);
        }
    }
}
