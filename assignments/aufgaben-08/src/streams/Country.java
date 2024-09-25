package streams;

import static java.util.Objects.requireNonNull;

public class Country {
    private final String code;
    private final String name;
    private final int area;         // km²
    private final int population;
    private final boolean landlocked;

    public Country(String code, String name, int area, int population, boolean landlocked) {
        this.code = requireNonNull(code);
        this.name = requireNonNull(name);
        this.area = area;
        this.population = population;
        this.landlocked = landlocked;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getArea() {
        return area;
    }

    public int getPopulation() {
        return population;
    }

    public boolean isLandlocked() {
        return landlocked;
    }

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Country country && code.equals(country.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
