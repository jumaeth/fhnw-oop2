package floodedswitzerland;

/**
 * A coordinate in the CH1903 coordinate system. Two coordinates are considered
 * equal if they have the same east (x) and north (y) values.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Swiss_coordinate_system">Swiss coordinate system</a>
 */
public class Coordinate {

    private final double east;
    private final double north;

    public Coordinate(double east, double north) {
        if (east < 0 || north < 0) {
            throw new IllegalArgumentException();
        }
        this.east = east;
        this.north = north;
    }

    public double east() {
        return east;
    }

    public double north() {
        return north;
    }

    @Override
    public String toString() {
        return east + " / " + north;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Coordinate c
               && east == c.east
               && north == c.north;
    }
}
