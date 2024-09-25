package reporting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportingListTest {

    @Test
    void basicsString() {
        var list = new ReportingList<String>();
        assertEquals(0, list.size());

        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals(3, list.size());

        String first = list.get(0);
        String second = list.get(1);
        String third = list.get(2);

        assertEquals("A", first);
        assertEquals("B", second);
        assertEquals("C", third);

        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void basicsInteger() {
        var list = new ReportingList<Integer>();
        assertEquals(0, list.size());

        list.add(1);
        list.add(10);
        list.add(100);

        assertEquals(3, list.size());

        int first = list.get(0);
        int second = list.get(1);
        int third = list.get(2);

        assertEquals(1, first);
        assertEquals(10, second);
        assertEquals(100, third);
    }

    @Test
    void onAddedString() {
        var list = new ReportingList<String>();
        var log = new String[] {""};
        list.setOnAdded((index, element) -> {
            log[0] += index + ": " + element + "\n";
        });

        list.add("Hello");
        list.add("World");
        list.add("...");
        list.add("Good byte!");

        assertEquals(log[0], """
                0: Hello
                1: World
                2: ...
                3: Good byte!
                """);
    }

    @Test
    void onAddedInteger() {
        var list = new ReportingList<Integer>();
        var sum = new int[1];
        list.setOnAdded((index, element) -> {
            sum[0] += element; // element should be of type Integer
        });

        list.add(2);
        list.add(7);
        list.add(1);

        assertEquals(10, sum[0]);
    }

    @Test
    void onCleared() {
        var list = new ReportingList<String>();
        list.add("A");
        list.add("B");
        list.add("C");

        var count = new int[1];
        list.setOnCleared(() -> {
            count[0]++;
        });

        list.clear();

        assertEquals(1, count[0]);

        list.add("D");
        list.clear();

        assertEquals(2, count[0]);

        list.clear();
        list.clear();
        list.clear();

        assertEquals(5, count[0]);
    }
}
