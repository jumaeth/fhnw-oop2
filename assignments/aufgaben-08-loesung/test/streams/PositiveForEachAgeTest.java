package streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PositiveForEachAgeTest {

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
    private static final Person MATT = new Person("Matt", 23, true);
    private static final Person LILI = new Person("Lili", 23, true);
    private static final Person KARL = new Person("Karl", 34, true);

    private static final List<Person> ALL = List.of(ROLF, MIKE, MARA, JOHN,
            LARA, DIRK, OTTO, MELI, NICO, ERNA, MARC, LUKE, MATT, LILI, KARL);

    @Test
    public void testAll() {
        var map = StreamTasks.positiveForEachAge(ALL);
        assertNotNull(map);
        var mapWithInts = map.entrySet().stream()
                .collect(toMap(e -> e.getKey(), e -> e.getValue().intValue()));
        var expected = Map.of(
                23, 3,
                66, 2,
                57, 1,
                42, 1,
                63, 1,
                34, 1);
        assertEquals(expected, mapWithInts);
    }

    @Test
    public void testSome() {
        var some = List.of(ROLF, MIKE, MARA, JOHN, LARA, DIRK, OTTO);
        var map = StreamTasks.positiveForEachAge(some);
        assertNotNull(map);
        var mapWithInts = map.entrySet().stream()
                .collect(toMap(e -> e.getKey(), e -> e.getValue().intValue()));
        var expected = Map.of(
                23, 1,
                66, 1,
                57, 1);
        assertEquals(expected, mapWithInts);
    }

    @Test
    public void testAllPositive() {
        var map = StreamTasks.positiveForEachAge(
                List.of(ROLF, JOHN, DIRK, MELI, NICO, LUKE, MATT, LILI, KARL));
        assertNotNull(map);
        var mapWithInts = map.entrySet().stream()
                .collect(toMap(e -> e.getKey(), e -> e.getValue().intValue()));
        var expected = Map.of(
                23, 3,
                66, 2,
                57, 1,
                42, 1,
                63, 1,
                34, 1);
        assertEquals(expected, mapWithInts);
    }

    @Test
    public void testNonePositive() {
        var map = StreamTasks.positiveForEachAge(List.of(MIKE, MARA, LARA, OTTO));
        assertEquals(emptyMap(), map);
    }

    @Test
    public void testEmpty() {
        var map = StreamTasks.positiveForEachAge(emptyList());
        assertEquals(emptyMap(), map);
    }
}
