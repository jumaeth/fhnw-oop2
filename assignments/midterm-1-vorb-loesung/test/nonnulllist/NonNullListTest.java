package nonnulllist;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NonNullListTest {

    @Test
    void typeParameter() {
        var strings = new NonNullList<String>();
        var integers = new NonNullList<Integer>();
        var characters = new NonNullList<Character>();
    }

    @Test
    void noArgsConstructorSize() {
        var strings = new NonNullList<String>();
        assertEquals(0, strings.size());
    }

    @Test
    void listConstructorSize() {
        var strings = new NonNullList<>(List.of("One", "Two", "Three"));
        assertEquals(3, strings.size());

        var characters = new NonNullList<>(List.of('a', 'b', 'c', 'd'));
        assertEquals(4, characters.size());
    }

    @Test
    void addSize() {
        var strings = new NonNullList<String>();
        strings.add("One");
        assertEquals(1, strings.size());

        strings.add("Two");
        assertEquals(2, strings.size());

        strings.add("Three");
        assertEquals(3, strings.size());

        var integers = new NonNullList<Integer>();
        integers.add(1);
        assertEquals(1, integers.size());

        integers.add(2);
        assertEquals(2, integers.size());
    }

    @Test
    void get() {
        var strings = new NonNullList<String>();
        strings.add("One");
        strings.add("Two");
        strings.add("Three");
        assertEquals("One", strings.get(0));
        assertEquals("Two", strings.get(1));
        assertEquals("Three", strings.get(2));

        var doubles = new NonNullList<Double>();
        doubles.add(1.0);
        doubles.add(2.0);

        assertEquals(1.0, doubles.get(0));
        assertEquals(2.0, doubles.get(1));
    }

    @Test
    void addNull() {
        var strings = new NonNullList<String>();
        strings.add("One");
        strings.add("Two");
        strings.add("Three");
        assertThrows(NullPointerException.class, () -> {
            strings.add(null);
        });
    }

    @Test
    void listConstructorNull() {
        assertThrows(NullPointerException.class, () -> {
            new NonNullList<>(null);
        });
    }

    @Test
    void listConstructorNulls() {
        var strings = new ArrayList<String>();
        strings.add("One");
        strings.add("Two");
        strings.add(null);
        assertThrows(NullPointerException.class, () -> {
            new NonNullList<>(strings);
        });

        var integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(null);
        integers.add(5);
        assertThrows(NullPointerException.class, () -> {
            new NonNullList<>(integers);
        });
    }
}
