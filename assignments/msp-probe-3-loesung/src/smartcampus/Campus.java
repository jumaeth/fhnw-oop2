package smartcampus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import static java.lang.Integer.parseInt;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toCollection;

public class Campus {
    private final List<Room> rooms = new ArrayList<>();
    private final List<BiConsumer<Campus, TimeOfDay>> automations = new ArrayList<>();

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
        var room = rooms.stream()
                .filter(r -> r.getName().equals(roomName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return room.getDevices().stream()
                .filter(device -> device instanceof Actor)
                .mapToDouble(device -> ((Actor) device).powerConsumption())
                .sum();
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
        return rooms.stream()
                .map(Room::getOpeningTime)
                .filter(Objects::nonNull)
                .collect(toCollection(HashSet::new));
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
        return rooms.stream()
                .sorted(comparingInt(room -> room.getDevices().size()))
                .limit(n)
                .map(Room::getName)
                .toList();
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
        return rooms.stream()
                .flatMap(room -> room.getDevices().stream())
                .filter(device -> device instanceof Sensor)
                .map(device -> (Sensor) device)
                .filter(sensor -> sensor.getType() == type)
                .count();
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
        try (var reader = new BufferedReader(new InputStreamReader(in, UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                var openingTime = line.substring(line.length() - 5);
                var hour = parseInt(openingTime.substring(0, 2));
                var minute = parseInt(openingTime.substring(3));
                var time = new TimeOfDay(hour, minute);
                var name = line.substring(0, line.length() - 5).trim();
                var room = rooms.stream()
                        .filter(r -> r.getName().equals(name))
                        .findFirst()
                        .orElseThrow(() -> new ParseException("Invalid opening hours file"));
                room.setOpeningTime(time);
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            throw new ParseException("Invalid opening hours file", e);
        }
    }

    public void installAutomation(BiConsumer<Campus, TimeOfDay> automation) {
        // may use BiConsumer or create a new functional interface that fits
        // the lambda expressions in the tests
        automations.add(automation);
    }

    public void performAutomations(int hour, int minute) {
        for (var automation : automations) {
            automation.accept(this, new TimeOfDay(hour, minute));
        }
    }
}
