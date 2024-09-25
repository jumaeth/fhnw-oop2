package lambda;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

public class AccessedFilesPerEmployeeTest {

    @Test
    void accessedFilesPerEmployeeEmptyLog() {
        var map = Lambdas.accessedFilesPerEmployee(emptyList());
        assertNotNull(map);
        assertTrue(map.isEmpty());

        var accessLog = List.of(new LogEntry(new Employee("Alice", 42.5), "foo.txt"));
        map = Lambdas.accessedFilesPerEmployee(accessLog);
        assertNotNull(map);
        assertFalse(map.isEmpty());
    }

    @Test
    void accessedFilesPerEmployeeSingleFilePerEmployee() {
        var alice = new Employee("Alice", 42.5);
        var bob = new Employee("Bob", 44.5);
        var charlie = new Employee("Charlie", 43.0);
        var accessLog = List.of(
                new LogEntry(alice, "foo.txt"),
                new LogEntry(bob, "bar.pdf"),
                new LogEntry(charlie, "baz.docx"));

        var map = Lambdas.accessedFilesPerEmployee(accessLog);

        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(Set.of("foo.txt"), map.get(alice));
        assertEquals(Set.of("bar.pdf"), map.get(bob));
        assertEquals(Set.of("baz.docx"), map.get(charlie));

        accessLog = List.of(
                new LogEntry(alice, "foo.txt"),
                new LogEntry(bob, "foo.txt"));

        map = Lambdas.accessedFilesPerEmployee(accessLog);

        assertNotNull(map);
        assertEquals(2, map.size());
        assertEquals(Set.of("foo.txt"), map.get(alice));
        assertEquals(Set.of("foo.txt"), map.get(bob));
    }

    @Test
    void accessedFilesPerEmployeeMultipleFilesPerEmployee() {
        var alice = new Employee("Alice", 42.5);
        var bob = new Employee("Bob", 44.5);
        var charlie = new Employee("Charlie", 43.0);
        var accessLog = List.of(
                new LogEntry(alice, "foo.txt"),
                new LogEntry(bob, "bar.pdf"),
                new LogEntry(alice, "foo.txt"),
                new LogEntry(charlie, "baz.docx"),
                new LogEntry(alice, "bar.pdf"));

        var map = Lambdas.accessedFilesPerEmployee(accessLog);

        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(Set.of("foo.txt", "bar.pdf"), map.get(alice));
        assertEquals(Set.of("bar.pdf"), map.get(bob));
        assertEquals(Set.of("baz.docx"), map.get(charlie));

        accessLog = List.of(
                new LogEntry(alice, "foo.txt"),
                new LogEntry(alice, "foo.txt"),
                new LogEntry(charlie, "baz.docx"),
                new LogEntry(alice, "bar.pdf"),
                new LogEntry(alice, "baz.docx"));

        map = Lambdas.accessedFilesPerEmployee(accessLog);

        assertNotNull(map);
        assertEquals(2, map.size());
        assertEquals(Set.of("foo.txt", "bar.pdf", "baz.docx"), map.get(alice));
        assertEquals(Set.of("baz.docx"), map.get(charlie));
    }
}
