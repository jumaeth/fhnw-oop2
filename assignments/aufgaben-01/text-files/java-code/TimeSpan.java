public class TimeSpan {
    private int hours;
    private int minutes;

    public TimeSpan(int hours, int minutes) {
        // zero-initialization
        add(hours, minutes); // reuse checks and conversions in add()
    }

    public void add(int hours, int minutes) {
        if (hours < 0 || minutes < 0) {
            throw new IllegalArgumentException("no negative values permitted");
        }
        this.hours += hours;
        this.minutes += minutes;
        this.hours += this.minutes / 60; // convert surplus minutes to hours
        this.minutes %= 60;              // keep only rest
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int totalMinutes() {
        return hours * 60 + minutes;
    }

    public String toString() {
        return hours + "h " + minutes + "min";
    }
}
