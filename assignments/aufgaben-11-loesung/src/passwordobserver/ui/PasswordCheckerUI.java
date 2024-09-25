package passwordobserver.ui;

import guilib.*;
import io.github.humbleui.jwm.Clipboard;
import io.github.humbleui.skija.Color4f;
import passwordobserver.domain.PasswordChecker;

import java.io.IOException;

import static guilib.Button.*;
import static guilib.Position.below;
import static guilib.Position.rightOf;
import static guilib.TextAlignment.CENTER;
import static guilib.TextAlignment.RIGHT;
import static io.github.humbleui.jwm.ClipboardEntry.makePlainText;

public class PasswordCheckerUI extends Application {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 250;
    private static final int SPACING = 20;
    private static final float CTRL_WIDTH = WIDTH - 2 * SPACING;

    private final PasswordChecker checker;

    private TextField passwordField;
    private Label strengthLabel;
    private Label progressLabel;
    private Button copyButton;

    public PasswordCheckerUI(PasswordChecker checker) {
        this.checker = checker;
    }

    public void start() {
        start("Password Checker", WIDTH, HEIGHT);
    }

    @Override
    protected Group createContent() {
        passwordField = new TextField(SPACING, SPACING, CTRL_WIDTH);
        passwordField.setFontSize(24);

        strengthLabel = new Label(below(passwordField).offset(0, SPACING), CTRL_WIDTH / 2, "");

        var minStrengthLabel = new Label(rightOf(strengthLabel), CTRL_WIDTH / 2,
                "Min: " + checker.getMinStrength());
        minStrengthLabel.setTextAlignment(RIGHT);

        progressLabel = new Label(below(strengthLabel).offset(0, SPACING), CTRL_WIDTH, "");
        progressLabel.setFontSize(36);
        progressLabel.setTextAlignment(CENTER);

        copyButton = new Button(below(progressLabel).offset(0, SPACING), CTRL_WIDTH, "Copy & Save");

        setUpListeners();
        checker.setPassword(""); // trigger listeners once

        return new Group(passwordField, strengthLabel, minStrengthLabel, progressLabel, copyButton);
    }

    private void setUpListeners() {
        // UI to domain
        passwordField.setOnTextChanged(() -> {
            checker.setPassword(passwordField.getText());
        });

        // domain to UI
        checker.setStrengthListener(strength -> {
            strengthLabel.setText(String.format("Strength: %.1f", strength));
        });
        checker.setStrengthRatioListener(ratio -> {
            progressLabel.setText((int) (ratio * 100) + "%");
        });
        checker.setStrongEnoughListener(strongEnough -> {
            if (strongEnough) {
                copyButton.setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
                copyButton.setHoverBackgroundColor(DEFAULT_HOVER_COLOR);
                copyButton.setPressedBackgroundColor(DEFAULT_PRESSED_COLOR);
                copyButton.setOnAction(this::copyAndSavePassword);
            } else {
                var disabled = new Color4f(0xFFE0E0E0);
                copyButton.setBackgroundColor(disabled);
                copyButton.setHoverBackgroundColor(disabled);
                copyButton.setPressedBackgroundColor(disabled);
                copyButton.setOnAction(null);
            }
        });
    }

    private void copyAndSavePassword() {
        Clipboard.set(makePlainText(passwordField.getText()));
        try {
            checker.savePassword();
            passwordField.setText("");
        } catch (IOException e) {
            System.err.println("Error: Could not read/write passwords file.");
            e.printStackTrace();
        }
    }
}
