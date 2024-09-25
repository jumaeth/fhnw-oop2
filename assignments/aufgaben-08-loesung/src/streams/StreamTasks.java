package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

/**
 * Lösen Sie folgende Aufgaben mit Streams. Sie brauchen vereinzelt
 * Hilfsvariablen oder -Collections, aber sämtliche Aufgaben können ohne
 * Schleifen gelöst werden. Falls eine Lösung mit Streams zu schwierig ist,
 * können Sie sie auch (zuerst) mit Schleifen versuchen zu lösen. An einer
 * Prüfung könnten Lösungen mit Schleifen aber weniger Punkte geben.
 */
public class StreamTasks {

    /**
     * Gibt die Namen aller Länder mit einer Anzahl Einwohner zwischen
     * 10'000'000 und 100'000'000 zurück.
     */
    public static List<String> mediumCountries(List<Country> countries) {
        return countries.stream()
                .filter(c -> c.getPopulation() >= 10_000_000)
                .filter(c -> c.getPopulation() <= 100_000_000)
                .map(Country::getName)
                .toList();
    }

    /**
     * Findet alle Personen in der gegebenen Liste, die älter als 65 oder
     * "positiv" sind, und gibt deren Namen zurück.
     */
    public static List<String> peopleOver65OrPositive(List<Person> people) {
        return people.stream()
                .filter(p -> p.positive() || p.age() > 65)
                .map(Person::name)
                .toList();
    }

    /**
     * Findet alle Länder, die in einer Ländergruppe mit mindestens 5
     * Mitgliedern sind, und gibt deren Einwohnerzahlen als Liste zurück. Es
     * wird davon ausgegangen, dass jedes Land in genau einer Ländergruppe ist.
     */
    public static List<Integer> populationOfCountriesInLargeGroups(List<CountryGroup> groups) {
        return groups.stream()
                .filter(g -> g.getMembers().size() >= 5)
                .flatMap(g -> g.getMembers().stream())
                .map(Country::getPopulation)
                .toList();
    }

    /**
     * Ersetzt in dem gegebenen Stream von Namen (Strings) alle jene, die in dem
     * gegebenen Set von "kritischen Namen" vorkommen, mit einem
     * "anonymisierten" String. Ein solcher String besteht aus lauter
     * Fragezeichen und ist gleich lang wie der ursprüngliche Name. Zum Beispiel
     * würde der Name "John Walter" durch "???????????" ersetzt (wenn er in dem
     * Set vorkäme). Das Resultat wird wieder als Stream zurückgegeben, d. h.,
     * es soll keine terminal operation wie toList() aufgerufen werden.
     * <p>
     * Tipp: Hier brauchen Sie eine einzige map-Operation, welche aber einen
     * komplexeren (evtl. mehrzeiligen) Lambda-Ausdruck benötigt.
     */
    public static Stream<String> anonymize(Stream<String> names, Set<String> critical) {
        return names.map(s -> critical.contains(s) ? "?".repeat(s.length()) : s);

//        return names.map(s -> {
//            if (critical.contains(s)) {
//                return "?".repeat(s.length());
//            } else {
//                return s;
//            }
//        });
    }

    /**
     * Gibt ein Set mit den Altersangaben aller positiven Personen zurück. Da es
     * sich um ein Set handelt, kommen in dem Resultat keine doppelten
     * Altersangaben vor.
     */
    public static Set<Integer> agesOfPositivePeople(Person[] people) {
        return Arrays.stream(people)
                .filter(Person::positive)
                .map(Person::age)
                .collect(toSet());
    }

    /**
     * Gruppiert die Länder nach der Länge ihrer Namen.
     */
    public static Map<Integer, List<Country>> groupByNameLength(List<Country> countries) {
        return countries.stream()
                .collect(groupingBy(c -> c.getName().length()));
    }

    /**
     * Findet alle positiven Personen in der gegebenen Liste, gruppiert diese
     * nach Alter und gibt eine Map zurück, die für jedes Alter die Anzahl
     * positiver Personen in diesem Alter enthält. Die Map braucht nur Einträge
     * für jene Alter zu haben, für die es mindestens eine positive Person gibt.
     * Die Werte in der Map können irgendeine Art von Number sein.
     * <p>
     * Beispiel: Für folgende Liste von Personen (ohne Namen): [65-, 45+, 65+,
     * 60+, 44-, 51+, 65+, 45+, 61+, 62-, 65+] würde folgende Map zurückgegeben
     * werden: {45 → 2, 60 → 1, 51 → 1, 65 → 3, 61 → 1}.
     * <p>
     * Tipp: Versuchen Sie, groupingBy() mit counting() zu kombinieren.
     */
    public static Map<Integer, ? extends Number> positiveForEachAge(List<Person> people) {
        return people.stream()
                .filter(Person::positive)
                .collect(groupingBy(Person::age, counting()));
    }

    /**
     * Findet alle Namen (Strings) in der gegebenen Liste, die mindestens
     * dreimal vorkommen, und gibt sie als Set zurück. Versuchen Sie, die Namen
     * in eine Map zu sammeln, welche für jeden Namen die Anzahl Vorkommnisse
     * enthält, und finden Sie danach die gesuchten Namen, indem Sie einen
     * Stream für das entrySet() dieser Map erstellen.
     */
    public static Set<String> presentThreeTimes(List<String> names) {
        var counts = names.stream()
                .collect(groupingBy(identity(), counting()));
        return counts.entrySet().stream()
                .filter(e -> e.getValue() >= 3)
                .map(Map.Entry::getKey)
                .collect(toSet());
    }
}
