package collections;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReplaceTest {

    @Test
    public void testSimple() {
        Set<String> words = new HashSet<>(
                Set.of("Hello", "Programming", "World", "!"));
        CollectionTasks.replace(words);
        assertEquals(Set.of("hello", "programming", "world", "!"), words);
    }

    @Test
    public void testEmpty() {
        Set<String> words = new HashSet<>();
        CollectionTasks.replace(words);
        assertEquals(Set.of(), words);
    }
}