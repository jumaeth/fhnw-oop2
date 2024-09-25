package sequence;

import java.util.List;

public final class Sequence {

    private static final List<String> ALL_WEEKDAYS = List.of(
            "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag");

    public Sequence(List<String> weekdays) {
    }

    public int stepSize() {
        return 0;
    }
}
