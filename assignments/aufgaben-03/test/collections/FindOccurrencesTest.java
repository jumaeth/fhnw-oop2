package collections;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindOccurrencesTest {

    @Test
    public void testSimple() {
        String text = "hello world how are you\n"
                + "world are you ok\n"
                + "\n"
                + "how on earth are you";
        Map<String, Set<Integer>> expected = Map.of(
                "hello", Set.of(1),
                "world", Set.of(1, 2),
                "how", Set.of(1, 4),
                "are", Set.of(1, 2, 4),
                "you", Set.of(1, 2, 4),
                "ok", Set.of(2),
                "on", Set.of(4),
                "earth", Set.of(4));
        Map<String, Set<Integer>> res = CollectionTasks.findOccurrences(text);
        assertEquals(expected, res);
    }

    @Test
    public void testMultipleSpaces() {
        String text = "hello   world how are you\n"
                + "  world are you    ok\n"
                + "\n"
                + "how on earth are    you ";
        Map<String, Set<Integer>> expected = Map.of(
                "hello", Set.of(1),
                "world", Set.of(1, 2),
                "how", Set.of(1, 4),
                "are", Set.of(1, 2, 4),
                "you", Set.of(1, 2, 4),
                "ok", Set.of(2),
                "on", Set.of(4),
                "earth", Set.of(4));
        Map<String, Set<Integer>> res = CollectionTasks.findOccurrences(text);
        assertEquals(expected, res);
    }

    @Test
    public void testOneLine() {
        String text = "hello hello hello world";
        Map<String, Set<Integer>> expected = Map.of(
                "hello", Set.of(1),
                "world", Set.of(1));
        Map<String, Set<Integer>> res = CollectionTasks.findOccurrences(text);
        assertEquals(expected, res);
    }

    @Test
    public void testBlankLines() {
        String text = "\n\n\n  \n";
        Map<String, Set<Integer>> res = CollectionTasks.findOccurrences(text);
        assertEquals(Map.of(), res);
    }

    @Test
    public void testEmpty() {
        String text = "";
        Map<String, Set<Integer>> res = CollectionTasks.findOccurrences(text);
        assertEquals(Map.of(), res);
    }
}