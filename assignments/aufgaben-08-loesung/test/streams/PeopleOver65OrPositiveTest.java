package streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeopleOver65OrPositiveTest {

    private static final Person ROLF = new Person("Rolf", 23, true);
    private static final Person MIKE = new Person("Mike", 34, false);
    private static final Person MARA = new Person("Mara", 67, false);
    private static final Person JOHN = new Person("John", 66, true);
    private static final Person LARA = new Person("Lara", 61, false);
    private static final Person DIRK = new Person("Dirk", 57, true);
    private static final Person OTTO = new Person("Otto", 72, false);
    private static final Person MELI = new Person("Meli", 42, true);
    private static final Person NICO = new Person("Nico", 63, true);
    private static final Person ERNA = new Person("Erna", 68, false);
    private static final Person MARC = new Person("Marc", 13, false);
    private static final Person LUKE = new Person("Luke", 66, true);

    private static final List<Person> ALL = List.of(ROLF, MIKE, MARA, JOHN,
            LARA, DIRK, OTTO, MELI, NICO, ERNA, MARC, LUKE);

    @Test
    public void testOver65() {
        var people = List.of(ROLF, JOHN, DIRK, MELI, NICO, LUKE);
        var result = StreamTasks.peopleOver65OrPositive(people);
        var expected = List.of("Rolf", "John", "Dirk", "Meli", "Nico", "Luke");
        assertEquals(expected, result);
    }

    @Test
    public void testPositive() {
        var people = List.of(MARA, JOHN, OTTO, ERNA, LUKE);
        var result = StreamTasks.peopleOver65OrPositive(people);
        var expected = List.of("Mara", "John", "Otto", "Erna", "Luke");
        assertEquals(expected, result);
    }

    @Test
    public void testAll() {
        var result = StreamTasks.peopleOver65OrPositive(ALL);
        var expected = List.of("Rolf", "Mara", "John", "Dirk", "Otto",
                "Meli", "Nico", "Erna", "Luke");
        assertEquals(expected, result);
    }

    @Test
    public void testNoMatch() {
        var people = List.of(MIKE, LARA, MARC);
        var result = StreamTasks.peopleOver65OrPositive(people);
        assertEquals(emptyList(), result);
    }

    @Test
    public void testAllMatch() {
        var people = List.of(ROLF, MARA, JOHN, DIRK, OTTO, MELI, NICO, ERNA, LUKE);
        var result = StreamTasks.peopleOver65OrPositive(people);
        var expected = List.of("Rolf", "Mara", "John", "Dirk", "Otto",
                "Meli", "Nico", "Erna", "Luke");
        assertEquals(expected, result);
    }

    @Test
    public void testEmpty() {
        var result = StreamTasks.peopleOver65OrPositive(emptyList());
        assertEquals(emptyList(), result);
    }
}
