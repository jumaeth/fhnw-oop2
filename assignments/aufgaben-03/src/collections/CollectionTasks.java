package collections;

import java.util.*;

public class CollectionTasks {

    /**
     * Entfernt alle Strings aus dem gegebenen Set, welche mehr als 10 Zeichen
     * enthalten, und gibt diese Strings in einem neuen Set zurück. Lösen Sie
     * diese Aufgabe mit einem Iterator.
     */
    public static Set<String> removeLongStrings(Set<String> set) {
        // TODO
        return null;
    }

    /**
     * Entfernt jedes dritte Element aus der gegebenen Liste, angefangen beim
     * dritten. Lösen Sie diese Aufgabe mit einer klassischen Schleife mit Index.
     */
    public static <E> void removeEveryThird(List<E> list) {
        // TODO
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
        // TODO
        return null;
    }

    /**
     * Wandelt alle Strings im words-Set in Kleinbuchstaben um. (Das heisst,
     * ersetzt jeden String im Set mit der entsprechenden
     * Kleinbuchstaben-Version des Strings).
     */
    public static void replace(Set<String> words) {
        // TODO
    }

    /**
     * Gibt die Anzahl Duplikate, die sich als Werte (Values) in der gegebenen
     * Map befinden, zurück. Befinden sich z.B. die Werte 1, 2, 2, 2, 3, 3 in
     * der Map, dann gibt es 3 Duplikate (2, 2, 3).
     */
    public static int countDuplicates(Map<String, Integer> map) {
        // TODO
        return -1;
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
        // TODO
    }

    /**
     * Entfernt alle Duplikate aus der gegebenen Liste. Ein Element ist ein
     * Duplikat, wenn es weiter vorne in der Liste bereits vorkommt.
     */
    public static void removeDuplicates(List<Integer> list) {
        // TODO
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
        // TODO
        return null;
    }
}
