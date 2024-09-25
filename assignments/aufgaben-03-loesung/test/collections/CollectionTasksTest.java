package collections;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionTasksTest {

    @Nested
    class RemoveLongStringsTest {

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

    @Nested
    class RemoveEveryThirdTest {

        @Test
        public void testEmpty() {
            List<Integer> list = List.of();
            CollectionTasks.removeEveryThird(list);
            assertEquals(List.of(), list);
        }

        @Test
        public void testNoRemove() {
            List<String> list = new ArrayList<>(List.of("Hello"));
            CollectionTasks.removeEveryThird(list);
            assertEquals(List.of("Hello"), list);

            list = new ArrayList<>(List.of("Hello", "World"));
            CollectionTasks.removeEveryThird(list);
            assertEquals(List.of("Hello", "World"), list);
        }

        @Test
        public void testRemove() {
            List<Integer> list = new ArrayList<>(List.of(0, 1, 2, 3, 4));
            CollectionTasks.removeEveryThird(list);
            assertEquals(List.of(0, 1, 3, 4), list);
        }

        @Test
        public void testRemoveLong() {
            List<Double> list = new ArrayList<>(List.of(1.0, 2.5, 3.1, 4.7, 5.0, 6.9, 7.4));
            CollectionTasks.removeEveryThird(list);
            assertEquals(List.of(1.0, 2.5, 4.7, 5.0, 7.4), list);
        }

        @Test
        public void testRemoveDuplicates() {
            List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 1, 2, 3, 1));
            CollectionTasks.removeEveryThird(list);
            assertEquals(List.of(1, 2, 1, 2, 1), list);
        }
    }

    @Nested
    class MergeTest {

        @Test
        public void testSameLength() {
            List<Integer> fst = List.of(10, 20, 30);
            List<Integer> snd = List.of(15, 25, 35);
            List<Integer> res = CollectionTasks.merge(fst, snd);
            assertEquals(List.of(10, 15, 20, 25, 30, 35), res);
        }

        @Test
        public void testFirstLonger() {
            List<Integer> fst = List.of(10, 20, 30, 40, 50);
            List<Integer> snd = List.of(15, 25, 35);
            List<Integer> res = CollectionTasks.merge(fst, snd);
            assertEquals(List.of(10, 15, 20, 25, 30, 35, 40, 50), res);
        }

        @Test
        public void testSecondLonger() {
            List<Integer> fst = List.of(10, 20, 30);
            List<Integer> snd = List.of(15, 25, 35, 45, 55);
            List<Integer> res = CollectionTasks.merge(fst, snd);
            assertEquals(List.of(10, 15, 20, 25, 30, 35, 45, 55), res);
        }

        @Test
        public void testFirstEmpty() {
            List<Integer> fst = List.of();
            List<Integer> snd = List.of(15, 25, 35);
            List<Integer> res = CollectionTasks.merge(fst, snd);
            assertEquals(List.of(15, 25, 35), res);
        }

        @Test
        public void testSecondEmpty() {
            List<Integer> fst = List.of(10, 20, 30);
            List<Integer> snd = List.of();
            List<Integer> res = CollectionTasks.merge(fst, snd);
            assertEquals(List.of(10, 20, 30), res);
        }

        @Test
        public void testBothEmpty() {
            List<Integer> fst = List.of();
            List<Integer> snd = List.of();
            List<Integer> res = CollectionTasks.merge(fst, snd);
            assertEquals(List.of(), res);
        }
    }

    @Nested
    class ReplaceTest {

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

    @Nested
    class CountDuplicatesTest {

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

    @Nested
    class RemoveTwinsTest {

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

    @Nested
    class RemoveDuplicatesTest {

        @Test
        public void testSimple() {
            List<Integer> list = new ArrayList<>(List.of(1, 2, 2, 3, 3, 3));
            CollectionTasks.removeDuplicates(list);
            assertEquals(List.of(1, 2, 3), list);
        }

        @Test
        public void testCorrectOrder() {
            List<Integer> list = new ArrayList<>(List.of(3, 2, 2, 1, 0, 1, 3, 2, 2));
            CollectionTasks.removeDuplicates(list);
            assertEquals(List.of(3, 2, 1, 0), list);
        }

        @Test
        public void testNoDuplicates() {
            List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
            CollectionTasks.removeDuplicates(list);
            assertEquals(List.of(1, 2, 3), list);
        }

        @Test
        public void testEmpty() {
            List<Integer> list = List.of();
            CollectionTasks.removeDuplicates(list);
            assertEquals(List.of(), list);
        }
    }

    @Nested
    class FindOccurrencesTest {

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
}
