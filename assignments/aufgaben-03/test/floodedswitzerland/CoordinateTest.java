package floodedswitzerland;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {

    @Test
    void constructorGetter() {
        var coordinate = new Coordinate(123, 456);
        assertEquals(123, coordinate.east());
        assertEquals(456, coordinate.north());

        coordinate = new Coordinate(789, 101112);
        assertEquals(789, coordinate.east());
        assertEquals(101112, coordinate.north());
    }

    @Test
    void constructorIllegal() {
        var coordinate = new Coordinate(0, 0);
        assertEquals(0, coordinate.east());
        assertEquals(0, coordinate.north());

        assertThrows(IllegalArgumentException.class, () -> new Coordinate(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Coordinate(0, -1));
    }

    @Test
    void equals() {
        var coordinate = new Coordinate(123, 456);
        assertTrue(coordinate.equals(coordinate));

        var other = new Coordinate(123, 456);
        assertTrue(coordinate.equals(other));

        other = new Coordinate(123, 457);
        assertFalse(coordinate.equals(other));

        other = new Coordinate(124, 456);
        assertFalse(coordinate.equals(other));
    }

    @Test
    void equalsNullOrOtherType() {
        var coordinate = new Coordinate(123, 456);
        assertFalse(coordinate.equals(null));
        assertFalse(coordinate.equals("123, 456"));
        assertFalse(coordinate.equals(123));
        assertFalse(coordinate.equals(123.456));
    }

    @Test
    void toStringTest() {
        assertEquals("123.0 / 456.0", new Coordinate(123, 456).toString());
        assertEquals("789.0 / 101112.0", new Coordinate(789, 101112).toString());
    }
}
