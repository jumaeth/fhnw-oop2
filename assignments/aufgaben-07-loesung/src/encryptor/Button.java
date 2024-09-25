package encryptor;

import io.github.humbleui.jwm.Event;
import io.github.humbleui.jwm.EventMouseButton;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.Typeface;
import io.github.humbleui.types.RRect;

public class Button {

    public static final Font FONT = new Font(Typeface.makeDefault(), 30);
    public static final float HEIGHT = 60;
    public static final float RADIUS = 10;
    public static final int TEXT_COLOR = 0xFFFFFFFF;
    public static final int BG_COLOR = 0xFF297FD5;

    private final float x;
    private final float y;
    private final float width;
    private final String text;

    private Runnable onAction;

    public Button(float x, float y, float width, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.text = text;
    }

    public void setOnAction(Runnable onAction) {
        this.onAction = onAction;
    }

    public void paint(Canvas canvas) {
        var rect = RRect.makeXYWH(x, y, width, HEIGHT, RADIUS);
        canvas.drawRRect(rect, new Paint().setColor(BG_COLOR));

        var textWidth = FONT.measureTextWidth(text);
        var textX = x + (width - textWidth) / 2;
        var textY = y - FONT.getMetrics().getAscent() + (HEIGHT - FONT.getMetrics().getHeight()) / 2;
        canvas.drawString(text, textX, textY, FONT, new Paint()
                .setColor(TEXT_COLOR));
    }

    public void handleEvent(Event event) {
        if (event instanceof EventMouseButton e && !e.isPressed()) {
            int mx = e.getX();
            int my = e.getY();
            if (x <= mx && mx <= x + width && y <= my && my <= y + HEIGHT) {
                onMouseClicked();
            }
        }
    }

    private void onMouseClicked() {
        if (onAction != null) {
            onAction.run();
        }
    }
}
