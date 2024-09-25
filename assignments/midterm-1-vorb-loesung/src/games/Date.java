package games;

import java.util.Objects;

/**
 * Ein einfaches Datum mit Tag, Monat und Jahr.
 */
public final class Date implements Comparable<Date> {

    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isEarlierThan(Date other) {
        return compareTo(other) < 0;
    }

    @Override
    public int compareTo(Date other) {
        if (year != other.year) {
            return year - other.year;
        } else if (month != other.month) {
            return month - other.month;
        } else {
            return day - other.day;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Date other
                && day == other.day
                && month == other.month
                && year == other.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    @Override
    public String toString() {
        return day + "." + month + "." + year;
    }
}
