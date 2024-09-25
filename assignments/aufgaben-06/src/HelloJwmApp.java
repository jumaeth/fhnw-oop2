import io.github.humbleui.jwm.*;

public class HelloJwmApp {

    public static void main(String[] args) {
        // Lambda-Ausdruck für Initialisierung (Typ Runnable):
        App.start(() -> {
            var window = App.makeWindow();
            window.setTitle("Hello, JWM & Skija!");
            window.setContentSize(800, 600);
            // Lambda-Ausdruck für Event Handling (Typ Consumer<Event>):
            window.setEventListener(e -> {
                if (e instanceof EventWindowCloseRequest) {
                    App.terminate();
                }
            });
            window.setVisible(true);
        });
    }
}
