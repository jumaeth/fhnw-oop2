import java.util.Scanner;

public class Schubladen {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Geben Sie drei Schubladenhöhen ein (in cm, ganze Zahlen): ");
        int a = s.nextInt();
        int b = s.nextInt();
        int c = s.nextInt();

        int mindestHoehe = mindestHoehe(a, b, c);
        System.out.println("Mindesthöhe: " + mindestHoehe);
    }

    public static int mindestHoehe(int a, int b, int c) {
        if (a < 0 || b < 0 || c < 0) {
            return -1;
        }

        int hoehe = a;
        while (hoehe % b != 0 || hoehe % c != 0) {
            hoehe += a;
        }
        return hoehe;
    }
}
