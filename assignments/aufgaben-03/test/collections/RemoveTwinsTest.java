package collections;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveTwinsTest {

    @Test
    public void testSimple() {
        Map<String, String> map = new HashMap<>(Map.of(
                "hello", "world",
                "hi", "bye",
                "hey", "hey",
                "no", "no",
                "yes", "no"));
        Map<String, String> expected = Map.of(
                "hello", "world",
                "hi", "bye",
                "yes", "no");
        CollectionTasks.removeTwins(map);
        assertEquals(expected, map);
    }

    @Test
    public void testNoTwins() {
        Map<String, String> map = new HashMap<>(Map.of(
                "hello", "world",
                "hi", "bye",
                "yes", "no"));
        Map<String, String> expected = Map.of(
                "hello", "world",
                "hi", "bye",
                "yes", "no");
        CollectionTasks.removeTwins(map);
        assertEquals(expected, map);
    }

    @Test
    public void testAllTwins() {
        Map<String, String> map = new HashMap<>(Map.of(
                "hello", "hello",
                "hi", "hi",
                "yes", "yes"));
        CollectionTasks.removeTwins(map);
        assertEquals(Map.of(), map);
    }

    @Test
    public void testEmpty() {
        Map<String, String> map = Map.of();
        CollectionTasks.removeTwins(map);
        assertEquals(Map.of(), map);
    }
}