package lambdas;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaTasksTest {

    @Test
    public void testRemoveLongStrings() {
        var set = new HashSet<>(Set.of("hello", "programming", "world", "!"));
        LambdaTasks.removeLongStrings(set, 5);
        assertEquals(Set.of("hello", "world", "!"), set);

        LambdaTasks.removeLongStrings(set, 3);
        assertEquals(Set.of("!"), set);

        LambdaTasks.removeLongStrings(set, 1);
        assertEquals(Set.of("!"), set);

        LambdaTasks.removeLongStrings(set, 0);
        assertEquals(Set.of(), set);
    }

    @Test
    public void testPrintList() {
        var list = List.of(3, 4, 1, 8, 3, 1);
        var bytes = new ByteArrayOutputStream();
        LambdaTasks.printList(list, new PrintStream(bytes));
        var lines = bytes.toString().lines().collect(toList());
        assertEquals(List.of("3", "4", "1", "8", "3", "1"), lines);
    }

    @Test
    public void testReplaceWeekends() {
        var dates = new ArrayList<>(List.of(
                LocalDate.of(2023, 5, 15),      // mon
                LocalDate.of(2023, 5, 16),      // tue
                LocalDate.of(2023, 5, 17),      // wed
                LocalDate.of(2023, 5, 18),      // thu
                LocalDate.of(2023, 5, 19),      // fri
                LocalDate.of(2023, 5, 20),      // sat
                LocalDate.of(2023, 5, 21),      // sun
                LocalDate.of(2023, 5, 22)));    // mon
        LambdaTasks.replaceWeekends(dates);
        var expected = List.of(
                LocalDate.of(2023, 5, 15),
                LocalDate.of(2023, 5, 16),
                LocalDate.of(2023, 5, 17),
                LocalDate.of(2023, 5, 18),
                LocalDate.of(2023, 5, 19),
                LocalDate.of(2023, 5, 22),      // !
                LocalDate.of(2023, 5, 22),      // !
                LocalDate.of(2023, 5, 22));
        assertEquals(expected, dates);
    }

    @Test
    public void testIncrementAll() {
        var map = new HashMap<>(Map.of(
                "foo", 3,
                "bar", 1,
                "baz", 20,
                "meh", 0,
                "bla", 2));
        LambdaTasks.incrementAll(map);
        var expected = Map.of(
                "foo", 4,
                "bar", 2,
                "baz", 21,
                "meh", 1,
                "bla", 3);
        assertEquals(expected, map);
    }

    @Test
    public void testFrequencies() {
        var words = List.of("me", "and", "me", "friends",
                "me", "too", "and", "you", "too");
        var frequencies = LambdaTasks.frequencies(words);
        var expected = Map.of(
                "me", 3,
                "and", 2,
                "friends", 1,
                "too", 2,
                "you", 1);
        assertEquals(expected, frequencies);
    }

    @Test
    public void testSortByLength() {
        var list = new ArrayList<>(List.of(
                "short", "pretty long", "tiny", "longish", "suuuuuper long"));
        LambdaTasks.sortByLength(list);
        var exprected = List.of(
                "tiny", "short", "longish", "pretty long", "suuuuuper long");
        assertEquals(exprected , list);
    }

    private final Person priska = new Person("Priska", "Müller");
    private final Person michael = new Person("Michael", "Meier");
    private final Person rudolf = new Person("Rudolf", "Schneider");
    private final Person sarah = new Person("Sarah", "Ackerfrau");

    private final Person andrea = new Person("Andrea", "Ackerfrau");
    private final Person walter = new Person("Walter", "Müller");

    @Test
    public void testSortByLastThenFirstNameSimple() {
        var list = new ArrayList<>(List.of(priska, michael, rudolf, sarah));
        LambdaTasks.sortByLastThenFirstName(list);
        var expected = List.of(sarah, michael, priska, rudolf);
        assertEquals(expected, list);
    }

    @Test
    public void testSortByLastThenFirstNameDuplicates() {
        var list = new ArrayList<>(List.of(
                priska, michael, rudolf, sarah, priska, sarah, sarah));
        LambdaTasks.sortByLastThenFirstName(list);
        var expected = List.of(
                sarah, sarah, sarah, michael, priska, priska, rudolf);
        assertEquals(expected, list);
    }

    @Test
    public void testSortByLastThenFirstNameSameLast() {
        var list = new ArrayList<>(List.of(
                walter, priska, michael, rudolf, sarah, andrea));
        LambdaTasks.sortByLastThenFirstName(list);
        var expected = List.of(andrea, sarah, michael, priska, walter, rudolf);
        assertEquals(expected, list);
    }
}
