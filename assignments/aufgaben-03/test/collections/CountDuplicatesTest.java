package collections;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountDuplicatesTest {

    @Test
    public void testSimple() {
        Map<String, Integer> map = Map.of("hello", 42, "world", -3,
                "programming", 4, "bla", -3, "blu", -2, "bli", 4, "blo", 4);
        int res = CollectionTasks.countDuplicates(map);
        assertEquals(3, res);
    }

    @Test
    public void testNoDuplicates() {
        Map<String, Integer> map = Map.of("hello", 42, "world", -3,
                "programming", 4, "java", 5, "oop2", 0);
        int res = CollectionTasks.countDuplicates(map);
        assertEquals(0, res);
    }

    @Test
    public void testAllSameValues() {
        Map<String, Integer> map = Map.of("hello", 42, "world", 42,
                "programming", 42, "hi", 42, "bye", 42, "!", 42);
        int res = CollectionTasks.countDuplicates(map);
        assertEquals(5, res);
    }

    @Test
    public void testEmpty() {
        Map<String, Integer> map = Map.of();
        int res = CollectionTasks.countDuplicates(map);
        assertEquals(0, res);
    }
}