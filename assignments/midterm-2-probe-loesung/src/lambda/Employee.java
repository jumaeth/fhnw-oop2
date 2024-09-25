package lambda;

import static java.util.Objects.requireNonNull;

public class Employee {
    private final String name;
    private double weekHours;

    public Employee(String name, double weekHours) {
        this.name = requireNonNull(name);
        this.weekHours = weekHours;
    }

    public String getName() {
        return name;
    }

    public double getWeekHours() {
        return weekHours;
    }

    public void setWeekHours(double weekHours) {
        this.weekHours = weekHours;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Employee other && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
