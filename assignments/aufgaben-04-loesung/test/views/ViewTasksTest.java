package views;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewTasksTest {
    @Nested
    class RemoveSignatureTest {

        @Test
        public void testSig() {
            List<String> email = new ArrayList<>(List.of(
                    "Hi,",
                    "How are you?",
                    "Best,",
                    "Tim",
                    "-- ",
                    "Tim Johnson",
                    "Consultant"));
            List<String> expected = List.of(
                    "Hi,",
                    "How are you?",
                    "Best,",
                    "Tim");
            ViewTasks.removeSignature(email);
            assertEquals(expected, email);
        }

        @Test
        public void testEmptyLines() {
            List<String> email = new ArrayList<>(List.of(
                    "Hi,",
                    "How are you?",
                    "",
                    "Best,",
                    "Tim",
                    "-- ",
                    "Tim Johnson",
                    "Consultant",
                    "",
                    "The Company"));
            List<String> expected = List.of(
                    "Hi,",
                    "How are you?",
                    "",
                    "Best,",
                    "Tim");
            ViewTasks.removeSignature(email);
            assertEquals(expected, email);
        }

        @Test
        public void testIncorrectSigSeparatorFirst() {
            List<String> email = new ArrayList<>(List.of(
                    "Hi,",
                    "How are you?",
                    "Best,",
                    "Tim",
                    "--",
                    "Tim Johnson",
                    "-- ",
                    "Consultant"));
            List<String> expected = List.of(
                    "Hi,",
                    "How are you?",
                    "Best,",
                    "Tim",
                    "--",
                    "Tim Johnson");
            ViewTasks.removeSignature(email);
            assertEquals(expected, email);
        }

        @Test
        public void testNoSig() {
            testSig(); // test basic functionality, so empty implementation doesn't pass

            List<String> email = new ArrayList<>(List.of(
                    "Hi,",
                    "How are you?",
                    "Best,",
                    "Tim"));
            List<String> expected = List.of(
                    "Hi,",
                    "How are you?",
                    "Best,",
                    "Tim");
            ViewTasks.removeSignature(email);
            assertEquals(expected, email);
        }
    }

    @Nested
    class CountNamesTest {

        @Test
        public void testSome() {
            Map<String, PhoneNumber> phoneBook = new HashMap<>(Map.of(
                    "Petra", new PhoneNumber("079"),
                    "Franz", new PhoneNumber("076"),
                    "Lea", new PhoneNumber("077"),
                    "Notruf", new PhoneNumber("112")));
            String[] names = {"Petra", "Lea", "Pascal", "Paul"};
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
            String[] names = {"Petra", "Lea", "Notruf"};
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
            String[] names = {"Pascal", "Paul"};
            int count = ViewTasks.countNames(phoneBook, names);
            assertEquals(0, count);
        }

        @Test
        public void testDuplicates() {
            Map<String, PhoneNumber> phoneBook = new HashMap<>(Map.of(
                    "Petra", new PhoneNumber("079"),
                    "Franz", new PhoneNumber("076"),
                    "Lea", new PhoneNumber("077"),
                    "Notruf", new PhoneNumber("112")));
            String[] names = {"Lea", "Franz", "Lea"};
            int count = ViewTasks.countNames(phoneBook, names);
            assertEquals(2, count);

            names = new String[]{"Notruf", "Notruf", "Notruf"};
            count = ViewTasks.countNames(phoneBook, names);
            assertEquals(1, count);
        }
    }

    @Nested
    class PeopleFromMapTest {

        @Test
        public void testSimple() {
            Map<String, Set<String>> nameMap = new HashMap<>(Map.of(
                    "Müller", Set.of("Peter", "Pauline"),
                    "Schmid", Set.of("Aline"),
                    "Schaad", Set.of("Meret", "Fritz")));
            Set<Person> expected = Set.of(
                    new Person("Peter", "Müller"),
                    new Person("Pauline", "Müller"),
                    new Person("Aline", "Schmid"),
                    new Person("Meret", "Schaad"),
                    new Person("Fritz", "Schaad"));
            Set<Person> people = ViewTasks.peopleFromNameMap(nameMap);
            assertEquals(expected, people);
        }

        @Test
        public void testSingleLastName() {
            Map<String, Set<String>> nameMap = new HashMap<>(Map.of(
                    "Müller", Set.of("Peter", "Pauline")));
            Set<Person> expected = Set.of(
                    new Person("Peter", "Müller"),
                    new Person("Pauline", "Müller"));
            Set<Person> people = ViewTasks.peopleFromNameMap(nameMap);
            assertEquals(expected, people);
        }
    }

    @Nested
    class DuplicateNumbersTest {

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
}
