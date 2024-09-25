package passwordobserver.domain;

import passwordobserver.io.PasswordIO;

import java.io.IOException;
import java.util.function.Consumer;

import static java.lang.Math.*;

public class PasswordChecker {

    private static final int DEFAULT_MIN_STRENGTH = 80;

    private final PasswordIO io;

    private String password = "";
    private int minStrength = DEFAULT_MIN_STRENGTH;

    private Consumer<Double> strengthListener;
    private Consumer<Double> strengthRatioListener;
    private Consumer<Boolean> strongEnoughListener;

    public PasswordChecker(PasswordIO io) {
        this.io = io;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        strengthChanged();
    }

    public double getStrength() {
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

    public double getMinStrength() {
        return minStrength;
    }

    public void setMinStrength(int minStrength) {
        this.minStrength = minStrength;
        strengthRatioChanged();
    }

    public double getStrengthRatio() {
        return min(1, getStrength() / minStrength);
    }

    public boolean isStrongEnough() {
        return getStrength() >= minStrength;
    }

    public void savePassword() throws IOException {
        if (!isStrongEnough()) {
            throw new IllegalArgumentException("password is not strong enough");
        }
        io.saveNewPassword(password);
    }

    public void setStrengthListener(Consumer<Double> strengthListener) {
        this.strengthListener = strengthListener;
    }

    public void setStrengthRatioListener(Consumer<Double> strengthRatioListener) {
        this.strengthRatioListener = strengthRatioListener;
    }

    public void setStrongEnoughListener(Consumer<Boolean> strongEnoughListener) {
        this.strongEnoughListener = strongEnoughListener;
    }

    private void strengthChanged() {
        if (strengthListener != null) {
            strengthListener.accept(getStrength());
        }
        strengthRatioChanged();
    }

    private void strengthRatioChanged() {
        if (strengthRatioListener != null) {
            strengthRatioListener.accept(getStrengthRatio());
        }
        if (strongEnoughListener != null) {
            strongEnoughListener.accept(isStrongEnough());
        }
    }
}
