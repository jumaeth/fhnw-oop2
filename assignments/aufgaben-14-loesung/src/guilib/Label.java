package guilib;

import io.github.humbleui.types.Point;

public class Label extends Labeled {

    public Label(float x, float y, String text) {
        super(x, y, text);
        autoFit();
    }

    public Label(Point position, String text) {
        this(position.getX(), position.getY(), text);
    }

    public Label(float x, float y, float width, String text) {
        super(x, y, width, text);
    }

    public Label(Point position, float width, String text) {
        this(position.getX(), position.getY(), width, text);
    }
}
