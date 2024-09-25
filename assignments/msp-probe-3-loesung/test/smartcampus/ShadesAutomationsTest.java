package smartcampus;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(OrderAnnotation.class)
public class ShadesAutomationsTest {

    @Order(1)
    @Test
    void canBeInstalled() {
        var campus = new Campus(List.of());
        var automation = ShadesAutomations.newOpenCloseAutomation();
        campus.installAutomation(automation);
    }

    @Order(2)
    @Test
    void shadesOpenAt0600() {
        var shades = new Shades(0, "Fensterstoren");
        shades.setPercentClosed(100);
        var campus = new Campus(List.of(
                new Room("Aula", List.of(shades))));
        campus.installAutomation(ShadesAutomations.newOpenCloseAutomation());

        campus.performAutomations(6, 0);
        assertEquals(0, shades.getPercentClosed());

        var shadesNorth = new Shades(1, "Fensterstoren Nord");
        shadesNorth.setPercentClosed(100);
        var shadesEast = new Shades(2, "Fensterstoren Ost");
        shadesEast.setPercentClosed(90);
        campus = new Campus(List.of(
                new Room("Aula", List.of(shadesNorth, shadesEast))));
        campus.installAutomation(ShadesAutomations.newOpenCloseAutomation());

        campus.performAutomations(6, 0);
        assertEquals(0, shadesNorth.getPercentClosed());
    }

    @Order(3)
    @Test
    void shadesCloseAt2030() {
        var shades = new Shades(0, "Fensterstoren");
        shades.setPercentClosed(0);
        var campus = new Campus(List.of(
                new Room("Aula", List.of(shades))));
        campus.installAutomation(ShadesAutomations.newOpenCloseAutomation());

        campus.performAutomations(20, 30);
        assertEquals(100, shades.getPercentClosed());

        var shadesNorth = new Shades(1, "Fensterstoren Nord");
        shadesNorth.setPercentClosed(50);
        var shadesEast = new Shades(2, "Fensterstoren Ost");
        shadesEast.setPercentClosed(10);
        var shadesSouth = new Shades(3, "Fensterstoren Süd");
        shadesSouth.setPercentClosed(0);
        campus = new Campus(List.of(
                new Room("Aula", List.of(shadesNorth, shadesEast)),
                new Room("Bibliothek", List.of(shadesSouth))));
        campus.installAutomation(ShadesAutomations.newOpenCloseAutomation());

        campus.performAutomations(20, 30);
        assertEquals(100, shadesNorth.getPercentClosed());
        assertEquals(100, shadesEast.getPercentClosed());
        assertEquals(100, shadesSouth.getPercentClosed());
    }

    @Order(4)
    @Test
    void nothingHappensAtOtherTimes() {
        var shades = new Shades(0, "Fensterstoren");
        shades.setPercentClosed(50);
        var campus = new Campus(List.of(
                new Room("Aula", List.of(shades))));
        campus.installAutomation(ShadesAutomations.newOpenCloseAutomation());

        for (var hour = 0; hour < 24; hour++) {
            for (var min = 0; min < 60; min++) {
                if ((hour != 6 || min != 0) && (hour != 20 || min != 30)) {
                    campus.performAutomations(hour, min);
                    assertEquals(50, shades.getPercentClosed(),
                            "automations should not change shades at " + hour + ":" + min);
                }
            }
        }

        // also test that something happens at 6:00 or 20:30
        campus.performAutomations(6, 0);
        campus.performAutomations(20, 30);
        assertNotEquals(50, shades.getPercentClosed());
    }
}
