package calculator;

import guilib.*;
import io.github.humbleui.skija.Color4f;
import io.github.humbleui.skija.Font;

import static calculator.Operator.*;
import static guilib.Position.below;
import static guilib.Position.rightOf;

public class CalculatorApp extends Application {

    private static final int WIDTH = 320;
    private static final int HEIGHT = 346;
    private static final int SPACING = 10;
    private static final float BUTTON_WIDTH = (WIDTH - 5 * SPACING) / 4.0f;
    private static final int MAX_DISPLAY_FONT_SIZE = 60;
    private static final int BUTTON_FONT_SIZE = 20;

    public static void main(String[] args) {
        new CalculatorApp().start("Calculator", WIDTH, HEIGHT);
    }

    private final Calculator calc = new Calculator();
    private Label display;

    @Override
    protected Group createContent() {
        display = new Label(SPACING, SPACING, WIDTH - 2 * SPACING, calc.getDisplay());
        display.setTextAlignment(TextAlignment.RIGHT);
        display.setFontSize(MAX_DISPLAY_FONT_SIZE);
        calc.setOnDisplayChanged(this::updateDisplay);

        var clear = new Button(below(display).offset(0, SPACING), BUTTON_WIDTH, "C");
        clear.setFontSize(BUTTON_FONT_SIZE);
        clear.setOnAction(calc::clear);
        var clearEntry = new Button(rightOf(clear).offset(SPACING, 0), BUTTON_WIDTH, "CE");
        clearEntry.setFontSize(BUTTON_FONT_SIZE);
        clearEntry.setOnAction(calc::clearEntry);
        var backspace = new Button(rightOf(clearEntry).offset(SPACING, 0), BUTTON_WIDTH, "←");
        backspace.setFontSize(BUTTON_FONT_SIZE);
        backspace.setOnAction(calc::backspace);
        var dividedBy = new Button(rightOf(backspace).offset(SPACING, 0), BUTTON_WIDTH, "÷");
        dividedBy.setFontSize(BUTTON_FONT_SIZE);
        dividedBy.setOnAction(() -> calc.operator(DIVIDED_BY));

        var digit7 = new Button(below(clear).offset(0, SPACING), BUTTON_WIDTH, "7");
        digit7.setFontSize(BUTTON_FONT_SIZE);
        digit7.setOnAction(() -> calc.digit(7));
        var digit8 = new Button(rightOf(digit7).offset(SPACING, 0), BUTTON_WIDTH, "8");
        digit8.setFontSize(BUTTON_FONT_SIZE);
        digit8.setOnAction(() -> calc.digit(8));
        var digit9 = new Button(rightOf(digit8).offset(SPACING, 0), BUTTON_WIDTH, "9");
        digit9.setFontSize(BUTTON_FONT_SIZE);
        digit9.setOnAction(() -> calc.digit(9));
        var times = new Button(rightOf(digit9).offset(SPACING, 0), BUTTON_WIDTH, "×");
        times.setFontSize(BUTTON_FONT_SIZE);
        times.setOnAction(() -> calc.operator(TIMES));

        var digit4 = new Button(below(digit7).offset(0, SPACING), BUTTON_WIDTH, "4");
        digit4.setFontSize(BUTTON_FONT_SIZE);
        digit4.setOnAction(() -> calc.digit(4));
        var digit5 = new Button(rightOf(digit4).offset(SPACING, 0), BUTTON_WIDTH, "5");
        digit5.setFontSize(BUTTON_FONT_SIZE);
        digit5.setOnAction(() -> calc.digit(5));
        var digit6 = new Button(rightOf(digit5).offset(SPACING, 0), BUTTON_WIDTH, "6");
        digit6.setFontSize(BUTTON_FONT_SIZE);
        digit6.setOnAction(() -> calc.digit(6));
        var minus = new Button(rightOf(digit6).offset(SPACING, 0), BUTTON_WIDTH, "–");
        minus.setFontSize(BUTTON_FONT_SIZE);
        minus.setOnAction(() -> calc.operator(MINUS));

        var digit1 = new Button(below(digit4).offset(0, SPACING), BUTTON_WIDTH, "1");
        digit1.setFontSize(BUTTON_FONT_SIZE);
        digit1.setOnAction(() -> calc.digit(1));
        var digit2 = new Button(rightOf(digit1).offset(SPACING, 0), BUTTON_WIDTH, "2");
        digit2.setFontSize(BUTTON_FONT_SIZE);
        digit2.setOnAction(() -> calc.digit(2));
        var digit3 = new Button(rightOf(digit2).offset(SPACING, 0), BUTTON_WIDTH, "3");
        digit3.setFontSize(BUTTON_FONT_SIZE);
        digit3.setOnAction(() -> calc.digit(3));
        var plus = new Button(rightOf(digit3).offset(SPACING, 0), BUTTON_WIDTH, "+");
        plus.setFontSize(BUTTON_FONT_SIZE);
        plus.setOnAction(() -> calc.operator(PLUS));

        var negate = new Button(below(digit1).offset(0, SPACING), BUTTON_WIDTH, "+/–");
        negate.setFontSize(BUTTON_FONT_SIZE);
        negate.setOnAction(calc::negate);
        var digit0 = new Button(rightOf(negate).offset(SPACING, 0), BUTTON_WIDTH, "0");
        digit0.setFontSize(BUTTON_FONT_SIZE);
        digit0.setOnAction(() -> calc.digit(0));
        var point = new Button(rightOf(digit0).offset(SPACING, 0), BUTTON_WIDTH, ".");
        point.setFontSize(BUTTON_FONT_SIZE);
        point.setOnAction(calc::point);
        var equals = new Button(rightOf(point).offset(SPACING, 0), BUTTON_WIDTH, "=");
        equals.setFontSize(BUTTON_FONT_SIZE);
        equals.setOnAction(calc::equals);

        makeLighter(clear);
        makeLighter(clearEntry);
        makeLighter(backspace);
        makeLighter(dividedBy);
        makeLighter(times);
        makeLighter(minus);
        makeLighter(plus);
        makeDarker(equals);

        return new Group(display,
                clear, clearEntry, backspace,
                dividedBy, times, minus, plus, equals,
                digit0, digit1, digit2, digit3, digit4,
                digit5, digit6, digit7, digit8, digit9,
                point, negate);
    }

