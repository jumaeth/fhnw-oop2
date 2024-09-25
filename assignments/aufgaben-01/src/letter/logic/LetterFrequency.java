package letter.logic;

import gui.Window;
import letter.gui.Drawer;

import java.nio.file.Path;
import java.util.List;

public class LetterFrequency {

    private static final int ASCII_OFFSET = 97;
    private static final int REFRESH_TIME = 2;

    private final int[] letterAmount = new int[26];

    private final Window window;
    private final Drawer drawer;

    public LetterFrequency(Window window, Drawer drawer) {
        this.window = window;
        this.drawer = drawer;
    }

    public void countLetters(List<String> text, Path file) {
        for (String s : text) {
            for (int index = 0; index < s.length(); index++) {
                for(char alphabet = 'a'; alphabet <='z'; alphabet++ ) {
                    if (s.charAt(index) == alphabet) {
                        letterAmount[(int)alphabet - ASCII_OFFSET] += 1;
                        drawer.drawStatistics(letterAmount, file);
                        window.refreshAndClear(REFRESH_TIME);
                        break;
                    }
                }
            }
        }
    }

    public int[] getLetterAmount() {
        return letterAmount;
    }
}
