package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class IOTasks {

    /**
     * Liefert die ersten (bis zu) 'n' nicht-leeren Zeilen im Text zurück,
     * den der gegebene InputStream liefert. Verwenden Sie ISO 8859-1, um den
     * Text zu decodieren, und achten Sie darauf, den InputStream am
     * Ende zu schliessen.
     */
    public static List<String> firstNonEmptyLines(InputStream in, int n) throws IOException {
        // TODO
        return null;
    }

    /**
     * Berechnet die ersten 'n' Zweierpotenzen (1, 2, 4, 8...) und
     * schreibt sie als Text in den gegebenen OutputStream, jede auf
     * eine eigene Zeile. Verwenden Sie UTF-8, um den Text zu codieren,
     * und achten Sie darauf, den OutputStream am Ende zu schliessen.
     */
    public static void writePowersOfTwo(OutputStream out, int n) throws IOException {
        // TODO
    }

    /**
     * Durchsucht den Text, den der gegebene InputStream liefert, nach
     * Zahlen (ganze oder reelle) und gibt alle in einer Liste zurück.
     * Sie können davon ausgehen, dass sämtliche Zahlen durch Whitespace
     * von anderen Textstücken getrennt sind. Verwenden Sie UTF-8, um
     * den Text zu decodieren, und achten Sie darauf, den InputStream
     * am Ende zu schliessen.
     * <p>
     * Tipp: Verwenden Sie die Scanner-Klasse.
     */
    public static List<Double> extractNumbers(InputStream in) throws IOException {
        // TODO
        return null;
    }

    /**
     * Parst den Text, den der gegebene InputStream liefert, als CSV
     * und wandelt jede Zeile (ausser der Header-Zeile) in ein Person-
     * Objekt um. Der Text hat folgendes Format:
     * <pre>
     * Name;Age;Positive
     * Maria Mopp;46;1
     * Boris Bopp;23;0
     * Sarah Sutter;39;0
     * Kuno Koriander;78;1
     * Lisa Laufener;17;1
     * </pre>
     * Verwenden Sie UTF-8, um den Text zu decodieren, und achten Sie
     * darauf, den InputStream am Ende zu schliessen.
     */
    public static List<Person> readPeopleFromCsv(InputStream in) throws IOException {
        // TODO
        return null;
    }
}
