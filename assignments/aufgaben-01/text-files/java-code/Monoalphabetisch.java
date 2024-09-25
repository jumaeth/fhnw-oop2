import java.util.Scanner;

public class Monoalphabetisch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Schlüssel?     ");
        String schluessel = scanner.nextLine();
        System.out.print("Nachricht?     ");
        String nachricht = scanner.nextLine();

        String verschluesselt = "";
        for (int i = 0; i < nachricht.length(); i++) {
            char c = nachricht.charAt(i);
            int pos = c - 'a';
            verschluesselt += schluessel.charAt(pos);
        }

        System.out.println("Verschlüsselt: " + verschluesselt);
    }
}
