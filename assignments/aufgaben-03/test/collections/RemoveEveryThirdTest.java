package collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveEveryThirdTest {

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
