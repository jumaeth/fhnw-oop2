package hotel;

public class Hotel {
    private final String name;
    private final int stars;

    public Hotel(String name, int stars) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return "*".repeat(stars) + " " + name;
    }
}
