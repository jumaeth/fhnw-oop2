package views;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuplicateNumbersTest {

    @Test
    public void testSimple() {
        Map<String, PhoneNumber> phoneBook = new HashMap<>(Map.of(
                "Petra", new PhoneNumber("079"),
                "Franz", new PhoneNumber("076"),
                "Lea", new PhoneNumber("077"),
                "Fritz", new PhoneNumber("076"),
                "Andrea", new PhoneNumber("079"),
                "Notruf", new PhoneNumber("112")));
        Set<PhoneNumber> duplicates = ViewTasks.duplicateNumbers(phoneBook);
        assertEquals(Set.of(new PhoneNumber("079"), new PhoneNumber("076")), duplicates);
    }

    @Test
    public void testMore() {
        Map<String, PhoneNumber> phoneBook = new HashMap<>(Map.of(
                "Petra", new PhoneNumber("079"),
                "Franz", new PhoneNumber("076"),
                "Lea", new PhoneNumber("077"),
                "Anna", new PhoneNumber("078"),
                "Fritz", new PhoneNumber("076"),
                "Andrea", new PhoneNumber("079"),
                "Notruf", new PhoneNumber("112"),
                "Polizei", new PhoneNumber("117"),
                "Feuerwehr", new PhoneNumber("118"),
                "Peter", new PhoneNumber("078")));
        Set<PhoneNumber> duplicates = ViewTasks.duplicateNumbers(phoneBook);
        assertEquals(Set.of(new PhoneNumber("079"), new PhoneNumber("076"), new PhoneNumber("078")),
                duplicates);
    }

    @Test
    public void testThreeTimes() {
        Map<String, PhoneNumber> phoneBook = new HashMap<>(Map.of(
                "Petra", new PhoneNumber("079"),
                "Franz", new PhoneNumber("076"),
                "Lea", new PhoneNumber("077"),
                "Anna", new PhoneNumber("078"),
                "Fritz", new PhoneNumber("076"),
                "Markus", new PhoneNumber("076"),
                "Andrea", new PhoneNumber("079"),
                "Andi", new PhoneNumber("079"),
                "Notruf", new PhoneNumber("112")));
        Set<PhoneNumber> duplicates = ViewTasks.duplicateNumbers(phoneBook);
        assertEquals(Set.of(new PhoneNumber("079"), new PhoneNumber("076")), duplicates);
    }
}
