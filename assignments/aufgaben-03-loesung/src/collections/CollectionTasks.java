package collections;

import java.util.*;

import static java.lang.Math.min;

public class CollectionTasks {

    /**
     * Entfernt alle Strings aus dem gegebenen Set, welche mehr als 10 Zeichen
     * enthalten, und gibt diese Strings in einem neuen Set zurück. Lösen Sie
     * diese Aufgabe mit einem Iterator.
     */
    public static Set<String> removeLongStrings(Set<String> set) {
        var result = new HashSet<String>();
        var i = set.iterator();
        while (i.hasNext()) {
            var s = i.next();
            if (s.length() > 10) {
                i.remove();
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Entfernt jedes dritte Element aus der gegebenen Liste, angefangen beim
     * dritten. Lösen Sie diese Aufgabe mit einer klassischen Schleife mit Index.
     */
    public static <E> void removeEveryThird(List<E> list) {
        for (int i = 2; i < list.size(); i += 2) {
            list.remove(i);
        }
    }

    /**
     * Fügt die beiden gegebenen Listen zu einer neuen zusammen, indem
     * abwechslungsweise Elemente der beiden Liste genommen werden. Falls eine
     * Liste kürzer ist als die andere, werden die übrigen Elemente der längeren
     * Liste einfach hinten angefügt.
     * 
     * Achtung: Die übergebenen Listen sollen nicht verändern werden.
     */
    public static <E> List<E> merge(List<? extends E> first, List<? extends E> second) {
        List<E> result = new ArrayList<>();
        int shorterSize = min(first.size(), second.size());
        for (int i = 0; i < shorterSize; i++) {
            result.add(first.get(i));
            result.add(second.get(i));
        }
        List<? extends E> longer;
        if (first.size() > second.size()) {
            longer = first;
        } else {
            longer = second;
        }
        result.addAll(longer.subList(shorterSize, longer.size()));
        return result;
    }

    /**
     * Wandelt alle Strings im words-Set in Kleinbuchstaben um. (Das heisst,
     * ersetzt jeden String im Set mit der entsprechenden
     * Kleinbuchstaben-Version des Strings).
     */
    public static void replace(Set<String> words) {
        Set<String> lower = new HashSet<>();
        for (String word : words) {
            lower.add(word.toLowerCase());
        }
        words.clear();
        words.addAll(lower);
    }

    /**
     * Gibt die Anzahl Duplikate, die sich als Werte (Values) in der gegebenen
     * Map befinden, zurück. Befinden sich z.B. die Werte 1, 2, 2, 2, 3, 3 in
     * der Map, dann gibt es 3 Duplikate (2, 2, 3).
     */
    public static int countDuplicates(Map<String, Integer> map) {
        return map.values().size() - new HashSet<>(map.values()).size();
    }

    /**
     * Entfernt alle Einträge aus der gegebenen Map, bei welchen der Schlüssel
     * (Key) gleich dem Wert (Value) ist.
     *
     * Tipp: Verwenden Sie die entrySet-Methode, welche ein Set von Entry-
     * Objekten zurückgibt. Wenn man Einträge aus diesem Set entfernt,
     * verschwinden sie auch aus der Map!
     */
    public static <E> void removeTwins(Map<E, E> map) {
        var entries = map.entrySet();
        entries.removeIf(e -> e.getKey().equals(e.getValue()));
    }

    /**
     * Entfernt alle Duplikate aus der gegebenen Liste. Ein Element ist ein
     * Duplikat, wenn es weiter vorne in der Liste bereits vorkommt.
     */
    public static void removeDuplicates(List<Integer> list) {
        var set = new HashSet<>(list);
        var i = list.iterator();
        while (i.hasNext()) {
            var wasInSet = set.remove(i.next());
            if (!wasInSet) {
                i.remove();
            }
        }
    }

    /**
     * Findet für jedes Wort im gegebenen Text heraus, auf welchen Zeilen es
     * vorkommt. Sie können davon ausgehen, dass sich im Text nur
     * Kleinbuchstaben, Leerzeichen und Zeilenumbrüche befinden. Das Resultat
     * ist eine Map, welche für jedes Wort im Text ein Set mit allen
     * entsprechenden Zeilennummern enthält.
     *
     * Tipps: Verwenden Sie einen Scanner, und die hasNextLine- und nextLine-
     * Methoden. Innerhalb einer Zeile können Sie mit einem zusätzlichen
     * Scanner mittels hasNext() und next() einzelne Wörter von der Zeile
     * lesen.
     */
    public static Map<String, Set<Integer>> findOccurrences(String text) {
        Map<String, Set<Integer>> res = new HashMap<>();
        Scanner lineScanner = new Scanner(text);
        int line = 1;
        while (lineScanner.hasNextLine()) {
            Scanner wordScanner = new Scanner(lineScanner.nextLine());
            while (wordScanner.hasNext()) {
                String word = wordScanner.next();
                Set<Integer> set = res.get(word);
                if (set == null) {
                    set = new HashSet<>();
                    res.put(word, set);
                }
                set.add(line);
            }
            line++;
        }
        return res;
    }
}
