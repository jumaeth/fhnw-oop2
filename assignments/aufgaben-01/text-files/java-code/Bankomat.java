public class Bankomat {
    public static void main(String[] args) {
        int betrag = 3573;
        System.out.println("Willkommen bei der Bank Ihres Vertrauens");
        System.out.println("****************************************");
        System.out.println();
        System.out.println("Sie wollen also " + betrag + " CHF abheben.");

        System.out.println(betrag / 1000 + " 1000er");
        betrag %= 1000;
        System.out.println(betrag / 200 + " 200er");
        betrag %= 200;
        System.out.println(betrag / 100 + " 100er");
        betrag %= 100;
        System.out.println(betrag / 50 + " 50er");
        betrag %= 50;
        System.out.println(betrag / 20 + " 20er");
        betrag %= 20;
        System.out.println(betrag / 10 + " 10er");
        betrag %= 10;
        System.out.println("\nRestbetrag: " + betrag + " CHF.");
    }
}
