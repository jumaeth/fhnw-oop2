package oop2.project.guilib;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Color4f;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.Rect;

/**
 * Common superclass for all controls that include a readonly text (a "label").
 * The width of such controls can either be initialized explicitly or be computed
 * based on the label text. (The height is always determined based on the text.)
 * In the latter case, the control will continue to auto-fit whenever a property
 * that affects width changes.
 * Note: All subclass constructors that initialize the control with auto-fit
 * enabled must call {@link #autoFit()} after initialization or modification of
 * properties that affect the width.
 */
public abstract class Labeled extends Control {

    public static final TextAlignment DEFAULT_TEXT_ALIGNMENT = TextAlignment.LEFT;

    private final boolean autoFitting;
    private float width;
    private String text;
    private float fontSize = DEFAULT_FONT_SIZE;
    private TextAlignment textAlignment = DEFAULT_TEXT_ALIGNMENT;
    private Color4f textColor = DEFAULT_TEXT_COLOR;

    /**
     * Creates a control with the given text. The control will auto-fit
     * its width to the text, even if the text or some other property
     * that affects the size is changed later. Subclass constructors
     * calling this constructor should call {@link #autoFit()} after
     * completing the initialization of the fields that influence the
     * width.
     */
    protected Labeled(float x, float y, String text) {
        super(x, y);
        this.text = text;
        this.autoFitting = true;
    }

    /**
     * Creates a control with the given text and the given width. Auto-fit
     * is disabled.
     */
    protected Labeled(float x, float y, float width, String text) {
        super(x, y);
        this.width = width;
        this.text = text;
        this.autoFitting = false;
    }

    @Override
    public float getWidth() {
        return width;
    }

    /**
     * The height of a labeled control defaults to the height of the
     * label text. A subclass can override this method to account for
     * additional space needed. If so, it should also consider
     * overriding {@link #labelBounds()}.
     */
    @Override
    public float getHeight() {
        return font().getMetrics().getHeight();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        updateDirty(this.text, text);
        this.text = text;
        autoFit();
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        updateDirty(this.fontSize, fontSize);
        this.fontSize = fontSize;
        autoFit();
    }

    public TextAlignment getTextAlignment() {
        return textAlignment;
    }

    public void setTextAlignment(TextAlignment textAlignment) {
        updateDirty(this.textAlignment, textAlignment);
        this.textAlignment = textAlignment;
    }

    public Color4f getTextColor() {
        return textColor;
    }

    public void setTextColor(Color4f textColor) {
        updateDirty(this.textColor, textColor);
        this.textColor = textColor;
    }

    /**
     * Fits the width if this control is auto-fitting. Called by the
     * setters of all properties that affects the width of this control. If
     * subclasses add such properties, their setters should call this method
     * too. In addition, constructors that initialize the control with
     * auto-fit enabled should call this method after initialization.
     */
    protected void autoFit() {
        if (autoFitting) {
            var width = computeWidth();
            updateDirty(this.width, width);
            this.width = width;
        }
    }

    /**
     * Computes the width of this control, based on the label text and the font
     * size. A subclass can override this method to account for additional space
     * needed. If so, it should also consider overriding {@link #labelBounds()}.
     */
    protected float computeWidth() {
        return font().measureTextWidth(text);
    }

    /**
     * Returns the bounds of the label, corresponding to this control's x, y,
     * width, and height. As this information is used to draw the label,
     * subclasses can override this method to influence where the label is drawn.
     */
    protected Rect labelBounds() {
        return Rect.makeXYWH(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    protected void paint(Canvas canvas) {
        canvas.save();
        var labelBounds = labelBounds();
        var startX = labelBounds.getLeft();
        var font = font();
        if (getTextAlignment() != TextAlignment.LEFT) {
            var textWidth = font.measureTextWidth(text);
            switch (textAlignment) {
                case CENTER -> startX += (labelBounds.getWidth() - textWidth) / 2;
                case RIGHT -> startX += labelBounds.getWidth() - textWidth;
            }
        }
        var baseline = labelBounds.getTop() - font.getMetrics().getAscent(); // ascent is negative
        var paint = new Paint();
        paint.setColor4f(textColor);
        canvas.clipRect(labelBounds);
        canvas.drawString(text, startX, baseline, font, paint);
        canvas.restore();
    }

    protected Font font() {
        return new Font(DEFAULT_TYPEFACE, fontSize);
    }
}
