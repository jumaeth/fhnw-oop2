package smartcampus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Campus {
    private final List<Room> rooms = new ArrayList<>();

    public Campus(List<Room> rooms) {
        this.rooms.addAll(rooms);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Gibt den gesamten Stromverbrauch ('powerConsumption') aller Aktoren im
     * Raum mit dem gegebenen Namen zurück. Falls kein Raum mit dem gegebenen
     * Namen existiert, wird eine IllegalArgumentException geworfen.
     * <p>
     * Diese Methode darf mit oder ohne Streams implementiert werden.
     */
    public double totalPowerConsumptionForRoom(String roomName) {
        // TODO
        return -1.0;
    }

    /**
     * Gibt ein Set mit den Öffnungszeiten aller Räume im Campus zurück. Die
     * Öffnungszeiten werden als TimeOfDay-Objekte zurückgegeben, wobei eine
     * bestimmte Öffnungszeit, z. B. 08:30, nur einmal im Set vorkommen darf.
     * Ausnahmsweise ist hier als Rückgabetyp eine konkrete Set-Implementation
     * vorgegeben, nämlich 'HashSet'. Passen Sie die Klasse TimeOfDay
     * entsprechend an und implementieren Sie anschliessend diese Methode.
     * Beachten Sie, dass bestimmte Räume keine Öffnungszeit (null) haben
     * können. Diese Räume sollen ignoriert werden.
     * <p>
     * Tipp: Diese Methode darf mit oder ohne Stream implementiert werden, aber
     * da der Rückgabetyp 'HashSet' und nicht 'Set' ist, ist eine Stream-Lösung
     * nicht ganz offensichtlich.
     */
    public HashSet<TimeOfDay> allOpeningHours() {
        // TODO
        return null;
    }

    /**
     * Findet die (höchstens) 'n' Räume im Campus mit den wenigsten Geräten und
     * gibt deren Namen zurück. Die Namen werden als Liste zurückgegeben, wobei
     * die Räume mit den wenigsten Geräten zuerst in der Liste erscheinen. Falls
     * mehrere Räume gleich viele Geräte haben, können diese in beliebiger
     * Reihenfolge in der Liste erscheinen. Falls es weniger als 'n' Räume gibt,
     * werden entsprechend weniger Namen zurückgegeben.
     * <p>
     * Wichtig: Diese Methode muss mit einer einzigen Stream-Pipeline (ohne
     * forEach) implementiert werden. Lösungen, die mehrere Pipelines, forEach
     * oder Schleifen verwenden, erhalten keine Punkte.
     */
    public List<String> roomsWithFewestDevices(int n) {
        // TODO
        return null;
    }

    /**
     * Zählt, wie viele Sensoren von dem gegebenen Sensortyp im Campus vorhanden
     * sind.
     * <p>
     * Wichtig: Diese Methode muss mit einer einzigen Stream-Pipeline (ohne
     * forEach) implementiert werden. Lösungen, die mehrere Pipelines, forEach
     * oder Schleifen verwenden, erhalten keine Punkte.
     */
    public Number countSensorsByType(SensorType type) {
        // TODO
        return null;
    }

    /**
     * Liest aus dem gegebenen InputStream die Öffnungszeiten der Räume im
     * Campus ein und speichert sie direkt in die Room-Objekte mit den
     * entsprechenden Namen. Es wird davon ausgegangen, dass keine zwei Räume
     * mit gleichem Namen existieren. Falls für einen Raum bereits eine
     * Öffnungszeit existiert, wird diese durch die neue Öffnungszeit ersetzt.
     * Falls für einen bestimmten Raum keine Öffnungszeit in der Datei angegeben
     * ist, bleibt dessen Öffnungszeit unverändert.
     * <p>
     * Der InputStream liefert den Inhalt einer Textdatei, welche z. B.
     * folgenden Inhalt haben könnte:
     * <pre>
     * Mensa         08:30
     * Bibliothek    09:00
     * Studiensaal A 08:00
     * Studiensaal B 08:00
     * Sekretariat   08:45
     * </pre>
     * Jede Zeile enthält den Namen eines Raums und die Öffnungszeit des Raums.
     * Die beiden Werte sind durch ein oder mehrere Leerzeichen getrennt. Der
     * Name eines Raums kann ebenfalls Leerzeichen enthalten, aber das letzte
     * Leerzeichen in einer Zeile steht immer direkt vor der Öffnungszeit. Die
     * Öffnungszeiten sind im Format HH:MM angegeben. Für ungültige Zeilen
     * (fehlende oder ungültige Öffnungszeit, nicht existierender Raum) wird
     * eine ParseException aus dem Package smartcampus geworfen.
     * <p>
     * Die Textdatei ist in UTF-8 kodiert; stellen Sie sicher, dass garantiert
     * der korrekte Zeichensatz verwendet wird und dass der InputStream am Ende
     * auf jeden Fall geschlossen wird.
     */
    public void readOpeningHours(InputStream in) throws IOException {
        // TODO
    }

    // TODO: Fügen Sie hier die Methoden 'installAutomation' und
    //  'performAutomations' hinzu, welche zu den Unit-Tests in der Klasse
    //  'AutomationTest' passen.
}