    private void updateDisplay(String text) {
        int size;
        for (size = MAX_DISPLAY_FONT_SIZE; size >= 1; size--) {
            var font = new Font(Control.DEFAULT_TYPEFACE, size);
            if (font.measureTextWidth(text) <= display.getWidth()) {
                break;
            }
        }
        display.setFontSize(size);
        display.setText(text);
    }

    private void makeLighter(Button b) {
        b.setBackgroundColor(lighter(b.getBackgroundColor()));
        b.setHoverBackgroundColor(lighter(b.getHoverBackgroundColor()));
        b.setPressedBackgroundColor(lighter(b.getPressedBackgroundColor()));
    }

    private Color4f lighter(Color4f c) {
        return new Color4f(
                1 - (1 - c.getR()) * 0.5f,
                1 - (1 - c.getG()) * 0.5f,
                1 - (1 - c.getB()) * 0.5f,
                c.getA());
    }

    private void makeDarker(Button b) {
        b.setBackgroundColor(darker(b.getBackgroundColor()));
        b.setHoverBackgroundColor(darker(b.getHoverBackgroundColor()));
        b.setPressedBackgroundColor(darker(b.getPressedBackgroundColor()));
    }

    private Color4f darker(Color4f c) {
        return new Color4f(
                c.getR() * 0.5f,
                c.getG() * 0.5f,
                c.getB() * 0.5f,
                c.getA());
    }
}
