package lambda;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncreaseFullTimeSalariesTest {

    @Test
    void increaseFullTimeSalariesAnyChange() {
        var alice = new Employee("Alice", 42.5);
        var bob = new Employee("Bob", 44.5);
        var charlie = new Employee("Charlie", 43.0);
        var employees = new HashMap<>(Map.of(
                alice, 5000.0,
                bob, 4000.0,
                charlie, 3000.0));

        Lambdas.increaseFullTimeSalaries(employees, 500);

        assertTrue(employees.get(alice) != 5000.0);
        assertTrue(employees.get(bob) != 4000.0);
        assertTrue(employees.get(charlie) != 3000.0);
    }

    @Test
    void increaseFullTimeSalariesPreciseIncrease() {
        var david = new Employee("David", 42.5);
        var eve = new Employee("Eve", 50.0);
        var fiona = new Employee("Fiona", 43.0);
        var employees = new HashMap<>(Map.of(
                david, 8000.0,
                eve, 6000.0,
                fiona, 10000.0));

        Lambdas.increaseFullTimeSalaries(employees, 500);

        assertEquals(8500.0, employees.get(david), 0.1);
        assertEquals(6500, employees.get(eve), 0.1);
        assertEquals(10500, employees.get(fiona), 0.1);

        Lambdas.increaseFullTimeSalaries(employees, 1500);

        assertEquals(10000, employees.get(david), 0.1);
        assertEquals(8000, employees.get(eve), 0.1);
        assertEquals(12000, employees.get(fiona), 0.1);
    }

    @Test
    void increaseFullTimeSalariesOnlyFullTime() {
        var alice = new Employee("Alice", 42.5);
        var bob = new Employee("Bob", 44.5);
        var charlie = new Employee("Charlie", 20.0);
        var david = new Employee("David", 42.0);
        var eve = new Employee("Eve", 10.0);
        var employees = new HashMap<>(Map.of(
                alice, 5000.0,
                bob, 4000.0,
                charlie, 3000.0,
                david, 4500.0,
                eve, 5000.0));

        Lambdas.increaseFullTimeSalaries(employees, 100);

        assertEquals(5100.0, employees.get(alice), 0.1);
        assertEquals(4100.0, employees.get(bob), 0.1);
        assertEquals(3000.0, employees.get(charlie), 0.1);
        assertEquals(4500.0, employees.get(david), 0.1);
        assertEquals(5000.0, employees.get(eve), 0.1);

        alice.setWeekHours(40.0);
        bob.setWeekHours(42.4);

        Lambdas.increaseFullTimeSalaries(employees, 400);

        assertEquals(5100.0, employees.get(alice), 0.1);
        assertEquals(4100.0, employees.get(bob), 0.1);
        assertEquals(3000.0, employees.get(charlie), 0.1);
        assertEquals(4500.0, employees.get(david), 0.1);
        assertEquals(5000.0, employees.get(eve), 0.1);
    }
}
