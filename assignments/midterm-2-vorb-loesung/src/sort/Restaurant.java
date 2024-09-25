package sort;

import java.util.List;

public class Restaurant {
    private final String name;
    private final double starRating;
    private final int capacity;
    private final List<String> categories;

    public Restaurant(String name, double starRating, int capacity, List<String> categories) {
        this.name = name;
        this.starRating = starRating;
        this.capacity = capacity;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public double getStarRating() {
        return starRating;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return name + " (" + starRating + " / " + capacity + ")";
    }
}
