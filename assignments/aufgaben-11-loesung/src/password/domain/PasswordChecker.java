package password.domain;

import password.io.PasswordIO;

import java.io.IOException;

import static java.lang.Math.*;

public class PasswordChecker {

    private static final int DEFAULT_MIN_STRENGTH = 80;

    private final PasswordIO io;

    private String password = "";
    private int minStrength = DEFAULT_MIN_STRENGTH;

    public PasswordChecker(PasswordIO io) {
        this.io = io;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        password = "";
    }
}
