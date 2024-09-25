package password;

import guilib.*;
import io.github.humbleui.jwm.Clipboard;
import io.github.humbleui.skija.Color4f;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static guilib.Button.*;
import static guilib.Position.below;
import static guilib.Position.rightOf;
import static guilib.TextAlignment.CENTER;
import static guilib.TextAlignment.RIGHT;
import static io.github.humbleui.jwm.ClipboardEntry.makePlainText;
import static java.lang.Math.*;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.Collections.emptyList;

public class PasswordCheckerApp extends Application {

    private static final int MIN_STRENGTH = 80;
    private static final Path PASSWORDS_FILE = Path.of("passwords.txt").toAbsolutePath();

    private static final int WIDTH = 300;
    private static final int HEIGHT = 250;
    private static final int SPACING = 20;
    private static final float CTRL_WIDTH = WIDTH - 2 * SPACING;

    public static void main(String[] args) {
        new PasswordCheckerApp().start("Password Checker", WIDTH, HEIGHT);
    }

    private TextField passwordField;
    private Label strengthLabel;
    private Label progressLabel;
    private Button copyButton;

    @Override
    protected Group createContent() {
        passwordField = new TextField(SPACING, SPACING, CTRL_WIDTH);
        passwordField.setFontSize(24);
        passwordField.setOnTextChanged(this::synchronizeControls);

        strengthLabel = new Label(below(passwordField).offset(0, SPACING), CTRL_WIDTH / 2, "");
        var minStrengthLabel = new Label(rightOf(strengthLabel), CTRL_WIDTH / 2,
                "Min: " + MIN_STRENGTH);
        minStrengthLabel.setTextAlignment(RIGHT);

        progressLabel = new Label(below(strengthLabel).offset(0, SPACING), CTRL_WIDTH, "");
        progressLabel.setFontSize(36);
        progressLabel.setTextAlignment(CENTER);

        copyButton = new Button(below(progressLabel).offset(0, SPACING), CTRL_WIDTH, "Copy & Save");

        synchronizeControls();
        return new Group(passwordField, strengthLabel, minStrengthLabel, progressLabel, copyButton);
    }

    private void synchronizeControls() {
        var strength = computeStrength();
        strengthLabel.setText(String.format("Strength: %.1f", strength));
        var ratio = min(1, strength / MIN_STRENGTH);
        progressLabel.setText((int) (ratio * 100) + "%");

        if (strength >= MIN_STRENGTH) {
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
    }

    private double computeStrength() {
        var password = passwordField.getText();
        var charsToTry = 0;
        if (password.chars().anyMatch(Character::isUpperCase)) {
            charsToTry += 26;
        }
        if (password.chars().anyMatch(Character::isLowerCase)) {
            charsToTry += 26;
        }
        if (password.chars().anyMatch(Character::isDigit)) {
            charsToTry += 10;
        }
        if (password.chars().anyMatch(c -> !Character.isLetterOrDigit(c))) {
            charsToTry += 30; // because why not
        }
        var guessesNeeded = pow(charsToTry, password.length());
        // compute "entropy"
        return log(guessesNeeded) / log(2);
    }

    private void copyAndSavePassword() {
        Clipboard.set(makePlainText(passwordField.getText()));
        try {
            saveNewPassword();
            passwordField.setText("");
        } catch (IOException e) {
            System.err.println("Error: Could not read/write passwords file.");
            e.printStackTrace();
        }
    }

    public void saveNewPassword() throws IOException {
        List<?> existingPasswords = emptyList();
        if (Files.exists(PASSWORDS_FILE)) {
            existingPasswords = Files.readAllLines(PASSWORDS_FILE);
        }
        if (!existingPasswords.contains(passwordField.getText())) {
            Files.writeString(PASSWORDS_FILE, passwordField.getText() + "\n", CREATE, APPEND);
        }
    }
}
