package smartcampus;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestClassOrder(OrderAnnotation.class)
public class AutomationTest {

//    @Test
//    void installAutomation() {
//        Campus campus = new Campus(List.of());
//        campus.installAutomation((Campus c, TimeOfDay time) -> {
//            System.out.println("Hello, World!");
//        });
//    }
//
//    @Test
//    void performAutomationsSingle() {
//        var lamp1 = new Lamp(1, "Deckenlampe gross", 20, 1);
//        var lamp2 = new Lamp(2, "Deckenlampe klein", 12, 1);
//        Campus campus = new Campus(List.of(
//                new Room("Aula", List.of(lamp1, lamp2))));
//
//        // bei diesem Test spielt die Uhrzeit noch keine Rolle
//        campus.installAutomation((Campus c, TimeOfDay time) -> {
//            var firstLamp = (Lamp) c.getRooms().get(0).getDevices().get(0);
//            firstLamp.setOn(true);
//        });
//
//        // irgendeine Uhrzeit
//        campus.performAutomations(12, 30);
//        assertTrue(lamp1.isOn());
//        assertFalse(lamp2.isOn());
//
//        lamp1.setOn(false);
//
//        // irgendeine Uhrzeit
//        campus.performAutomations(12, 30);
//        assertTrue(lamp1.isOn());
//        assertFalse(lamp2.isOn());
//    }
//
//    @Test
//    void performAutomationsMultiple() {
//        var lamp1 = new Lamp(1, "Deckenlampe gross", 20, 1);
//        var lamp2 = new Lamp(2, "Deckenlampe klein", 12, 1);
//        Campus campus = new Campus(List.of(
//                new Room("Aula", List.of(lamp1, lamp2))));
//
//        // bei diesem Test spielt die Uhrzeit noch keine Rolle
//        campus.installAutomation((Campus c, TimeOfDay time) -> {
//            var firstLamp = (Lamp) c.getRooms().get(0).getDevices().get(0);
//            firstLamp.setOn(true);
//        });
//        campus.installAutomation((Campus c, TimeOfDay time) -> {
//            var secondLamp = (Lamp) c.getRooms().get(0).getDevices().get(1);
//            secondLamp.setOn(true);
//        });
//
//        // irgendeine Uhrzeit
//        campus.performAutomations(12, 30);
//        assertTrue(lamp1.isOn());
//        assertTrue(lamp2.isOn());
//
//        lamp1.setOn(false);
//        lamp2.setOn(false);
//
//        // irgendeine Uhrzeit
//        campus.performAutomations(12, 30);
//        assertTrue(lamp1.isOn());
//        assertTrue(lamp2.isOn());
//    }
//
//    @Test
//    void performAutomationsTime() {
//        var lamp1 = new Lamp(1, "Deckenlampe gross", 20, 1);
//        var lamp2 = new Lamp(2, "Deckenlampe klein", 12, 1);
//        Campus campus = new Campus(List.of(
//                new Room("Aula", List.of(lamp1, lamp2))));
//
//        // diese Automation wird nur um 8:30 Uhr ausgeführt
//        campus.installAutomation((Campus c, TimeOfDay time) -> {
//            if (time.hour() == 8 && time.minute() == 30) {
//                var firstLamp = (Lamp) campus.getRooms().get(0).getDevices().get(0);
//                firstLamp.setOn(true);
//            }
//        });
//
//        campus.performAutomations(7, 30);
//        assertFalse(lamp1.isOn());
//        assertFalse(lamp2.isOn());
//
//        campus.performAutomations(8, 29);
//        assertFalse(lamp1.isOn());
//        assertFalse(lamp2.isOn());
//
//        campus.performAutomations(8, 30);
//        assertTrue(lamp1.isOn());
//        assertFalse(lamp2.isOn());
//
//        lamp1.setOn(false);
//
//        campus.performAutomations(8, 31);
//        assertFalse(lamp1.isOn());
//        assertFalse(lamp2.isOn());
//    }
}
