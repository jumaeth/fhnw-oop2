package encryptor;

import io.github.humbleui.jwm.App;
import io.github.humbleui.jwm.EventMouseButton;
import io.github.humbleui.jwm.EventWindowCloseRequest;
import io.github.humbleui.jwm.Window;
import io.github.humbleui.jwm.skija.EventFrameSkija;
import io.github.humbleui.jwm.skija.LayerGLSkija;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.Typeface;
import io.github.humbleui.types.RRect;

public class Encryptor {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 200;
    private static final int SPACING = 20;

    private static final Font MSG_FONT = new Font(Typeface.makeDefault(), 60);

    private static final Font BTN_FONT = new Font(Typeface.makeDefault(), 30);
    private static final float BTN_HEIGHT = 60;
    private static final float BTN_RADIUS = 10;
    private static final int BTN_TEXT_COLOR = 0xFFFFFFFF;
    private static final int BTN_BG_COLOR = 0xFF297FD5;

    public static void main(String[] args) {
        new Encryptor().start();
    }

    private final String original = "TREFFEN UM ACHT UHR";
    private int valShift = 0;
    private int posShift = 0;
    private String encrypted;

    private Window window;
    private final float btnWidth = (WIDTH - 3 * SPACING) / 2f;
    private final float valBtnX = SPACING;
    private final float posBtnX = WIDTH - SPACING - btnWidth;
    private final float btnY = HEIGHT - SPACING - BTN_HEIGHT;

    public Encryptor() {
        encrypt();
    }

    private void start() {
        App.start(() -> {
            window = App.makeWindow();
            window.setLayer(new LayerGLSkija());
            window.setTitle("Encryptor");
            window.setContentSize(WIDTH, HEIGHT);
            window.setEventListener(event -> {
                if (event instanceof EventWindowCloseRequest) {
                    App.terminate();
                } else if (event instanceof EventFrameSkija e) {
                    paint(e.getSurface().getCanvas());
                } else if (event instanceof EventMouseButton e && !e.isPressed()) {
                    handleClick(e.getX(), e.getY());
                    window.requestFrame();
                }
            });
            window.setVisible(true);
        });
    }

    private void paint(Canvas canvas) {
        canvas.clear(0xFFFFFFFF);

        // message
        {
            var width = MSG_FONT.measureTextWidth(encrypted);
            var x = (WIDTH - width) / 2;
            var y = SPACING - MSG_FONT.getMetrics().getAscent();
            canvas.drawString(encrypted, x, y, MSG_FONT, new Paint());
        }

        // shift values button
        {
            var rect = RRect.makeXYWH(valBtnX, btnY, btnWidth, BTN_HEIGHT, BTN_RADIUS);
            canvas.drawRRect(rect, new Paint()
                    .setColor(BTN_BG_COLOR));

            var textWidth = BTN_FONT.measureTextWidth("Shift values");
            var textX = valBtnX + (btnWidth - textWidth) / 2;
            var textY = btnY - BTN_FONT.getMetrics().getAscent() + (BTN_HEIGHT - BTN_FONT.getMetrics().getHeight()) / 2;
            canvas.drawString("Shift values", textX, textY, BTN_FONT, new Paint()
                    .setColor(BTN_TEXT_COLOR));
        }

        // shift position button
        {
            var rect = RRect.makeXYWH(posBtnX, btnY, btnWidth, BTN_HEIGHT, BTN_RADIUS);
            canvas.drawRRect(rect, new Paint()
                    .setColor(BTN_BG_COLOR));

            var textWidth = BTN_FONT.measureTextWidth("Shift positions");
            var textX = posBtnX + (btnWidth - textWidth) / 2;
            var textY = btnY - BTN_FONT.getMetrics().getAscent() + (BTN_HEIGHT - BTN_FONT.getMetrics().getHeight()) / 2;
            canvas.drawString("Shift positions", textX, textY, BTN_FONT, new Paint()
                    .setColor(BTN_TEXT_COLOR));
        }
    }

    private void handleClick(int x, int y) {
        // check if it's the shift values button
        if (valBtnX <= x && x <= valBtnX + btnWidth && btnY <= y && y <= btnY + BTN_HEIGHT) {
            valShift = (valShift + 1) % 26;
            encrypt();
        }
        // check if it's the shift positions button
        if (x >= posBtnX && x <= posBtnX + btnWidth && btnY <= y && y <= btnY + BTN_HEIGHT) {
            posShift = (posShift + 1) % original.length();
            encrypt();
        }
    }

    private void encrypt() {
        // shift values
        var valShifted = "";
        for (int i = 0; i < original.length(); i++) {
            var c = original.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                var shifted = c + valShift;
                if (shifted > 'Z') {
                    shifted -= 26;
                }
                valShifted += (char) shifted;
            } else {
                valShifted += c;
            }
        }

        // shift positions
        encrypted = valShifted.substring(original.length() - posShift)
                    + valShifted.substring(0, original.length() - posShift);
    }
}
