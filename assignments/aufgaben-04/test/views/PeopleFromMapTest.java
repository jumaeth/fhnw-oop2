package views;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeopleFromMapTest {

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
