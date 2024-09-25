package calculator;

import java.util.function.Consumer;

import static java.lang.Double.parseDouble;

/**
 * Cheap calculator based on double precision.
 */
public class Calculator {

    private String display;
    private Operator operator;
    private double prevNumber;
    private boolean numberEntered;

    private Consumer<String> onDisplayChanged;

    public Calculator() {
        clear();
    }

    public String getDisplay() {
        return display;
    }

    public void setOnDisplayChanged(Consumer<String> onDisplayChanged) {
        this.onDisplayChanged = onDisplayChanged;
    }

    public void clear() {
        display = "0";
        operator = null;
        numberEntered = false;
        triggerDisplayChanged();
    }

    public void clearEntry() {
        display = "0";
        numberEntered = false;
        triggerDisplayChanged();
    }

    public void backspace() {
        if (numberEntered) {
            display = display.substring(0, display.length() - 1);
            if (display.isEmpty()
                    || display.equals("-")
                    || display.equals("-0")) {
                display = "0";
            }
            triggerDisplayChanged();
        }
    }

    public void digit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException();
        }
        if (numberEntered && !display.equals("0")) {
            display += digit;
        } else {
            display = "" + digit;
        }
        numberEntered = true;
        triggerDisplayChanged();
    }

    public void negate() {
        if (!display.equals("0")) {
            if (display.startsWith("-")) {
                display = display.substring(1);
            } else {
                display = "-" + display;
            }
            triggerDisplayChanged();
        }
    }

    public void point() {
        if (numberEntered) {
            if (!display.contains(".")) {
                display += ".";
            }
        } else {
            display = "0.";
        }
        numberEntered = true;
        triggerDisplayChanged();
    }

    public void operator(Operator op) {
        if (operator != null && numberEntered) {
            calculate(); // in case there is an outstanding operation, complete it first
        }
        prevNumber = parseDouble(display);
        operator = op;
        numberEntered = false;
    }

    public void equals() {
        if (operator != null) {
            calculate();
            operator = null;
        }
        numberEntered = false;
    }

    private void calculate() {
        var current = parseDouble(display);
        display = Double.toString(switch (operator) {
            case PLUS -> prevNumber + current;
            case MINUS -> prevNumber - current;
            case TIMES -> prevNumber * current;
            case DIVIDED_BY -> prevNumber / current;
        }).replaceAll("\\.0$", ""); // remove trailing ".0"
        triggerDisplayChanged();
    }

    private void triggerDisplayChanged() {
        if (onDisplayChanged != null) {
            onDisplayChanged.accept(display);
        }
    }
}
