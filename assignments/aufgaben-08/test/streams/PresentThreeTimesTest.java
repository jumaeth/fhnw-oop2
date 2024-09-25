package streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PresentThreeTimesTest {

    private static final List<String> THREE = List.of("Mara", "Mike", "John",
            "Jeff", "Mara", "John", "Jeff", "Dirk", "John", "Mike", "Mara",
            "Luke", "Luke", "John", "Erna", "Mike");

    private static final List<String> EXACTLY_THREE = List.of("Mara", "Mike",
            "Jeff", "Mara", "John", "Jeff", "Dirk", "John", "Mike", "Mara",
            "Luke", "Luke", "John", "Erna", "Mike");

    private static final List<String> ONE = List.of("Mike", "John", "Jeff",
            "Mara", "John", "Jeff", "Dirk", "John", "Mike", "Mara", "Luke",
            "Luke", "John", "Erna");

    private static final List<String> NONE = List.of("Mike", "John", "Jeff",
            "Mara", "Jeff", "Dirk", "John", "Mike", "Mara", "Luke", "Luke");

    @Test
    public void testSimple() {
        var result = StreamTasks.presentThreeTimes(THREE);
        assertEquals(Set.of("Mara", "John", "Mike"), result);
    }

    @Test
    public void testExactlyThreeTimes() {
        var result = StreamTasks.presentThreeTimes(EXACTLY_THREE);
        assertEquals(Set.of("Mara", "John", "Mike"), result);
    }

    @Test
    public void testOnlyOne() {
        var result = StreamTasks.presentThreeTimes(ONE);
        assertEquals(Set.of("John"), result);
    }

    @Test
    public void testNone() {
        var result = StreamTasks.presentThreeTimes(NONE);
        assertEquals(emptySet(), result);
    }

    @Test
    public void testEmpty() {
        var result = StreamTasks.presentThreeTimes(emptyList());
        assertEquals(emptySet(), result);
    }
}
