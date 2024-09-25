package guilib;

import io.github.humbleui.jwm.*;
import io.github.humbleui.jwm.skija.EventFrameSkija;
import io.github.humbleui.jwm.skija.LayerGLSkija;

public abstract class Application {

    public static final int BACKGROUND_COLOR = 0xFFF9F9FB;

    private Window window;
    private float scale;
    private Group content;

    public Window getWindow() {
        return window;
    }

    protected final void start(String title, float width, float height) {
        App.start(() -> {
            window = App.makeWindow();
            // for high-res displays, determine scaling factor
            scale = window.getScreen().getScale();

            content = createContent();

            window.setLayer(new LayerGLSkija());
            window.setTitle(title);
            // make window larger on high-res displays
            window.setContentSize((int) (width * scale), (int) (height * scale));
            window.setEventListener(this::handleEvent);
            window.setVisible(true);
        });
    }

    protected abstract Group createContent();

    protected void handleEvent(Event e) {
        if (e instanceof EventWindowCloseRequest) {
            App.terminate();
        } else if (e instanceof EventFrameSkija frame) {
            var canvas = frame.getSurface().getCanvas();
            canvas.save();
            // scale canvas up, so that coordinates used in paint()
            // methods are automatically converted to larger size
            canvas.scale(scale, scale);
            canvas.clear(BACKGROUND_COLOR);
            content.paint(canvas);
            canvas.restore();
        } else {
            e = scaleMouseCoordinates(e);
            content.handleEvent(e);
            if (content.clearDirty()) {
                window.requestFrame();
            }
        }
    }

    /**
     * Scales the coordinates in the given mouse event down, so that larger size
     * window coordinates are converted back to smaller size. Non-mouse events
     * are unaffected.
     */
    private Event scaleMouseCoordinates(Event e) {
        if (e instanceof EventMouseButton mb) {
            return new EventMouseButton(mb.getButton()._mask, mb.isPressed(),
                    (int) (mb.getX() / scale), (int) (mb.getY() / scale), mb.getModifiers());
        } else if (e instanceof EventMouseMove mm) {
            return new EventMouseMove((int) (mm.getX() / scale), (int) (mm.getY() / scale),
                    (int) (mm.getMovementX() / scale), (int) (mm.getMovementY() / scale),
                    mm._buttons, mm._modifiers);
        } else if (e instanceof EventMouseScroll ms) {
            return new EventMouseScroll(ms.getDeltaX() / scale, ms.getDeltaY() / scale,
                    ms.getDeltaChars(), ms.getDeltaLines(), ms.getDeltaPages(),
                    (int) (ms.getX() / scale), (int) (ms.getY() / scale), ms._modifiers);
        } else {
            return e;
        }
    }

    /**
     * Runs the given action on the UI thread and performs dirty checking.
     */
    protected final void runOnUIThread(Runnable action) {
        App.runOnUIThread(() -> {
            action.run();
            if (content.clearDirty()) {
                window.requestFrame();
            }
        });
    }
}
