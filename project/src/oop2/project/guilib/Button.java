package oop2.project.guilib;

import io.github.humbleui.jwm.*;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Color4f;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.Point;
import io.github.humbleui.types.RRect;
import io.github.humbleui.types.Rect;

public class Button extends Labeled {

    public static final Color4f DEFAULT_TEXT_COLOR = new Color4f(0xFFFFFFFF);
    public static final TextAlignment DEFAULT_TEXT_ALIGNMENT = TextAlignment.CENTER;
    public static final Color4f DEFAULT_BACKGROUND_COLOR = new Color4f(0xFF297FD5);
    public static final Color4f DEFAULT_HOVER_COLOR = new Color4f(0xFF1F5FA0);
    public static final Color4f DEFAULT_PRESSED_COLOR = new Color4f(0xFF1B5289);

    private static final Padding PADDING = new Padding(5.5f, 20, 7, 20);

    private Color4f backgroundColor = DEFAULT_BACKGROUND_COLOR;
    private Color4f hoverBackgroundColor = DEFAULT_HOVER_COLOR;
    private Color4f pressedBackgroundColor = DEFAULT_PRESSED_COLOR;

    private boolean pressed;

    private Runnable onAction;

    public Button(float x, float y, float width, String text) {
        super(x, y, width, text);
        initializeDefaults();
    }

    public Button(Point position, float width, String text) {
        this(position.getX(), position.getY(), width, text);
    }

    private void initializeDefaults() {
        setTextColor(DEFAULT_TEXT_COLOR);
        setTextAlignment(DEFAULT_TEXT_ALIGNMENT);
    }

    @Override
    public float getHeight() {
        return super.getHeight() + PADDING.verticalSum();
    }

    @Override
    protected boolean isFocusable() {
        return true;
    }

    public Color4f getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color4f backgroundColor) {
        updateDirty(this.backgroundColor, backgroundColor);
        this.backgroundColor = backgroundColor;
    }

    public Color4f getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color4f hoverBackgroundColor) {
        updateDirty(this.hoverBackgroundColor, hoverBackgroundColor);
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color4f getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color4f pressedBackgroundColor) {
        updateDirty(this.pressedBackgroundColor, pressedBackgroundColor);
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    public void setOnAction(Runnable onAction) {
        this.onAction = onAction;
    }

    @Override
    protected void onMouseEntered(EventMouseMove e) {
        super.onMouseEntered(e);
        setDirty();
    }

    @Override
    protected void onMouseExited(EventMouseMove e) {
        super.onMouseExited(e);
        setDirty();
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
        triggerIfNotNull(onAction);
    }

    @Override
    protected void handleEvent(Event e) {
        super.handleEvent(e);
        if (isFocused() && e instanceof EventKey k
                && (k.getKey() == Key.SPACE || k.getKey() == Key.ENTER)) {
            pressed = k.isPressed();
            setDirty();
            if (!pressed) {
                triggerIfNotNull(onAction);
            }
        }
    }

    @Override
    protected float computeWidth() {
        return super.computeWidth() + PADDING.horizontalSum();
    }

    @Override
    protected Rect labelBounds() {
        return Rect.makeXYWH(getX() + PADDING.getLeft(), getY() + PADDING.getTop(),
                getWidth() - PADDING.horizontalSum(), getHeight() - PADDING.verticalSum());
    }

    @Override
    protected void paint(Canvas canvas) {
        drawButton(canvas);
        super.paint(canvas);
    }

    private void drawButton(Canvas canvas) {
        var button = bounds().withRadii(CORNER_RADIUS);
        if (isFocused()) {
            canvas.drawRRect((RRect) button.inflate(1), new Paint()
                    .setStroke(true)
                    .setStrokeWidth(3)
                    .setColor4f(FOCUS_COLOR));
        }
        var paint = new Paint();
        if (pressed) {
            paint.setColor4f(pressedBackgroundColor);
        } else if (isMouseInside()) {
            paint.setColor4f(hoverBackgroundColor);
        } else {
            paint.setColor4f(backgroundColor);
        }
        canvas.drawRRect(button, paint);
    }
}
