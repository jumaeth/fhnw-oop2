package streams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

@Timeout(value = 5, threadMode = SEPARATE_THREAD)
public class AgeOfFirstPositiveTest {

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
    public void allWithoutSort() {
        var first3 = StreamTasks2.ageOfFirstPositive(ALL.stream(), 3);
        assertNotNull(first3);
        assertEquals(Set.of(23, 57, 66), new HashSet<>(first3));

        var first4 = StreamTasks2.ageOfFirstPositive(ALL.stream(), 4);
        assertNotNull(first4);
        assertEquals(Set.of(23, 42, 57, 66), new HashSet<>(first4));

        var first5 = StreamTasks2.ageOfFirstPositive(ALL.stream(), 5);
        assertNotNull(first5);
        assertEquals(Set.of(23, 42, 57, 63, 66), new HashSet<>(first5));
    }

    @Test
    public void all() {
        var first3 = StreamTasks2.ageOfFirstPositive(ALL.stream(), 3);
        assertEquals(List.of(23, 57, 66), first3);

        var first4 = StreamTasks2.ageOfFirstPositive(ALL.stream(), 4);
        assertEquals(List.of(23, 42, 57, 66), first4);

        var first5 = StreamTasks2.ageOfFirstPositive(ALL.stream(), 5);
        assertEquals(List.of(23, 42, 57, 63, 66), first5);
    }

    @Test
    public void firstAreAllPositive() {
        var people = List.of(ROLF, JOHN, DIRK, MELI, ERNA, MARC);
        var first3 = StreamTasks2.ageOfFirstPositive(people.stream(), 3);
        assertEquals(List.of(23, 57, 66), first3);

        var first4 = StreamTasks2.ageOfFirstPositive(people.stream(), 4);
        assertEquals(List.of(23, 42, 57, 66), first4);
    }

    @Test
    public void tooFew() {
        var first = StreamTasks2.ageOfFirstPositive(ALL.stream(), 10);
        assertEquals(List.of(23, 42, 57, 63, 66, 66), first);
    }

    @Test
    public void tooFewAllPositive() {
        var people = List.of(ROLF, JOHN, DIRK, MELI);
        var first = StreamTasks2.ageOfFirstPositive(people.stream(), 10);
        assertEquals(List.of(23, 42, 57, 66), first);
    }

    @Test
    public void zero() {
        var first = StreamTasks2.ageOfFirstPositive(ALL.stream(), 0);
        assertEquals(List.of(), first);
    }

    @Test
    public void empty() {
        var first = StreamTasks2.ageOfFirstPositive(Stream.empty(), 5);
        assertEquals(List.of(), first);
    }

    @Test
    public void infinite() {
        var infinite = concat(ALL.stream(), generate(() -> MIKE));
        var first5 = StreamTasks2.ageOfFirstPositive(infinite, 5);
        assertEquals(List.of(23, 42, 57, 63, 66), first5);
    }
}
