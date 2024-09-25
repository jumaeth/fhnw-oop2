package encryptor2;

import guilib.Application;
import guilib.Button;
import guilib.Group;
import guilib.Label;

import static guilib.Position.below;
import static guilib.Position.rightOf;
import static guilib.TextAlignment.CENTER;

public class Encryptor extends Application {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 120;
    private static final int SPACING = 15;

    public static void main(String[] args) {
        new Encryptor().start("Encryptor", WIDTH, HEIGHT);
    }

    private final String original = "TREFFEN UM ACHT UHR";
    private int valShift = 0;
    private int posShift = 0;

    private Label text;

    @Override
    protected Group createContent() {
        text = new Label(SPACING, SPACING, WIDTH - 2 * SPACING, original);
        text.setFontSize(30);
        text.setTextAlignment(CENTER);

        var btnWidth = WIDTH - 3 * SPACING;
        Button valBtn = new Button(below(text).offset(0, SPACING), btnWidth / 2f, "Shift values");
        valBtn.setOnAction(() -> {
            valShift = (valShift + 1) % 26;
            encrypt();
        });

        var posBtn = new Button(rightOf(valBtn).offset(SPACING, 0), btnWidth / 2f, "Shift positions");
        posBtn.setOnAction(() -> {
            posShift = (posShift + 1) % original.length();
            encrypt();
        });

        return new Group(text, valBtn, posBtn);
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
        var encrypted = valShifted.substring(original.length() - posShift)
                        + valShifted.substring(0, original.length() - posShift);
        text.setText(encrypted);
    }
}
