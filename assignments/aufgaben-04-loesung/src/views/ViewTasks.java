package views;

import java.util.*;

public class ViewTasks {

    /*
     * Sämtliche Aufgaben in dieser Klasse kann man elegant mit Views lösen.
     * Es wird aber nicht getestet, ob Sie Views verwenden.
     */

    /**
     * Entfernt eine "Signatur" aus der gegebenen E-Mail. Eine Signatur
     * beginnt immer mit einer Zeile, welche genau die Zeichen "-- "
     * enthält (ohne Anführungszeichen). Falls keine Signatur vorhanden
     * ist, macht die Methode nichts. Die E-Mail wird bereits als Liste
     * von Zeilen übergeben, welche direkt modifiziert werden soll.
     *
     * Die Aufgabe kann ohne Schleife gelöst werden.
     */
    public static void removeSignature(List<String> email) {
        int sigStart = email.indexOf("-- ");
        if (sigStart >= 0) {
            email.subList(sigStart, email.size()).clear();
        }
    }

    /**
     * Gibt zurück, wie viele von den Namen im 'names'-Array in dem
     * Telefonbuch zu finden sind. Falls 'names' einen Namen doppelt
     * enthält, soll er nur einmal gezählt werden. Das Telefonbuch soll
     * nicht verändert werden.
     *
     * Die Aufgabe kann ohne Schleife gelöst werden.
     */
    public static int countNames(Map<String, PhoneNumber> phoneBook,
                                 String[] names) {
        var namesInBook = new HashSet<>(phoneBook.keySet());
        namesInBook.retainAll(Arrays.asList(names));
        return namesInBook.size();
    }

    /**
     * Erstellt ein Set von Personen aus einer "name map": eine Map,
     * welche für jeden Nachnamen angibt, welche Vornamen dazu existieren.
     * Das zurückgegebene Set enthält für jede Nachname-Vorname-
     * Kombination eine Person mit diesem Namen. 'nameMap' soll nicht
     * verändert werden.
     *
     * Beispiel: Angenommen, nameMap enthält folgende Einträge:
     *              "Müller" -> ["Peter", "Pauline"],
     *              "Schmid" -> ["Aline"],
     *              "Schaad" -> ["Meret", "Fritz"]
     * Dann enthält das Resultat folgende Personen:
     *              Peter Müller,
     *              Pauline Müller,
     *              Aline Schmid,
     *              Meret Schaad,
     *              Fritz Schaad
     */
    public static Set<Person> peopleFromNameMap(Map<String, Set<String>> nameMap) {
        var people = new HashSet<Person>();
        for (Map.Entry<String, Set<String>> entry : nameMap.entrySet()) {
            var lastName = entry.getKey();
            for (String firstName : entry.getValue()) {
                people.add(new Person(firstName, lastName));
            }
        }
        return people;
    }

    /**
     * Gibt alle Telefonnummern, welche doppelt (oder noch öfter) in dem
     * Telefonbuch vorkommen, zurück. Das Telefonbuch soll nicht verändert
     * werden.
     *
     * Tipp: Erstellen Sie eine Liste der Nummern und versuchen Sie dann,
     * subList() und contains() zu kombinieren.
     */
    public static Set<PhoneNumber> duplicateNumbers(Map<String, PhoneNumber> phoneBook) {
        var numbers = new ArrayList<>(phoneBook.values());
        var duplicates = new HashSet<PhoneNumber>();
        for (int i = 0; i < numbers.size() - 1; i++) {
            var num = numbers.get(i);
            if (numbers.subList(i + 1, numbers.size()).contains(num)) {
                duplicates.add(num);
            }
        }
        return duplicates;
    }
}
