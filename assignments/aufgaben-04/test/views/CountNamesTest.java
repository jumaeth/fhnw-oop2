package views;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountNamesTest {

    @Test
    public void testSome() {
        Map<String, PhoneNumber> phoneBook = new HashMap<>(Map.of(
                "Petra", new PhoneNumber("079"),
                "Franz", new PhoneNumber("076"),
                "Lea", new PhoneNumber("077"),
                "Notruf", new PhoneNumber("112")));
        String[] names = { "Petra", "Lea", "Pascal", "Paul" };
        int count = ViewTasks.countNames(phoneBook, names);
        assertEquals(2, count);
    }

    @Test
    public void testAll() {
        Map<String, PhoneNumber> phoneBook = new HashMap<>(Map.of(
                "Petra", new PhoneNumber("079"),
                "Franz", new PhoneNumber("076"),
                "Lea", new PhoneNumber("077"),
                "Notruf", new PhoneNumber("112")));
        String[] names = { "Petra", "Lea", "Notruf" };
        int count = ViewTasks.countNames(phoneBook, names);
        assertEquals(3, count);
    }

    @Test
    public void testNone() {
        Map<String, PhoneNumber> phoneBook = new HashMap<>(Map.of(
                "Petra", new PhoneNumber("079"),
                "Franz", new PhoneNumber("076"),
                "Lea", new PhoneNumber("077"),
                "Notruf", new PhoneNumber("112")));
        String[] names = { "Pascal", "Paul" };
        int count = ViewTasks.countNames(phoneBook, names);
        assertEquals(0, count);
    }
}
