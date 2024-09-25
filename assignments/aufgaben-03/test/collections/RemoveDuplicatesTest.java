package collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveDuplicatesTest {

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
