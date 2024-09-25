package guilib;

import io.github.humbleui.jwm.Event;
import io.github.humbleui.jwm.EventKey;
import io.github.humbleui.jwm.EventMouseButton;
import io.github.humbleui.jwm.Key;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Color4f;
import io.github.humbleui.types.Point;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.RRect;
import io.github.humbleui.types.Rect;

public class RadioButton extends Labeled {
    private static final float GAP = 6;
    private static final float CORNER_RADIUS = 25;
    private static final Color4f UNSELECTED_BG_COLOR = new Color4f(0xFFFFFFFF);
    private static final Color4f UNSELECTED_PRESSED_BG_COLOR = new Color4f(0x333B4759);
    private static final Color4f UNSELECTED_BORDER_COLOR = new Color4f(0xFF3B4759);
    private static final Color4f SELECTED_BG_COLOR = new Color4f(0xFF297FD5);
    private static final Color4f SELECTED_PRESSED_BG_COLOR = new Color4f(0xFF1B5289);

    private boolean selected;
    private boolean pressed;

    private RadioGroup group;

    private Runnable onCheckedChanged;

    public RadioButton(float x, float y, String text) {
        super(x, y, text);
        autoFit();
    }

    public RadioButton(Point position, String text) {
        this(position.getX(), position.getY(), text);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setOnSelectedChanged(Runnable onCheckedChanged) {
        this.onCheckedChanged = onCheckedChanged;
    }

    public void setGroup(RadioGroup group) {
        this.group = group;
        group.addRadioButton(this);
    }

    @Override
    protected boolean isFocusable() {
        return true;
    }

    @Override
    protected void onMousePressed(EventMouseButton e) {
        super.onMousePressed(e);
        pressed = true;
        setDirty();
    }

    @Override
    protected void onMouseReleased(EventMouseButton e) {
        super.onMouseReleased(e);
        pressed = false;
        setDirty();
    }

    @Override
    protected void onMouseClicked(EventMouseButton e) {
        super.onMouseClicked(e);
        if (!group.currentSelected(this)) {
            group.removeSelected();
            this.selected = true;
            setDirty();
            triggerIfNotNull(onCheckedChanged);
        }
    }

    @Override
    protected void handleEvent(Event e) {
        super.handleEvent(e);
        if (isFocused() && e instanceof EventKey k
                && (k.getKey() == Key.SPACE || k.getKey() == Key.ENTER)) {
            pressed = k.isPressed();
            setDirty();
            if (!pressed) {
                selected = !selected;
                triggerIfNotNull(onCheckedChanged);
            }
        }
    }

    @Override
    protected float computeWidth() {
        return super.computeWidth() + circleSize() + GAP;
    }

    @Override
    protected Rect labelBounds() {
        return super.labelBounds().offset(circleSize() + GAP, 0);
    }

    @Override
    protected void paint(Canvas canvas) {
        super.paint(canvas);
        drawRadioButton(canvas);
    }

    void setSelected(boolean selected) {
        this.selected = selected;
    }

    private float circleSize() {
        return font().getMetrics().getHeight() * 0.9f;
    }

    private void drawRadioButton(Canvas canvas) {
        var circle = circle();
        if (isFocused()) {
            canvas.drawRRect((RRect) circle.inflate(1), new Paint()
                    .setStroke(true)
                    .setStrokeWidth(3)
                    .setColor4f(FOCUS_COLOR));
        }
        var bgPaint = new Paint();
        var borderPaint = new Paint();
        borderPaint.setStroke(true);
        if (selected) {
            bgPaint.setColor4f(pressed
                    ? SELECTED_PRESSED_BG_COLOR
                    : SELECTED_BG_COLOR);
            borderPaint.setColor4f(bgPaint.getColor4f());
        } else {
            bgPaint.setColor4f(pressed
                    ? UNSELECTED_PRESSED_BG_COLOR
                    : UNSELECTED_BG_COLOR);
            borderPaint.setColor4f(UNSELECTED_BORDER_COLOR);
        }
        canvas.drawRRect(circle, bgPaint);
        canvas.drawRRect(circle, borderPaint);

        if (selected) {
            canvas.drawRRect((RRect) circle.inflate(-4), new Paint()
                    .setStroke(false)
                    .setColor4f(UNSELECTED_BG_COLOR));
        }
    }

    private RRect circle() {
        var size = circleSize();
        var orig = super.labelBounds();
        return RRect.makeXYWH(orig.getLeft(), orig.getBottom() - size,
                size, size, CORNER_RADIUS);
    }
}
