package wordquiz;

import io.github.humbleui.jwm.*;
import io.github.humbleui.jwm.skija.EventFrameSkija;
import io.github.humbleui.jwm.skija.LayerGLSkija;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.Typeface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class WordQuizBasic {

    private static final ArrayList<String> WORDS = new ArrayList<>(List.of(
            "OBJEKT", "PROGRAMM", "JAVA", "NORDWEST", "HOCHSCHULE", "WINDISCH",
            "SEMESTER", "LAMBDA", "METHODE", "OPERATOR", "CODEZEILE", "INFORMATIK",
            "GURKE", "FUCHS", "VERREISEN", "MASCHINE", "TRAKTOR", "PANZER"));
    private static final int CHAR_SIZE = 200;

    public static void main(String[] args) {
        new WordQuizBasic().start();
    }

    private final Random random = new Random();
    private final String originalWord;
    private String currentWord;
    private int selectedIndex = -1; // zu Beginn ist nichts ausgewählt

    private Window window;

    public WordQuizBasic() {
        originalWord = WORDS.get(random.nextInt(WORDS.size()));
        currentWord = scramble(originalWord);
    }

    private String scramble(String word) {
        String rest = word;
        String scrambled = "";
        while (!rest.isEmpty()) {
            int i = random.nextInt(rest.length());
            scrambled += rest.charAt(i);
            rest = rest.substring(0, i) + rest.substring(i + 1);
        }
        return scrambled;
    }

    private void start() {
        App.start(new Initializer());
    }

    class Initializer implements Runnable {
        public void run() {
            window = App.makeWindow();
            window.setTitle("Word Quiz");
            window.setContentSize(CHAR_SIZE * currentWord.length(), CHAR_SIZE);
            window.setLayer(new LayerGLSkija());
            window.setEventListener(new EventHandler());
            window.setVisible(true);
        }
    }

    class EventHandler implements Consumer<Event> {
        public void accept(Event event) {
            if (event instanceof EventWindowCloseRequest) {
                App.terminate();
            } else if (event instanceof EventFrameSkija e) {
                paint(e.getSurface().getCanvas());
            } else if (event instanceof EventMouseButton e) {
                if (!e.isPressed()) { // Taste losgelassen
                    handleClick(e.getX(), e.getY());
                }
            }
        }
    }

    private void paint(Canvas canvas) {
        // zuerst mit Weiss füllen
        canvas.clear(0xFFFFFFFF);

        Font font = new Font(Typeface.makeDefault(), 120);
        int baseline = CHAR_SIZE * 7 / 10;
        for (int i = 0; i < currentWord.length(); i++) {
            var letter = "" + currentWord.charAt(i);
            // x-Position plus/minus zentriert (für breite Buchstaben)
            var x = i * CHAR_SIZE + CHAR_SIZE / 3;
            // wähle Farbe, je nachdem ob Buchstabe ausgewählt und Rätsel gelöst
            int color;
            if (i == selectedIndex) {
                color = 0xFFCC0000;
            } else if (currentWord.equals(originalWord)) {
                color = 0xFF38BE75;
            } else {
                color = 0xFF000000;
            }
            Paint paint = new Paint().setColor(color);
            canvas.drawString(letter, x, baseline, font, paint);
        }
    }

    private void handleClick(int x, int y) {
        // finde heraus, welcher Buchstabe gedrückt wurde
        int clickedIndex = x / CHAR_SIZE;
        if (selectedIndex < 0) {
            // noch nichts markiert
            selectedIndex = clickedIndex;
        } else if (selectedIndex != clickedIndex) {
            // Buchstabe wurde vorher markiert, also tausche
            int first = Math.min(selectedIndex, clickedIndex);
            int second = Math.max(selectedIndex, clickedIndex);
            swap(first, second);
            selectedIndex = -1;
        }
        window.requestFrame(); // Zustand geändert, lasse Fenster neu zeichnen
    }

    private void swap(int first, int second) {
        currentWord = currentWord.substring(0, first)
                      + currentWord.charAt(second)
                      + currentWord.substring(first + 1, second)
                      + currentWord.charAt(first)
                      + currentWord.substring(second + 1);
    }
}
