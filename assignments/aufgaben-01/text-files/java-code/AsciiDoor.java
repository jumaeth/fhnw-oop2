import java.util.Scanner;

public class AsciiDoor {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.print("Grösse? ");
        int size = scanner.nextInt();

        // Balken oben
        for (int i = 1; i <= size + 1; i++) {
            System.out.print("_");
        }
        System.out.println();

        // Pfosten links und rechts
        for (int i = 1; i <= size; i++) {
            System.out.print("|");
            for (int j = 1; j <= size - 1; j++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
    }
}
