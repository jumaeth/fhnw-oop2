package floodedswitzerland;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MunicipalityTest {

    @Test
    void testEquals() {
        Municipality m = new Municipality("Bern", 133798, 51.6);

        assertEquals(m, new Municipality("Bern", 133798, 51.6));
        assertNotEquals(m, new Municipality("Bern", 444222, 15.4));
    }

    @Test
    public void testEqualsName() {
        Municipality municipality1 = new Municipality("City1", 10000, 50.5);
        Municipality municipality2 = new Municipality("City2", 10000, 50.5);
        assertNotEquals(municipality1, municipality2);
    }

    @Test
    public void testEqualsPopulation() {
        Municipality municipality1 = new Municipality("City", 999, 50.5);
        Municipality municipality2 = new Municipality("City", 1000, 50.5);
        assertNotEquals(municipality1, municipality2);
    }

    @Test
    public void testEqualsArea() {
        Municipality municipality1 = new Municipality("City", 10000, 50.5);
        Municipality municipality2 = new Municipality("City", 10000, 44.5);
        assertNotEquals(municipality1, municipality2);
    }

    @Test
    public void testEqualsNull() {
        Municipality municipality = new Municipality("City", 10000, 50.5);
        assertNotEquals(null, municipality);
    }

    @Test
    public void testDifferentClass() {
        Municipality municipality = new Municipality("City", 10000, 50.5);
        Object obj = new Object();
        assertNotEquals(municipality, obj);
    }

    @Test
    void testHashCode() {
        Municipality m = new Municipality("Bern", 133798, 51.6);

        assertEquals(m.hashCode(), new Municipality("Bern", 133798, 51.6).hashCode());
        assertNotEquals(m.hashCode(), new Municipality("City", 333333, 22.4).hashCode());
    }

    @Test
    void testHashCodeName() {
        Municipality m = new Municipality("Bern", 133798, 51.6);

        assertNotEquals(m.hashCode(), new Municipality("City", 133798, 51.6).hashCode());
    }

    @Test
    void testHashCodePopulation() {
        Municipality m = new Municipality("Bern", 133798, 51.6);

        assertNotEquals(m.hashCode(), new Municipality("Bern", 444444, 51.6).hashCode());
    }

    @Test
    void testHashCodeArea() {
        Municipality m = new Municipality("Bern", 133798, 51.6);

        assertNotEquals(m.hashCode(), new Municipality("Bern", 133798, 533.6).hashCode());
    }


    @Test
    void testConstructor() {
        Municipality m = new Municipality("Bern", 133798, 51.6);

        assertEquals(m.name(), "Bern");
        assertEquals(m.population(), 133798);
        assertEquals(m.area(), 51.6);
    }
}
