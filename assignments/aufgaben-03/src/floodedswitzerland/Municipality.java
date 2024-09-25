package floodedswitzerland;

import java.util.Objects;

/**
 * Represents a municipality in Switzerland. Two municipalities are considered
 * equal if they have the same name, population, and area.
 * <p>
 * This class should be immutable (all fields final) and contain simple getters
 * named <code>name()</code>, <code>population()</code>, and <code>area()</code>.
 */
public class Municipality {
    private final String name;
    private final int population;
    private final double area;

    public Municipality(String name, int population, double area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }

    public String name() {
        return name;
    }

    public int population() {
        return population;
    }

    public double area() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Municipality that = (Municipality) o;
        return population == that.population && Double.compare(area, that.area) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, population, area);
    }
}
