import java.util.Scanner;

public class ZinseszinsInteraktiv {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Anfangskontostand (CHF)? ");
        int stand = s.nextInt() * 100; // Rappen
        System.out.print("Zinssatz (%)?            ");
        double zinssatz = s.nextDouble() / 100;
        System.out.print("Anzahl Jahre?            ");
        int jahre = s.nextInt();

        for (int jahr = 1; jahr <= jahre; jahr++) {
            int zins = (int) (stand * zinssatz);
            stand += zins;
        }
        double endstand = stand / 100.0;
        System.out.println("Kontostand nach " + jahre + " Jahren: " + endstand);
    }
}
