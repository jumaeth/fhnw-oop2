package views;

import java.util.Arrays;

/**
 * Eine Telefonnummer. Kann nicht viel mehr als ein String aus Ziffern,
 * aber stellt wenigstens sicher, dass die Nummer wirklich aus Ziffern
 * besteht.
 *
 * Diese Klasse nicht verändern!
 */
public class PhoneNumber {

    private final byte[] digits;

    public PhoneNumber(String number) {
        if (number.isEmpty()) {
            throw new IllegalArgumentException("invalid phone number");
        }
        digits = new byte[number.length()];
        for (int i = 0; i < number.length(); i++) {
            char digit = number.charAt(i);
            if (!Character.isDigit(digit)) {
                throw new IllegalArgumentException("invalid phone number");
            }
            digits[i] = (byte) Character.getNumericValue(digit);
        }
    }

    public int length() {
        return digits.length;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PhoneNumber other
                && Arrays.equals(digits, other.digits);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(digits);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (byte digit : digits) {
            builder.append(digit);
        }
        return builder.toString();
    }
}
