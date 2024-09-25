package currencyconverter;

import guilib.*;
import io.github.humbleui.skija.Color4f;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static guilib.Position.below;
import static guilib.Position.rightOf;

public class CurrencyConverterApp extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 350;
    private static final int FONT_SIZE = 36;
    private static final int SPACING = 25;
    private static final float BUTTON_WIDTH = (WIDTH - 5 * SPACING) / 4.0f;
    private static final float TEXT_FIELD_WIDTH = 200;
    private static final Color4f INVALID_INPUT_COLOR = new Color4f(0xFFFF0000);
    private static final Color4f VALID_INPUT_COLOR = new Color4f(0xFF38475b);
    private static final Map<String, Double> CURRENCIES = Map.of("GBP", 0.88, "EUR", 1.02, "CHF", 1.0, "USD", 1.1);
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    private final RadioGroup radioGroup1 = new RadioGroup();
    private final RadioGroup radioGroup2 = new RadioGroup();

    public static void main(String[] args) {
        new CurrencyConverterApp().start("Currency Converter", WIDTH, HEIGHT);
    }

    @Override
    protected Group createContent() {
        Label display = new Label(SPACING, SPACING, WIDTH - 2 * SPACING, "Test");
        List<Node> nodes = new ArrayList<>();

        var labelInput = new Label(below(display).offset(0, SPACING), BUTTON_WIDTH, "Original");
        labelInput.setFontSize(16);
        nodes.add(labelInput);

        var inputAmount = new TextField(below(labelInput).offset(0, 5), TEXT_FIELD_WIDTH);
        inputAmount.setFontSize(FONT_SIZE);
        nodes.add(inputAmount);

        var labelCalc = new Label(rightOf(labelInput).offset(120, 0), BUTTON_WIDTH, "Converted");
        labelCalc.setFontSize(16);
        nodes.add(labelCalc);

        var calc = new Label(below(labelCalc).offset(0, 10), BUTTON_WIDTH, "-");
        calc.setFontSize(FONT_SIZE);
        nodes.add(calc);

        int y = 0;
        for (String currency : CURRENCIES.keySet()) {
            var radio = new RadioButton(below(inputAmount).offset(0, SPACING + y), currency);
            radio.setOnSelectedChanged(() -> calc.setText(calcConversionRate(inputAmount, inputAmount.getText())));
            radio.setGroup(radioGroup1);
            y += 30;
        }
        radioGroup1.setFirstSelected();
        nodes.addAll(radioGroup1.getGroup());

        y = 0;
        for (String currency : CURRENCIES.keySet()) {
            var radio = new RadioButton(below(calc).offset(0, 2 + SPACING + y), currency);
            radio.setOnSelectedChanged(() -> calc.setText(calcConversionRate(inputAmount, inputAmount.getText())));
            radio.setGroup(radioGroup2);
            y += 30;
        }
        radioGroup2.setFirstSelected();
        nodes.addAll(radioGroup2.getGroup());

        inputAmount.setOnTextChanged(() -> calc.setText(calcConversionRate(inputAmount, inputAmount.getText())));

        return new Group(nodes);
    }

    private String calcConversionRate(TextField input, String text) {
        var selected1 = radioGroup1.getSelected();
        var selected2 = radioGroup2.getSelected();
        if (text.isEmpty() || selected1 == null || selected2 == null) {
            return "-";
        }
        try {
            double baseCHF = Double.parseDouble(text) / CURRENCIES.get(selected1.getText());
            double value = baseCHF * CURRENCIES.get(selected2.getText());
            input.setTextColor(VALID_INPUT_COLOR);
            return String.valueOf(DECIMAL_FORMAT.format(value));
        } catch (NumberFormatException e) {
            input.setTextColor(INVALID_INPUT_COLOR);
            return "-";
        }
    }
}
