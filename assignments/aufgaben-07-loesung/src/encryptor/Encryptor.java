package encryptor;

import io.github.humbleui.jwm.App;
import io.github.humbleui.jwm.EventWindowCloseRequest;
import io.github.humbleui.jwm.Window;
import io.github.humbleui.jwm.skija.EventFrameSkija;
import io.github.humbleui.jwm.skija.LayerGLSkija;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.Typeface;

public class Encryptor {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 200;
    private static final int SPACING = 20;

    private static final Font MSG_FONT = new Font(Typeface.makeDefault(), (float) 60);

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
    private final float btnY = HEIGHT - SPACING - Button.HEIGHT;

    private final Button valBtn = new Button(valBtnX, btnY, btnWidth, "Shift values");
    private final Button posBtn = new Button(posBtnX, btnY, btnWidth, "Shift positions");

    public Encryptor() {
        encrypt();

        valBtn.setOnAction(() -> {
            valShift = (valShift + 1) % 26;
            encrypt();
        });

        posBtn.setOnAction(() -> {
            posShift = (posShift + 1) % original.length();
            encrypt();
        });
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
                } else {
                    valBtn.handleEvent(event);
                    posBtn.handleEvent(event);
                    window.requestFrame();
                }
            });
            window.setVisible(true);
        });
    }

    private void paint(Canvas canvas) {
        canvas.clear(0xFFFFFFFF);

        var width = MSG_FONT.measureTextWidth(encrypted);
        var x = (WIDTH - width) / 2;
        var y = SPACING - MSG_FONT.getMetrics().getAscent();
        canvas.drawString(encrypted, x, y, MSG_FONT, new Paint());

        valBtn.paint(canvas);
        posBtn.paint(canvas);
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
