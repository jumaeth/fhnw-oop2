package letter.gui;

import gui.Window;

public class LetterFrequencyGui {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Window createWindow() {
        Window window = new Window("Letter Frequency", WIDTH, HEIGHT);
        window.open();
        return window;
    }
}
