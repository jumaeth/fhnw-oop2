package guilib;

import io.github.humbleui.jwm.Event;
import io.github.humbleui.jwm.EventKey;
import io.github.humbleui.jwm.EventMouseButton;
import io.github.humbleui.jwm.Key;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Color4f;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.Point;
import io.github.humbleui.types.RRect;
import io.github.humbleui.types.Rect;

public class Checkbox extends Labeled {

    private static final float GAP = 6;
    private static final Color4f UNCHECKED_BG_COLOR = new Color4f(0xFFFFFFFF);
    private static final Color4f UNCHECKED_PRESSED_BG_COLOR = new Color4f(0x333B4759);
    private static final Color4f UNCHECKED_BORDER_COLOR = new Color4f(0xFF3B4759);
    private static final Color4f CHECKED_BG_COLOR = new Color4f(0xFF297FD5);
    private static final Color4f CHECKED_PRESSED_BG_COLOR = new Color4f(0xFF1B5289);
    private static final Color4f CHECKMARK_COLOR = new Color4f(0xFFFFFFFF);

    private boolean checked;
    private boolean pressed;

    private Runnable onCheckedChanged;

    public Checkbox(float x, float y, String text) {
        super(x, y, text);
        autoFit();
    }

    public Checkbox(Point position, String text) {
        this(position.getX(), position.getY(), text);
    }

    @Override
    protected boolean isFocusable() {
        return true;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        updateDirty(this.checked, checked);
        this.checked = checked;
    }

    public void setOnCheckedChanged(Runnable onCheckedChanged) {
        this.onCheckedChanged = onCheckedChanged;
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
        checked = !checked;
        setDirty();
        triggerIfNotNull(onCheckedChanged);
    }

    @Override
    protected void handleEvent(Event e) {
        super.handleEvent(e);
        if (isFocused() && e instanceof EventKey k
                && (k.getKey() == Key.SPACE || k.getKey() == Key.ENTER)) {
            pressed = k.isPressed();
            setDirty();
            if (!pressed) {
                checked = !checked;
                triggerIfNotNull(onCheckedChanged);
            }
        }
    }

    @Override
    protected float computeWidth() {
        return super.computeWidth() + boxSize() + GAP;
    }

    @Override
    protected Rect labelBounds() {
        return super.labelBounds().offset(boxSize() + GAP, 0);
    }

    private float boxSize() {
        return font().getMetrics().getHeight() * 0.9f;
    }

    @Override
    protected void paint(Canvas canvas) {
        super.paint(canvas);
        drawCheckbox(canvas);
    }

    private void drawCheckbox(Canvas canvas) {
        var box = box();
        if (isFocused()) {
            canvas.drawRRect((RRect) box.inflate(1), new Paint()
                    .setStroke(true)
                    .setStrokeWidth(3)
                    .setColor4f(FOCUS_COLOR));
        }
        var bgPaint = new Paint();
        var borderPaint = new Paint();
        borderPaint.setStroke(true);
        if (checked) {
            bgPaint.setColor4f(pressed
                    ? CHECKED_PRESSED_BG_COLOR
                    : CHECKED_BG_COLOR);
            borderPaint.setColor4f(bgPaint.getColor4f());
        } else {
            bgPaint.setColor4f(pressed
                    ? UNCHECKED_PRESSED_BG_COLOR
                    : UNCHECKED_BG_COLOR);
            borderPaint.setColor4f(UNCHECKED_BORDER_COLOR);
        }
        canvas.drawRRect(box, bgPaint);
        canvas.drawRRect(box, borderPaint);

        if (checked) {
            var paint = new Paint();
            paint.setStroke(true);
            paint.setStrokeWidth(0.15f * box.getWidth());
            paint.setColor4f(CHECKMARK_COLOR);
            var size = box.getWidth();
            float[] coords = {
                    box.getLeft() + 0.25f * size, box.getTop() + 0.25f * size,
                    box.getLeft() + 0.75f * size, box.getTop() + 0.75f * size,
                    box.getLeft() + 0.25f * size, box.getTop() + 0.75f * size,
                    box.getLeft() + 0.75f * size, box.getTop() + 0.25f * size
            };
            canvas.drawLines(coords, paint);
        }
    }

    private RRect box() {
        var size = boxSize();
        var orig = super.labelBounds();
        return RRect.makeXYWH(orig.getLeft(), orig.getBottom() - size,
                size, size, CORNER_RADIUS);
    }
}
