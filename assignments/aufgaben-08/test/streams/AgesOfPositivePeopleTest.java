package streams;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgesOfPositivePeopleTest {

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

    private static final Person[] ALL = {ROLF, MIKE, MARA, JOHN,
            LARA, DIRK, OTTO, MELI, NICO, ERNA, MARC, LUKE};

    @Test
    public void testOnlyPositive() {
        Person[] people = {ROLF, JOHN, DIRK, MELI, NICO, LUKE};
        var result = StreamTasks.agesOfPositivePeople(people);
        var expected = Set.of(23, 66, 57, 42, 63);
        assertEquals(expected, result);
    }

    @Test
    public void testAll() {
        var result = StreamTasks.agesOfPositivePeople(ALL);
        var expected = Set.of(23, 66, 57, 42, 63);
        assertEquals(expected, result);
    }

    @Test
    public void testNoMatch() {
        Person[] people = {MIKE, LARA, OTTO, ERNA, MARC};
        var result = StreamTasks.agesOfPositivePeople(people);
        assertEquals(emptySet(), result);
    }

    @Test
    public void testEmpty() {
        var result = StreamTasks.agesOfPositivePeople(new Person[0]);
        assertEquals(emptySet(), result);
    }
}
