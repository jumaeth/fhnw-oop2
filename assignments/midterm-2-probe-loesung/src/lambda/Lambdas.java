package lambda;

import java.util.*;

public class Lambdas {

    /**
     * Erhöht die Löhne aller Angestellten in der gegebenen Map um den gegebenen Betrag, wenn
     * sie Vollzeit arbeiten (mindestens 42.5 Stunden pro Woche).
     * <p>
     * Diese Methode muss mit der replaceAll-Methode von Map und einem Lambda-Ausdruck gelöst
     * werden. Andere Lösungen erhalten keine Punkte.
     */
    public static void increaseFullTimeSalaries(Map<Employee, Double> salaries, int increase) {
        salaries.replaceAll((e, s) -> {
            if (e.getWeekHours() >= 42.5) {
                return s + increase;
            } else {
                return s;
            }
        });
    }

    /**
     * Sammelt alle Dateien, auf die jeder Angestellte zugegriffen hat, in einer Map, basierend auf
     * dem gegebenen Access-Log. Als Beispiel würde für folgenden Log:
     * <pre>
     * Alice    foo.txt
     * Bob      bar.pdf
     * Alice    foo.txt
     * Charlie  baz.docx
     * Alice    bar.pdf
     * </pre>
     * folgende Map zurückgegeben werden:
     * <pre>
     * Alice   -> foo.txt, bar.pdf
     * Bob     -> bar.pdf
     * Charlie -> baz.docx
     * </pre>
     * Diese Methode muss mit der computeIfAbsent-Methode von Map und einem Lambda-Ausdruck gelöst
     * werden. Andere Lösungen erhalten keine Punkte.
     * <p>
     * Tipp: Erstellen Sie als Erstes eine leere HashMap und iterieren Sie dann über die
     * Log-Einträge. Für jeden Eintrag rufen Sie computeIfAbsent auf. Beachten Sie die Dokumentation
     * der Methode, um zu verstehen, wie Sie die Methode korrekt verwenden.
     */
    public static Map<Employee, Set<String>> accessedFilesPerEmployee(List<LogEntry> accessLog) {
        var result = new HashMap<Employee, Set<String>>();
        for (var entry : accessLog) {
            result.computeIfAbsent(entry.employee(), e -> new HashSet<>()).add(entry.file());
        }
        return result;
    }
}
