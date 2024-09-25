package streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static streams.StreamTasks2.peopleUnder65Sorted;

public class PeopleUnder65Sorted {

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
    public void all() {
        var result = peopleUnder65Sorted(ALL);
        var expected = List.of(MARC, ROLF, MIKE, MELI, DIRK, LARA, NICO);
        assertEquals(expected, result);
    }

    @Test
    public void noneMatch() {
        var result = peopleUnder65Sorted(
                List.of(MARA, JOHN, OTTO, ERNA, LUKE));
        assertEquals(emptyList(), result);
    }

    @Test
    public void allMatch() {
        var result = peopleUnder65Sorted(
                List.of(ROLF, MARC, MELI, DIRK, NICO));
        var expected = List.of(MARC, ROLF, MELI, DIRK, NICO);
        assertEquals(expected, result);
    }

    @Test
    public void empty() {
        var result = peopleUnder65Sorted(emptyList());
        assertEquals(emptyList(), result);
    }
}
