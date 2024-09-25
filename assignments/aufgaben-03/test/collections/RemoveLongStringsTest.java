package collections;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveLongStringsTest {

    @Test
    public void testSimple() {
        var strings = new HashSet<>(Set.of("Java", "programmers", "set", "list", "object-oriented"));
        var result = CollectionTasks.removeLongStrings(strings);
        assertEquals(Set.of("Java", "set", "list"), strings);
        assertEquals(Set.of("programmers", "object-oriented"), result);
    }

    @Test
    public void testNoneLong() {
        var strings = new HashSet<>(Set.of("Java", "programmer", "set", "list"));
        var result = CollectionTasks.removeLongStrings(strings);
        assertEquals(Set.of("Java", "programmer", "set", "list"), strings);
        assertEquals(emptySet(), result);
    }

    @Test
    public void testAllLong() {
        var strings = new HashSet<>(Set.of("programmers", "object-oriented", "Hello, World!"));
        var result = CollectionTasks.removeLongStrings(strings);
        assertEquals(emptySet(), strings);
        assertEquals(Set.of("programmers", "object-oriented", "Hello, World!"), result);
    }

    @Test
    public void testEmpty() {
        Set<String> strings = emptySet();
        var result = CollectionTasks.removeLongStrings(strings);
        assertEquals(emptySet(), strings);
        assertEquals(emptySet(), result);
    }
}
