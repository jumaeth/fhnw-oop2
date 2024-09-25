package oop2.project.guilib;

import io.github.humbleui.jwm.Event;
import io.github.humbleui.jwm.EventKey;
import io.github.humbleui.jwm.EventMouseButton;
import io.github.humbleui.jwm.EventTextInput;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Color4f;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.Point;
import io.github.humbleui.types.RRect;
import io.github.humbleui.types.Rect;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class TextField extends Control {

    public static final Color4f DEFAULT_BORDER_COLOR = new Color4f(0xFF3B4759);
    public static final Padding PADDING = new Padding(5);

    public static final Color4f BACKGROUND_COLOR = new Color4f(0xFFFFFFFF);
    public static final Color4f CURSOR_COLOR = new Color4f(0xFFB2BCCB);

    private String text = "";
    private int cursorPosition = 0;

    private float fontSize = DEFAULT_FONT_SIZE;
    private Color4f textColor = DEFAULT_TEXT_COLOR;
    private Color4f borderColor = DEFAULT_BORDER_COLOR;
    private float width;

    private Runnable onTextChanged;

    public TextField(float x, float y, float width) {
        super(x, y);
        this.width = width;
    }

    public TextField(Point position, float width) {
        this(position.getX(), position.getY(), width);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        updateDirty(this.text, text);
        this.text = text;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public void setCursorPosition(int cursorPosition) {
        updateDirty(this.cursorPosition, cursorPosition);
        this.cursorPosition = cursorPosition;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        updateDirty(this.fontSize, fontSize);
        this.fontSize = fontSize;
    }

    public Color4f getTextColor() {
        return textColor;
    }

    public void setTextColor(Color4f textColor) {
        updateDirty(this.textColor, textColor);
        this.textColor = textColor;
    }

    public Color4f getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color4f borderColor) {
        updateDirty(this.borderColor, borderColor);
        this.borderColor = borderColor;
    }

    @Override
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        updateDirty(this.width, width);
        this.width = width;
    }

    @Override
    public float getHeight() {
        return font().getMetrics().getHeight() + PADDING.verticalSum();
    }

    @Override
    protected boolean isFocusable() {
        return true;
    }

    public void setOnTextChanged(Runnable onTextChanged) {
        this.onTextChanged = onTextChanged;
    }

    @Override
    protected void handleEvent(Event e) {
        super.handleEvent(e);
        if (e instanceof EventMouseButton mb) {
            placeCursor(mb.getX());
        } else if (isFocused() && e instanceof EventTextInput ti) {
            var printable = ti.getText().replaceAll("\\p{C}", ""); // non-printable characters
            if (!printable.isEmpty()) {
                setText(text.substring(0, cursorPosition) + printable + text.substring(cursorPosition));
                cursorPosition += printable.length();
                triggerIfNotNull(onTextChanged);
            }
        } else if (isFocused() && e instanceof EventKey k) {
            if (k.isPressed()) {
                switch (k.getKey()) {
                    case LEFT -> setCursorPosition(max(0, cursorPosition - 1));
                    case RIGHT -> setCursorPosition(min(text.length(), cursorPosition + 1));
                    case BACKSPACE -> {
                        if (cursorPosition > 0) {
                            setText(text.substring(0, cursorPosition - 1) + text.substring(cursorPosition));
                            cursorPosition--;
                            triggerIfNotNull(onTextChanged);
                        }
                    }
                    case DELETE -> {
                        if (cursorPosition < text.length()) {
                            setText(text.substring(0, cursorPosition) + text.substring(cursorPosition + 1));
                            triggerIfNotNull(onTextChanged);
                        }
                    }
                }
            }
        }
    }

    private void placeCursor(double x) {
        x -= textBounds().getLeft();
        var lastWidth = 0.0;
        for (int i = 0; i < text.length(); i++) {
            var width = font().measureTextWidth(text.substring(0, i + 1));
            var middle = (lastWidth + width) / 2;
            if (x < middle) {
                setCursorPosition(i);
                return;
            }
            lastWidth = width;
        }
        setCursorPosition(text.length());
    }

    @Override
    protected void paint(Canvas canvas) {
        drawField(canvas);
        drawText(canvas);
        if (isFocused()) {
            drawCursor(canvas);
        }
    }

    private void drawField(Canvas canvas) {
        var field = bounds().withRadii(CORNER_RADIUS);
        canvas.drawRRect(field, new Paint().setColor4f(BACKGROUND_COLOR));

        if (isFocused()) {
            canvas.drawRRect((RRect) field.inflate(1), new Paint()
                    .setStroke(true)
                    .setStrokeWidth(3)
                    .setColor4f(FOCUS_COLOR));
        }

        canvas.drawRRect(field, new Paint()
                .setStroke(true)
                .setColor4f(borderColor));
    }

    private void drawText(Canvas canvas) {
        var textBounds = textBounds();
        var startX = textBounds.getLeft();
        var baseline = textBounds.getTop() - font().getMetrics().getAscent(); // ascent is negative
        var paint = new Paint().setColor4f(textColor);
        canvas.save();
        canvas.clipRect(textBounds);
        canvas.drawString(text, startX, baseline, font(), paint);
        canvas.restore();
    }

    private void drawCursor(Canvas canvas) {
        var textBounds = textBounds();
        var beforeCursor = text.substring(0, cursorPosition);
        var x = textBounds.getLeft() + font().measureTextWidth(beforeCursor);
        var paint = new Paint().setColor4f(CURSOR_COLOR);
        canvas.save();
        canvas.clipRect(textBounds.withLeft(bounds().getLeft())); // ensure cursor is shown on the left
        canvas.drawLine(x, textBounds.getTop(), x, textBounds.getBottom(), paint);
        canvas.restore();
    }

    private Font font() {
        return new Font(DEFAULT_TYPEFACE, fontSize);
    }

    protected Rect textBounds() {
        return Rect.makeXYWH(getX() + PADDING.getLeft(), getY() + PADDING.getTop(),
                getWidth() - PADDING.horizontalSum(), getHeight() - PADDING.verticalSum());
    }
}
