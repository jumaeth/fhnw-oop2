package streams;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

/**
 * Lösen Sie folgende Aufgaben mit Streams. Alle bis auf die letzte kann man mit
 * einer einzigen Stream-Pipeline lösen.
 */
public class StreamTasks2 {

    /**
     * Gibt die ersten 'n' guten Restaurants in der Liste zurück. Ein gutes
     * Restaurant hat eine Sternebewertung von mindestens 4.5.
     */
    public static List<Restaurant> firstGoodRestaurants(List<Restaurant> restaurants,
                                                        int n) {
        return restaurants.stream()
                .filter(r -> r.getStarRating() >= 4.5)
                .limit(n)
                .toList();
    }

    /**
     * Gibt alle Primzahlen zurück, die kleiner als 'n' sind. Verwenden Sie die
     * Methode isPrime() unten.
     * <p>
     * Beispiel: primeNumbersBelow(10) gibt [2, 3, 5, 7] zurück.
     */
    public static List<Integer> primeNumbersBelow(int n) {
        return Stream.iterate(2, i -> i + 1)
                .filter(i -> isPrime(i))
                .takeWhile(i -> i < n)
                .toList();
    }

    /**
     * Überprüft, ob die gegebene Zahl eine Primzahl ist.
     */
    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Findet alle Personen in der gegebenen Liste, die jünger als 65 sind, und
     * gibt sie als Liste zurück. Die Personen sollen nach Alter sortiert
     * zurückgegeben werden.
     * <p>
     * Tipp: Verwenden Sie sorted() und Comparator.comparing()/comparingInt().
     */
    public static List<Person> peopleUnder65Sorted(List<Person> people) {
        return people.stream()
                .filter(p -> p.age() < 65)
                .sorted(comparing(Person::age))
                .toList();
    }

    /**
     * Gibt die Namen der 5 "zweitbesten" Restaurants zurück. Das sind die
     * Restaurants mit den Plätzen 6 bis 10 in der Top-Ten-Liste, sortiert nach
     * Sternebewertung. Falls weniger als 10 Restaurants vorhanden sind, sollen
     * alle ab Platz 6 zurückgegeben werden. Falls weniger als 5 Restaurants
     * vorhanden sind, sollen keine zurückgegeben werden.
     */
    public static List<String> secondBestRestaurants(List<Restaurant> restaurants) {
        return restaurants.stream()
                .sorted(reverseOrder(comparing(Restaurant::getStarRating)))
                .skip(5)
                .limit(5)
                .map(Restaurant::getName)
                .toList();
    }

    /**
     * Findet die ersten (bis zu) 'n' positiven Personen im gegebenen Stream und
     * gibt deren Alter als aufsteigend sortierte Liste zurück.
     * <p>
     * Achtung: Die Methode soll auch mit Endlos-Streams funktionieren, solange
     * irgendwann 'n' positive Personen vorkommen.
     */
    public static List<Integer> ageOfFirstPositive(Stream<Person> people, int n) {
        return people
                .filter(Person::positive)
                .limit(n)
                .map(Person::age)
                .sorted()
                .toList();
    }

    /**
     * Sammelt die Kategorien aller Restaurants in der gegebenen Liste. Das
     * Resultat ist eine Liste, in der keine Kategorie doppelt vorkommt. Die
     * Reihenfolge der Kategorien in der Liste soll aber weiterhin jene sein, in
     * welcher sie in den Restaurants vorkommen, bzw. in welcher die Restaurants
     * in der Liste vorkommen.
     * <p>
     * Beispiel: Für folgende Liste von Restaurants:
     * <pre>
     *   Zum Schlüssel     [traditionell]
     *   Bibis             [vegetarisch, teuer, modern]
     *   Ochsen            [gehoben, traditionell]
     *   Mike's Steakhouse [modern, teuer]
     * </pre>
     * lautet das Resultat: [traditionell, vegetarisch, teuer, modern, gehoben].
     */
    public static List<String> collectCategories(List<Restaurant> restaurants) {
        return restaurants.stream()
                .flatMap(r -> r.getCategories().stream())
                .distinct()
                .toList();
    }

    /**
     * Findet das grösste Restaurant (maximale Kapazität) in der Liste, welche
     * die gegebene Kategorie enthält, und gibt dessen Namen zurück. Falls kein
     * solches Restaurant existiert, wird der String "No result" zurückgegeben.
     */
    public static String findLargestRestaurant(List<Restaurant> restaurants,
                                               String category) {
        return restaurants.stream()
                .filter(r -> r.getCategories().contains(category))
                .max(comparing(Restaurant::getCapacity))
                .map(Restaurant::getName)
                .orElse("No result");
    }

    /**
     * Gibt die Anzahl Restaurants zurück, die mindestens eine der gegebenen
     * Kategorien enthalten. Die Kategorien werden unabhängig von Gross- und
     * Kleinschreibung verglichen, wobei die Kategorien im gegebenen Set alle
     * kleingeschrieben sind, aber die Kategorien in den Restaurants auch
     * Grossbuchstaben enthalten können.
     * <p>
     * Versuchen Sie, das mit einer verschachtelten Stream-Pipeline (innerhalb
     * einer Stream-Operation wird wieder ein Stream erstellt) und mit
     * 'anyMatch' zu lösen.
     */
    public static long restaurantsWithAnyCategory(List<Restaurant> restaurants,
                                                  Set<String> categories) {
        return restaurants.stream()
                .filter(r -> r.getCategories().stream()
                        .map(String::toLowerCase)
                        .anyMatch(categories::contains))
                .count();
    }
}
