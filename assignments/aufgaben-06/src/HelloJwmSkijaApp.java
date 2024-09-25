import io.github.humbleui.jwm.*;
import io.github.humbleui.jwm.skija.*;
import io.github.humbleui.skija.*;

public class HelloJwmSkijaApp {

    public static void main(String[] args) {
        // erstelle Objekt, damit nicht alles 'static' ist:
        new HelloJwmSkijaApp().start();
    }

    private Window window;
    private boolean pressed;

    private void start() {
        // Lambda-Ausdruck für Initialisierung (Typ Runnable):
        App.start(() -> {
            window = App.makeWindow();
            // Fensterinhalt wird mit Skija erstellt:
            window.setLayer(new LayerGLSkija());
            window.setTitle("Hello, JWM & Skija!");
            window.setContentSize(800, 600);
            // Lambda-Ausdruck für Event Handling (Typ Consumer<Event>):
            window.setEventListener(e -> {
                if (e instanceof EventKey keyEvent) {
                    handleKeyEvent(keyEvent);
                } else if (e instanceof EventFrameSkija frameEvent) {
                    draw(frameEvent.getSurface().getCanvas());
                } else if (e instanceof EventWindowCloseRequest) {
                    App.terminate();
                }
            });
            window.setVisible(true);
        });
    }

    private void handleKeyEvent(EventKey e) {
        if (e.getKey() == Key.SPACE) {
            pressed = e.isPressed();
            // sichtbarer Zustand evtl. geändert → neu zeichnen
            window.requestFrame();
        }
    }

    private void draw(Canvas canvas) {
        // weisser Hintergrund
        canvas.clear(0xFFFFFFFF);

        var font = new Font(Typeface.makeDefault(), 80);
        var paint = new Paint();
        // Schriftfarbe abhängig von 'pressed'
        if (pressed) {
            paint.setColor(0xFFCC0000);
        } else {
            paint.setColor(0xFF333333);
        }
        canvas.drawString("Hello, JWM & Skija!", 50, 200, font, paint);
    }
}
