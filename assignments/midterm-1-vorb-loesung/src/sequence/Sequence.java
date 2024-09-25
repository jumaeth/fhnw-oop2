package sequence;

import java.util.List;

public final class Sequence {

    private static final List<String> ALL_WEEKDAYS = List.of(
            "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag");

    private final List<String> weekdays;

    public Sequence(List<String> weekdays) {
        this.weekdays = weekdays;
    }

    public int stepSize() {
        if (weekdays.size() < 2) {
            return -1;
        }
        var index1 = ALL_WEEKDAYS.indexOf(weekdays.get(0));
        var index2 = ALL_WEEKDAYS.indexOf(weekdays.get(1));
        var firstStep = (index2 - index1 + 7) % 7;

        var prevIndex = index2;
        for (int i = 2; i < weekdays.size(); i++) {
            var index = ALL_WEEKDAYS.indexOf(weekdays.get(i));
            var step = (index - prevIndex + 7) % 7;
            if (step != firstStep) {
                return -1;
            }
            prevIndex = index;
        }
        return firstStep;
    }
}
