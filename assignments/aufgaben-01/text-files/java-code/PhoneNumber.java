import java.util.Scanner;

public class PhoneNumber {

    public static void main(String[] args) {
        System.out.print("Enter a phone number: ");
        String rawNumber = new Scanner(System.in).nextLine();

        String formattedNumber = formatPhoneNumber(rawNumber);
        System.out.println("Formatted: " + formattedNumber);
    }

    public static String formatPhoneNumber(String raw) {
        String digits = "";
        for (int i = 0; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (Character.isDigit(c)) {
                digits += c;
            }
        }

        if (digits.length() != 10) {
            return "ungültig";
        }
        return digits.substring(0, 3) + " " + digits.substring(3, 6) + " "
                + digits.substring(6, 8) + " " + digits.substring(8, 10);

        // ohne substring():
        // return ""
        //         + digits.charAt(0)
        //         + digits.charAt(1)
        //         + digits.charAt(2)
        //         + " "
        //         + digits.charAt(3)
        //         + digits.charAt(4)
        //         + digits.charAt(5)
        //         + " "
        //         + digits.charAt(6)
        //         + digits.charAt(7)
        //         + " "
        //         + digits.charAt(8)
        //         + digits.charAt(9);

        // oder:
        // String result = "";
        // for (int i = 0; i < 10; i++) {
        //     result += digits.charAt(i);
        //     if (i == 2 || i == 5 || i == 7) {
        //         result += " ";
        //     }
        // }
        // return result;
    }
}
