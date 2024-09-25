package lambdas;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.*;

/**
 * In dieser Übung geht es nicht darum, selbst viel Code zu schreiben, sondern
 * mit Lambda-Ausdrücken und den richtigen Hilfsmethoden eine möglichst kurze,
 * elegante Lösung zu finden. Alle ausser einer Aufgabe lassen sich mit einer
 * einzigen Zeile lösen! Die Herausforderung besteht vor allem darin, zu
 * verstehen, wie die vorgegebenen Methoden zu verwenden sind und welche
 * passenden Lambda-Ausdrücke benötigt werden.
 * <p>
 * Die vorgegebenen Unit-Tests sind im Vergleich zu den früheren Übungen
 * minimal, da Sie vor allem auf vordefinierte Methoden zurückgreifen und
 * dadurch kaum Logik-Fehler machen werden.
 * <p>
 * Es ist empfehlenswert, für diese Übungen keinen Copilot o.ä. zu verwenden,
 * sondern die Syntax und Semantik der Lambda-Ausdrücke selbst zu erarbeiten.
 */
public class LambdaTasks {

    /**
     * Entfernt alle Strings aus dem Set, die länger als maxLength sind.
     * Verwenden Sie die removeIf-Methode von Set.
     * <p>
     * Erwartete Länge: 1 Zeile
     * <p>
     * Tipps: Als Erstes müssen Sie verstehen, welche Form der gesuchte
     * Lambda-Ausdruck haben muss. Verwenden Sie Ihre IDE um herauszufinden, um
     * welches Interface es sich beim Parameter der removeIf-Methode
     * handelt (Anzeigen der Methodendokumentation). In der Dokumentation dieses
     * Interfaces steht dann, welche Methode implementiert werden muss, zum
     * Beispiel:
     * "This is a functional interface whose functional method is 'test...'."
     * Bei der Dokumentation der Methode 'test' wiederum sehen Sie die Anzahl
     * Parameter und den Rückgabetyp, z. B. 'boolean test(T t)'. Damit wissen
     * Sie, dass der Lambda-Ausdruck einen Parameter haben muss, und dass er
     * einen boolean zurückgeben muss. Die Form ist also z. B.: 's -> ...'.
     */
    public static void removeLongStrings(Set<String> set, int maxLength) {
        // TODO
    }

    /**
     * Gibt alle Elemente (bzw. ihre String-Repräsentation) in der gegebenen
     * Liste einzeln auf einer Zeile auf dem 'output'-PrintStream aus. Verwenden
     * Sie hier keine for-Schleife, sondern die forEach-Methode von List.
     * <p>
     * Erwartete Länge: 1 Zeile
     */
    public static void printList(List<?> list, PrintStream output) {
        // TODO
    }

    /**
     * Ersetzt jedes Datum, das auf ein Wochenende fällt, mit einem Datum, das
     * dem folgenden Montag entspricht. Verwenden Sie die replaceAll-Methode von
     * List, und folgenden Methoden um ein einzelnes Datum 'date' zu
     * verarbeiten:
     * <p>
     * date.getDayOfWeek(): gibt ein Objekt vom Enum-Typ DayOfWeek zurück
     * <p>
     * date.plusDays(n): gibt ein neues Datum zurück, das n Tage später liegt
     * <p>
     * Tipp: Sie können das mit einem normalen if-else-Konstrukt lösen; dann
     * brauchen Sie einen Lambda-Ausdruck mit {}-Klammern. Eleganter ist ein
     * switch-Ausdruck (mit 'case ... -> ...;'), da Sie diesen ohne {} als
     * Lambda-Body verwenden können (er gilt als ein einziger Ausdruck).
     */
    public static void replaceWeekends(List<LocalDate> dates) {
        // TODO
    }

    /**
     * Erhöht alle Werte in der gegebenen Map um 1. Verwenden Sie die
     * replaceAll-Methode von Map. Beachten Sie, dass diese einen anderen
     * Lambda-Ausdruck als List.replaceAll benötigt, da Key *und* Value für die
     * Ersetzung verwendet werden können (auch wenn das hier nicht nötig ist).
     * <p>
     * Erwartete Länge: 1 Zeile
     */
    public static void incrementAll(Map<String, Integer> map) {
        // TODO
    }

    /**
     * Zählt für alle Wörter in der gegebenen List, wie oft sie vorkommen, und
     * gibt das Resultat als Map zurück. Mit Lambdas können Sie das Problem
     * beispielsweise so lösen: Verwenden Sie die merge-Methode von Map, welche
     * einen Schlüssel und einen neuen Wert nimmt, und zusätzlich eine Funktion,
     * die den alten mit dem neuen Wert kombiniert, falls der Schlüssel bereits
     * in der Map vorhanden ist.
     * <p>
     * Erwartete Länge: max 5 Zeilen
     */
    public static Map<String, Integer> frequencies(List<String> words) {
        // TODO
        return null;
    }

    /**
     * Sortiert die Liste der Strings nach der String-Länge. (Um Strings mit
     * derselben Länge brauchen Sie sich nicht zu kümmern, diese dürfen in
     * beliebiger Reihenfolge sortiert werden.) Verwenden Sie die sort-Methode
     * von List, sowie die statische Methode Comparator.comparing(function) oder
     * comparingInt(function). Diese nimmt eine Funktion, die ein
     * Vergleichsmerkmal aus einem Element extrahiert, und macht daraus einen
     * Comparator, der nach diesem Merkmal vergleicht.
     * <p>
     * Erwartete Länge: 1 Zeile
     */
    public static void sortByLength(List<String> list) {
        // TODO
    }

    /**
     * Sortiert die Liste der Personen nach Nach- und dann Vorname. Verwenden
     * Sie Comparator.comparing und beachten Sie die thenComparing-Methode,
     * welche Sie auf einen vorhandenen Comparator aufrufen können; damit können
     * Sie einen Comparator noch "modifizieren", d.h. weitere
     * Vergleichskriterien hinzufügen.
     * <p>
     * Erwartete Länge: 1 (ziemlich lange) Zeile
     */
    public static void sortByLastThenFirstName(List<Person> list) {
        // TODO
    }
}
