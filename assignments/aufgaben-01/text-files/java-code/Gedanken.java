import static ch.trick17.turtle4j.TurtleGraphics.*;

public class Gedanken {
    public static void main(String[] args) {
        setSpeed(1000);
        setPenWidth(2);

        kreis(10);

        penUp();
        right(60);
        forward(40);
        left(60);
        penDown();

        kreis(20);

        penUp();
        right(30);
        forward(150);
        left(30);
        penDown();

        for (int i = 1; i <= 7; i++) {
            halbkreis(25);
            left(180 - 360.0 / 7);
        }
    }

    public static void halbkreis(double radius) {
        for (int i = 1; i <= 180; i++) {
            forward(radius * 2 * 3.1415 / 360);
            right(1);
        }
    }

    public static void kreis(double radius) {
        halbkreis(radius);
        halbkreis(radius);
    }
}