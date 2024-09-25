package letter.gui;

import gui.Color;
import gui.Window;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class Drawer {

    private final static int MAX_WIDTH = 680;
    private final Window window;
    private boolean hitMax = false;

    public Drawer(Window window) {
        this.window = window;
    }

    public void drawStatistics(int[] arr, Path filename) {
        drawAlphabet();
        drawHistogram(arr);
        drawFolder(filename);
    }

    private void drawAlphabet() {
        window.setBold(true);
        window.setColor(Color.parseHexCode("#000000"));
        int canvasOffset = 20;
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            window.drawString(String.valueOf(alphabet).toUpperCase(), 15, canvasOffset);
            canvasOffset += 20;
        }
        window.setBold(false);
    }

    private void drawHistogram(int[] countArr) {
        IntSummaryStatistics stat = Arrays.stream(countArr).summaryStatistics();
        int max = stat.getMax();
        int min = stat.getMin();
        int range = max - min;
        int yOffset = 10;
        for (int count : countArr) {
            double scale;
            if (hitMax) {
                scale = count / (double) range * MAX_WIDTH;
            } else {
                scale = count / (double) 10000 * MAX_WIDTH;
                if (scale == MAX_WIDTH) {
                    hitMax = true;
                }
            }
            window.setColor(Color.parseHexCode("#287ed4"));
            window.fillRect(40, yOffset, scale, 15);
            window.setColor(Color.parseHexCode("#8A8A8A"));
            window.drawString(String.valueOf(count), 45 + scale, yOffset + 13);
            yOffset += 20;
        }
    }

    private void drawFolder(Path filename) {
        window.setColor(Color.parseHexCode("#8A8A8A"));
        window.drawString(filename.getFileName().toString(), 15, 550);
    }
}
