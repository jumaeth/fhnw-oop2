package smartcampus;

public class TimeOfDay {
    private final int hour;
    private final int minute;

    public TimeOfDay(int hour, int minute) {
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException();
        }
        this.hour = hour;
        this.minute = minute;
    }

    public int hour() {
        return hour;
    }

    public int minute() {
        return minute;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }

    // TODO: Erweitern Sie diese Klasse für die Aufgabe 'allOpeningHours'
}
