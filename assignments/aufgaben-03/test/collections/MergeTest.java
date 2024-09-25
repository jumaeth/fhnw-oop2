package collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeTest {

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