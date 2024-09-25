package streams;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnonymizeTest {

    private static final List<String> NAMES = List.of("Marla", "Johnny", "Jean",
            "Lara", "Dominique", "Matt", "Lucas", "John", "Jean", "Lara",
            "Erna", "Marla", "Jim", "James", "Jim", "Jon", "Joe");

    @Test
    public void testSimple() {
        Set<String> critical = Set.of("Marla", "Jim", "Li");
        Stream<String> result = StreamTasks.anonymize(NAMES.stream(), critical);
        assertNotNull(result);
        List<String> expected = List.of("?????", "Johnny", "Jean",
                "Lara", "Dominique", "Matt", "Lucas", "John", "Jean", "Lara",
                "Erna", "?????", "???", "James", "???", "Jon", "Joe");
        assertEquals(expected, result.collect(toList()));
    }

    @Test
    public void testAllCritical() {
        Set<String> critical = new HashSet<>(NAMES);
        Stream<String> result = StreamTasks.anonymize(NAMES.stream(), critical);
        assertNotNull(result);
        List<String> expected = List.of("?????", "??????", "????", "????",
                "?????????", "????", "?????", "????", "????", "????", "????",
                "?????", "???", "?????", "???", "???", "???");
        assertEquals(expected, result.collect(toList()));
    }

    @Test
    public void testNoneCritical() {
        Stream<String> result = StreamTasks.anonymize(NAMES.stream(), emptySet());
        assertNotNull(result);
        assertEquals(NAMES, result.collect(toList()));
    }

    @Test
    public void testEmpty() {
        Set<String> critical = Set.of("Marla", "Jim", "Li");
        Stream<String> result = StreamTasks.anonymize(Stream.empty(), critical);
        assertNotNull(result);
        assertEquals(emptyList(), result.collect(toList()));
    }
}
