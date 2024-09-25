public class Temperatur {

    /**
     * Gibt die Anzahl positiver Werte im gegebenen Array zurück.
     */
    public static int positiveWerte(double[] temp) {
        int counter = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > 0) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Zählt, wie viele Temperaturwerte im gegebenen Array höher
     * sind als der direkt vorangegangene Wert.
     *
     * Beispiel: im Array {3, 1, 2, 0, 4} gibt es zwei Werte, die
     * höher sind als der jeweils vorherige: 2 und 4.
     */
    public static int steigendeTemperatur(double[] temp) {
        int counter = 0;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i - 1] < temp[i]) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Gibt den Durchschnitt aller Temperaturwerte im gegebenen
     * Array zurück.
     */
    public static double durchschnittsTemperatur(double[] temp) {
        double summe = 0;
        for (double t : temp) {
            summe += t;
        }
        return summe / temp.length;
    }

    /**
     * Gibt die Position (der Index) des grössten Werts im Array
     * zurück. Falls es mehrere grösste Werte gibt, soll die
     * Position *des ersten* davon zurückgegeben werden.
     */
    public static int maxTemperaturPosition(double[] temp) {
        int max = -1;
        for (int i = 0; i < temp.length; i++) {
            if (max < 0 || temp[max] < temp[i]) {
                max = i;
            }
        }
        return max;
    }

    /**
     * Gibt den höchsten Temperaturwert im Array zurück. Ihre
     * Lösung darf auf vorherigen Methoden aufbauen.
     */
    public static double maxTemperatur(double[] temp) {
        // Wir sind faul! 👍
        var pos = maxTemperaturPosition(temp);
        if (pos >= 0) {
            return temp[pos];
        } else {
            return Double.NEGATIVE_INFINITY;
        }
    }
}
