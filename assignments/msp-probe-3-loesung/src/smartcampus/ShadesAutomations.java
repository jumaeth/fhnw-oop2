package smartcampus;

import java.util.function.BiConsumer;

public class ShadesAutomations {

    /**
     * Erstellt eine Automatisierung, welche alle Storen im Campus morgens um
     * 6 Uhr öffnet und abends um 20:30 Uhr schliesst.
     * <p>
     * Wichtig: Die Automatisierung muss mit einem Lambda-Ausdruck implementiert
     * werden, um die volle Punktzahl zu erhalten. Lösungen ohne Lambda-Ausdruck
     * geben maximal die Hälfte der Punkte.
     */
    public static BiConsumer<Campus, TimeOfDay> newOpenCloseAutomation() {
        return (campus, time) -> {
            for (var room : campus.getRooms()) {
                for (var device : room.getDevices()) {
                    if (device instanceof Shades shades) {
                        if (time.hour() == 6 && time.minute() == 0) {
                            shades.setPercentClosed(0);
                        } else if (time.hour() == 20 && time.minute() == 30) {
                            shades.setPercentClosed(100);
                        }
                    }
                }
            }
        };
    }
}
