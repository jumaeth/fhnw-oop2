package wordquiz;

import io.github.humbleui.jwm.*;
import io.github.humbleui.jwm.skija.EventFrameSkija;
import io.github.humbleui.jwm.skija.LayerGLSkija;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.Typeface;
import io.github.humbleui.types.RRect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordQuiz {

    private static final ArrayList<String> WORDS = new ArrayList<>(List.of(
            "OBJEKT", "PROGRAMM", "JAVA", "NORDWEST", "HOCHSCHULE", "WINDISCH",
            "SEMESTER", "LAMBDA", "METHODE", "OPERATOR", "CODEZEILE", "INFORMATIK",
            "GURKE", "FUCHS", "VERREISEN", "MASCHINE", "TRAKTOR", "PANZER"));

    private static final int FONT_SIZE = 100;
    private static final int TILE_SIZE = 180;
    private static final int PADDING = 50;
    private static final int SPACING = 20;
    public static final int TILE_RADIUS = 10;

    public static void main(String[] args) {
        new WordQuiz().start();
    }

    private Window window;

    private final Random random = new Random();
    private final String originalWord;
    private String currentWord;
    
    private int selectedIndex = -1; // zu Beginn ist nichts ausgewählt
    private int hoveredIndex = -1;

    public WordQuiz() {
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
        // Fenster können nur durch App-Klasse erstellt werden, deshalb übergeben
        // wir Initialisierungs-Code als Lambda-Ausdruck (für Runnable-Interface):
        App.start(() -> {
            // Der Code im Lambda-Ausdruck hat Zugriff auf äussere Variablen
            window = App.makeWindow();
            window.setLayer(new LayerGLSkija());
            window.setTitle("Word Quiz");
            int width = originalWord.length() * TILE_SIZE
                    + (originalWord.length() - 1) * SPACING
                    + 2 * PADDING;
            int height = TILE_SIZE + 2 * PADDING;
            window.setContentSize(width, height);
            window.setVisible(true);
            // Um auf Events zu reagieren, braucht es nochmals ein Lambda-Ausdruck
            // (für Consumer-Interface):
            window.setEventListener(event -> {
                if (event instanceof EventWindowCloseRequest) {
                    App.terminate();
                } else if (event instanceof EventFrameSkija e) {
                    paint(e.getSurface().getCanvas());
                } else if (event instanceof EventMouseButton e) {
                    if (!e.isPressed()) { // Taste losgelassen
                        handleClick(e.getX(), e.getY());
                    }
                } else if (event instanceof EventMouseMove e) {
                    handleMove(e.getX(), e.getY());
                }
            });
        });
    }

    private void handleClick(int x, int y) {
        int clickedIndex = tileIndexAt(x, y);
        if (clickedIndex < 0) {
            return;
        }

        if (selectedIndex < 0) {
            // noch nichts markiert
            selectedIndex = clickedIndex;
        } else if (selectedIndex != clickedIndex) {
            // Buchstabe wurde vorher markiert, also tausche
            int first = Math.min(clickedIndex, selectedIndex);
            int second = Math.max(clickedIndex, selectedIndex);
            swap(first, second);
            selectedIndex = -1;
        }
        window.requestFrame(); // Zustand geändert, lasse Fenster neu zeichnen
    }

    private void handleMove(int x, int y) {
        int index = tileIndexAt(x, y);
        if (index != hoveredIndex) {
            window.requestFrame();
        }
        hoveredIndex = index;
    }

    /**
     * Gibt zurück, welches Feldchen sind bei der Position x/y befindet.
     * -1, falls keines.
     */
    private int tileIndexAt(int x, int y) {
        for (int i = 0; i < originalWord.length(); i++) {
            RRect tile = tile(i);
            if (tile.getLeft() <= x && x <= tile.getRight()
                    && tile.getTop() <= y && y <= tile.getBottom()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gibt ein abgerundetes Rechteck zurück, das dem Feldchen für den
     * Buchstaben mit dem gegebenen Index entspricht.
     */
    private RRect tile(int index) {
        float x = index * (TILE_SIZE + SPACING) + PADDING;
        return RRect.makeXYWH(x, PADDING, TILE_SIZE, TILE_SIZE, TILE_RADIUS);
    }

    private void swap(int first, int second) {
        currentWord = currentWord.substring(0, first)
                      + currentWord.charAt(second)
                      + currentWord.substring(first + 1, second)
                      + currentWord.charAt(first)
                      + currentWord.substring(second + 1);
    }

    private void paint(Canvas canvas) {
        // zuerst mit Weiss füllen
        canvas.clear(0xFFFFFFFF);

        boolean finished = currentWord.equals(originalWord);
        Font font = new Font(Typeface.makeDefault(), FONT_SIZE);
        for (int i = 0; i < currentWord.length(); i++) {
            RRect tile = tile(i);
            if (i == selectedIndex) {
                canvas.drawRRect((RRect) tile.inflate(5), new Paint().setColor(0xFFCC0000));
            }
            int tileColor;
            if (finished) {
                tileColor = 0xFF38BE75;
            } else if (i == hoveredIndex) {
                tileColor = 0xFFCCCCCC;
            } else {
                tileColor = 0xFFDDDDDD;
            }
            canvas.drawRRect(tile, new Paint().setColor(tileColor));

            String letter = currentWord.substring(i, i + 1);
            float width = font.measureTextWidth(letter);
            float x = tile.getLeft() + (tile.getWidth() - width) / 2;
            float y = tile.getTop() + 0.7f * tile.getHeight();
            int letterColor;
            if (finished) {
                letterColor = 0xFFFFFFFF;
            } else {
                letterColor = 0xFF000000;
            }
            canvas.drawString(letter, x, y, font, new Paint().setColor(letterColor));
        }
    }
}
