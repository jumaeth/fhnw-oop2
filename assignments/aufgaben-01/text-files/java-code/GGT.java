import java.util.Scanner;

public class GGT {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Erste Zahl: ");
        int x = scanner.nextInt();
        System.out.print("Zweite Zahl: ");
        int y = scanner.nextInt();

        while (x != y) {
            if (x > y) { // tauschen
                int temp = x;
                x = y;
                y = temp;
            }
            y -= x;
        }

        System.out.println("Der GGT ist: " + x);
    }
}
