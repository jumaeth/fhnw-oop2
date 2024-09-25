package smartcampus;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(OrderAnnotation.class)
public class AllOpeningHoursTest {

    @Order(1)
    @Test
    void timeOfDayEquals() {
        var first = new TimeOfDay(12, 0);
        var second = new TimeOfDay(12, 0);
        assertEquals(first, second);
        assertEquals(second, first);

        var third = new TimeOfDay(12, 1);
        var fourth = new TimeOfDay(13, 0);
        assertNotEquals(first, third);
        assertNotEquals(third, first);
        assertNotEquals(first, fourth);
        assertNotEquals(fourth, first);
        assertNotEquals(third, fourth);
        assertNotEquals(fourth, third);

        var fifth = new TimeOfDay(13, 0);
        assertEquals(fourth, fifth);
        assertEquals(fifth, fourth);
    }

    @Order(2)
    @Test
    void timeOfDayWorksWithHashSet() {
        var times = new HashSet<TimeOfDay>();
        times.add(new TimeOfDay(8, 30));
        assertEquals(1, times.size());

        times.add(new TimeOfDay(8, 45));
        assertEquals(2, times.size());

        times.add(new TimeOfDay(9, 30));
        assertEquals(3, times.size());

        times.add(new TimeOfDay(8, 30)); // same as above, so should not be added
        assertEquals(3, times.size());

        times.add(new TimeOfDay(8, 45)); // same as above, so should not be added
        assertEquals(3, times.size());

        times.add(new TimeOfDay(9, 30)); // same as above, so should not be added
        times.add(new TimeOfDay(9, 30)); // same as above, so should not be added
        assertEquals(3, times.size());

        times.add(new TimeOfDay(12, 30));
        assertEquals(4, times.size());

        times.remove(new TimeOfDay(8, 30));
        assertEquals(3, times.size());

        times.remove(new TimeOfDay(8, 30));
        assertEquals(3, times.size());

        times.remove(new TimeOfDay(8, 45));
        times.remove(new TimeOfDay(9, 30));
        assertEquals(1, times.size());

        times.remove(new TimeOfDay(12, 30));
        assertEquals(0, times.size());
    }

    @Order(3)
    @Test
    void allOpeningHoursNoDuplicates() {
        var bibliothek = new Room("Bibliothek");
        bibliothek.setOpeningTime(new TimeOfDay(8, 30));
        var mensa = new Room("Mensa");
        mensa.setOpeningTime(new TimeOfDay(11, 30));
        var sekretariat = new Room("Sekretariat");
        sekretariat.setOpeningTime(new TimeOfDay(8, 45));
        var campus = new Campus(List.of(bibliothek, mensa, sekretariat));

        HashSet<TimeOfDay> result = campus.allOpeningHours();
        assertEquals(Set.of(
                        new TimeOfDay(8, 30),
                        new TimeOfDay(8, 45),
                        new TimeOfDay(11, 30)), result);

        bibliothek.setOpeningTime(new TimeOfDay(9, 0));
        result = campus.allOpeningHours();
        assertEquals(Set.of(
                        new TimeOfDay(8, 45),
                        new TimeOfDay(9, 0),
                        new TimeOfDay(11, 30)), result);
    }

    @Order(4)
    @Test
    void allOpeningHoursDuplicates() {
        var bibliothek = new Room("Bibliothek");
        bibliothek.setOpeningTime(new TimeOfDay(8, 30));
        var mensa = new Room("Mensa");
        mensa.setOpeningTime(new TimeOfDay(11, 30));
        var sekretariat = new Room("Sekretariat");
        sekretariat.setOpeningTime(new TimeOfDay(8, 30));
        var campus = new Campus(List.of(bibliothek, mensa, sekretariat));

        HashSet<TimeOfDay> result = campus.allOpeningHours();
        assertEquals(Set.of(
                        new TimeOfDay(8, 30),
                        new TimeOfDay(11, 30)), result);

        sekretariat.setOpeningTime(new TimeOfDay(9, 0));
        result = campus.allOpeningHours();
        assertEquals(Set.of(
                        new TimeOfDay(8, 30),
                        new TimeOfDay(9, 0),
                        new TimeOfDay(11, 30)), result);

        sekretariat.setOpeningTime(new TimeOfDay(8, 30));
        mensa.setOpeningTime(new TimeOfDay(8, 30));
        result = campus.allOpeningHours();
        assertEquals(Set.of(new TimeOfDay(8, 30)), result);
    }

    @Order(5)
    @Test
    void allOpeningHoursUnset() {
        var bibliothek = new Room("Bibliothek");
        bibliothek.setOpeningTime(new TimeOfDay(8, 30));
        var mensa = new Room("Mensa");
        mensa.setOpeningTime(new TimeOfDay(11, 30));
        var sekretariat = new Room("Sekretariat");
        sekretariat.setOpeningTime(new TimeOfDay(8, 30));
        var studiensaal = new Room("Studiensaal"); // opening time not set
        var campus = new Campus(List.of(bibliothek, mensa, sekretariat, studiensaal));

        HashSet<TimeOfDay> result = campus.allOpeningHours();
        assertEquals(Set.of(
                        new TimeOfDay(8, 30),
                        new TimeOfDay(11, 30)), result);

        studiensaal.setOpeningTime(new TimeOfDay(9, 0));
        result = campus.allOpeningHours();
        assertEquals(Set.of(
                        new TimeOfDay(8, 30),
                        new TimeOfDay(9, 0),
                        new TimeOfDay(11, 30)), result);

        bibliothek.setOpeningTime(null);
        mensa.setOpeningTime(null);
        sekretariat.setOpeningTime(null);
        result = campus.allOpeningHours();
        assertEquals(Set.of(new TimeOfDay(9, 0)), result);
    }

    @Order(6)
    @Test
    void allOpeningHoursNoRoomsOrAllUnset() {
        var campus = new Campus(List.of());
        HashSet<TimeOfDay> result = campus.allOpeningHours();
        assertEquals(Set.of(), result);

        var bibliothek = new Room("Bibliothek");
        var mensa = new Room("Mensa");
        var sekretariat = new Room("Sekretariat");
        campus = new Campus(List.of(bibliothek, mensa, sekretariat));
        assertEquals(Set.of(), campus.allOpeningHours());

        // also test that the method returns some non-empty set
        sekretariat.setOpeningTime(new TimeOfDay(8, 45));
        result = campus.allOpeningHours();
        assertNotEquals(Set.of(), result);
    }
}
