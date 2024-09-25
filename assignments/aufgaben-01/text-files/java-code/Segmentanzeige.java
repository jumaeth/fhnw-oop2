import java.util.Scanner;

public class Segmentanzeige {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie eine codierte Anzeige an: ");
        String codiert = scanner.next();

        int zahl = decodiere(codiert);

        System.out.println(zahl);
    }

    public static int decodiere(String codiert) {
        if (codiert.contains("f")) {
            // übrige Kandidaten: 0, 4, 5, 6, 8, 9
            if (codiert.contains("b")) {
                // übrige Kandidaten: 0, 4, 8, 9
                if (codiert.contains("e")) {
                    // übrige Kandidaten: 0, 8
                    if (codiert.contains("g")) {
                        // übrige Kandidaten: 8
                        return 8;
                    } else {
                        // übrige Kandidaten: 0
                        return 0;
                    }
                } else {
                    // übrige Kandidaten: 4, 9
                    if (codiert.contains("a")) {
                        // übrige Kandidaten: 9
                        return 9;
                    } else {
                        // übrige Kandidaten: 4
                        return 4;
                    }
                }
            } else {
                // übrige Kandidaten: 5, 6
                if (codiert.contains("e")) {
                    // übrige Kandidaten: 6
                    return 6;
                } else {
                    // übrige Kandidaten: 5
                    return 5;
                }
            }
        } else {
            // übrige Kandidaten: 1, 2, 3, 7
            if (codiert.contains("d")) {
                // übrige Kandidaten: 2, 3
                if (codiert.contains("c")) {
                    // übrige Kandidaten: 3
                    return 3;
                } else {
                    // übrige Kandidaten: 2
                    return 2;
                }
            } else {
                // übrige Kandidaten: 1, 7
                if (codiert.contains("a")) {
                    // übrige Kandidaten: 7
                    return 7;
                } else {
                    // übrige Kandidaten: 1
                    return 1;
                }
            }
        }
    }
}
