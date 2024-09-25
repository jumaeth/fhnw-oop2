package smartcampus;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class TotalPowerConsumptionForRoom {

    @Order(1)
    @Test
    void singleRoomWithLamps() {
        var campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Lamp(0, "Deckenlampe gross", 20, 1, true),
                        new Lamp(1, "Deckenlampe klein", 12, 1, true)))));
        assertEquals(34.0, campus.totalPowerConsumptionForRoom("Aula"), 0.001);

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Lamp(0, "Deckenlampe gross", 20, 1, true),
                        new Lamp(1, "Deckenlampe klein", 12, 1, true),
                        new Lamp(2, "Deckenlampe klein", 12, 1, false)))));
        assertEquals(35.0, campus.totalPowerConsumptionForRoom("Aula"), 0.001);

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Lamp(0, "Deckenlampe gross", 20, 1, true),
                        new Lamp(1, "Deckenlampe klein", 12, 1, true),
                        new Lamp(2, "Deckenlampe klein", 12, 1, true)))));
        assertEquals(47.0, campus.totalPowerConsumptionForRoom("Aula"), 0.001);
    }

    @Order(2)
    @Test
    void singleRoomMixedActors() {
        var campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Lamp(0, "Deckenlampe gross", 20, 1, true),
                        new Shades(1, "Fensterstoren")))));
        assertEquals(21.5, campus.totalPowerConsumptionForRoom("Aula"), 0.001);

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Lamp(0, "Deckenlampe gross", 20, 5, true),
                        new Shades(1, "Fensterstoren"),
                        new Lamp(2, "Deckenlampe klein", 12, 5, true)))));
        assertEquals(42.5, campus.totalPowerConsumptionForRoom("Aula"), 0.001);
    }

    @Order(3)
    @Test
    void multipleRooms() {
        var campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Lamp(0, "Deckenlampe gross", 19, 1, true),
                        new Lamp(1, "Deckenlampe klein", 9, 1, true))),
                new Room("Bibliothek", List.of(
                        new Lamp(2, "Deckenlampe gross", 20, 1, true))),
                new Room("Sekretariat", List.of(
                        new Lamp(3, "Deckenlampe gross", 40, 1, true),
                        new Lamp(4, "Deckenlampe klein", 30, 1, true)))));
        assertEquals(30, campus.totalPowerConsumptionForRoom("Aula"), 0.001);
        assertEquals(21, campus.totalPowerConsumptionForRoom("Bibliothek"), 0.001);
        assertEquals(72, campus.totalPowerConsumptionForRoom("Sekretariat"), 0.001);

        var lamp0 = (Lamp) campus.getRooms().get(0).getDevices().get(0);
        lamp0.setOn(false);
        assertEquals(11, campus.totalPowerConsumptionForRoom("Aula"), 0.001);
    }

    @Order(4)
    @Test
    void multipleRoomsWithSensors() {
        var campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Lamp(0, "Deckenlampe gross", 19, 1, true),
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT),
                        new Lamp(2, "Deckenlampe klein", 9, 1, true))),
                new Room("Bibliothek", List.of(
                        new Sensor(3, "Temperatursensor Decke", SensorType.TEMPERATURE),
                        new Sensor(4, "Lichtsensor Tür", SensorType.LIGHT),
                        new Lamp(5, "Deckenlampe gross", 20, 1, true))),
                new Room("Sekretariat", List.of(
                        new Lamp(6, "Deckenlampe gross", 40, 1, true),
                        new Lamp(7, "Deckenlampe klein", 30, 1, true)))));
        assertEquals(30, campus.totalPowerConsumptionForRoom("Aula"), 0.001);
        assertEquals(21, campus.totalPowerConsumptionForRoom("Bibliothek"), 0.001);
        assertEquals(72, campus.totalPowerConsumptionForRoom("Sekretariat"), 0.001);

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Bibliothek", List.of(
                        new Lamp(2, "Deckenlampe gross", 20, 1, true))),
                new Room("Sekretariat", List.of(
                        new Lamp(3, "Deckenlampe gross", 40, 1, true),
                        new Lamp(4, "Deckenlampe klein", 30, 1, true)))));
        assertEquals(0, campus.totalPowerConsumptionForRoom("Aula"), 0.001);
        assertEquals(21, campus.totalPowerConsumptionForRoom("Bibliothek"), 0.001);
        assertEquals(72, campus.totalPowerConsumptionForRoom("Sekretariat"), 0.001);
    }

    @Order(5)
    @Test
    void invalidRoomName() {
        var campus1 = new Campus(List.of(
                new Room("Aula", List.of(
                        new Lamp(0, "Deckenlampe gross", 19, 1, true),
                        new Lamp(1, "Deckenlampe klein", 9, 1, true))),
                new Room("Bibliothek", List.of(
                        new Lamp(2, "Deckenlampe gross", 20, 1, true))),
                new Room("Sekretariat", List.of(
                        new Lamp(3, "Deckenlampe gross", 40, 1, true),
                        new Ventilation(4, "Klimaanlage")))));
        assertThrows(IllegalArgumentException.class, () -> {
            campus1.totalPowerConsumptionForRoom("Mensa");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            campus1.totalPowerConsumptionForRoom("Studiensaal A");
        });

        var campus2 = new Campus(List.of(
                new Room("Aula", List.of(
                        new Lamp(0, "Deckenlampe gross", 19, 1, true),
                        new Lamp(1, "Deckenlampe klein", 9, 1, true)))));
        assertThrows(IllegalArgumentException.class, () -> {
            campus2.totalPowerConsumptionForRoom("Bibliothek");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            campus2.totalPowerConsumptionForRoom("Sekretariat");
        });

        // also test that the method sometimes returns normally
        assertDoesNotThrow(() -> campus1.totalPowerConsumptionForRoom("Bibliothek"));
        assertDoesNotThrow(() -> campus1.totalPowerConsumptionForRoom("Sekretariat"));
        assertDoesNotThrow(() -> campus2.totalPowerConsumptionForRoom("Aula"));
    }

    @Order(6)
    @Test
    void noOrEmptyRooms() {
        var campus1 = new Campus(List.of());
        assertThrows(IllegalArgumentException.class, () -> {
            campus1.totalPowerConsumptionForRoom("Aula");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            campus1.totalPowerConsumptionForRoom("Bibliothek");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            campus1.totalPowerConsumptionForRoom("Sekretariat");
        });

        var campus2 = new Campus(List.of(new Room("Aula", List.of())));
        assertEquals(0, campus2.totalPowerConsumptionForRoom("Aula"), 0.001);
    }
}
