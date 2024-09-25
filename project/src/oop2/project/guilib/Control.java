package oop2.project.guilib;

import io.github.humbleui.jwm.Event;
import io.github.humbleui.jwm.EventMouseButton;
import io.github.humbleui.jwm.EventMouseMove;
import io.github.humbleui.skija.Color4f;
import io.github.humbleui.skija.Typeface;
import io.github.humbleui.types.Rect;

public abstract class Control extends Node {

    public static final Color4f DEFAULT_TEXT_COLOR = new Color4f(0xFFF3EDE6);
    public static final Typeface DEFAULT_TYPEFACE = Typeface.makeDefault();
    public static final float DEFAULT_FONT_SIZE = 16;

    protected static final float CORNER_RADIUS = 5;
    protected static final Color4f FOCUS_COLOR = new Color4f(0xFFA9CCEE);

    private float x;
    private float y;

    private boolean focused = false;
    private boolean mouseInside = false;
    private boolean mousePressed = false;

    protected Control(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        updateDirty(this.x, x);
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        updateDirty(this.y, y);
        this.y = y;
    }

    public abstract float getWidth();

    public abstract float getHeight();

    protected boolean isFocusable() {
        return false;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        updateDirty(this.focused, focused);
        this.focused = focused;
    }

    @Override
    protected void handleEvent(Event e) {
        if (e instanceof EventMouseButton mb) {
            if (isFocusable()) {
                setFocused(isInside(mb.getX(), mb.getY()));
            }
            if (isInside(mb.getX(), mb.getY())) {
                if (mb.isPressed()) {
                    onMousePressed(mb);
                    mousePressed = true;
                } else {
                    onMouseReleased(mb);
                    if (mousePressed) {
                        onMouseClicked(mb);
                    }
                    mousePressed = false;
                }
            }
        } else if (e instanceof EventMouseMove mm) {
            if (!mouseInside && isInside(mm.getX(), mm.getY())) {
                onMouseEntered(mm);
                mouseInside = true;
            } else if (mouseInside && !isInside(mm.getX(), mm.getY())) {
                onMouseExited(mm);
                mouseInside = false;
                mousePressed = false;
            }
        }
    }

    protected boolean isInside(int x, int y) {
        return this.x <= x && x <= this.x + getWidth()
                && this.y <= y && y <= this.y + getHeight();
    }

    protected boolean isMouseInside() {
        return mouseInside;
    }

    protected void onMousePressed(EventMouseButton e) {
    }

    protected void onMouseReleased(EventMouseButton e) {
    }

    protected void onMouseClicked(EventMouseButton e) {
    }

    protected void onMouseEntered(EventMouseMove e) {
    }

    protected void onMouseExited(EventMouseMove e) {
    }

    protected static void triggerIfNotNull(Runnable handler) {
        if (handler != null) {
            handler.run();
        }
    }

    protected Rect bounds() {
        return Rect.makeXYWH(getX(), getY(), getWidth(), getHeight());
    }
}
