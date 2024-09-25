package smartcampus;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(OrderAnnotation.class)
public class CountSensorsByTypeTest {

    @Order(1)
    @Test
    void singleRoomOnlyOneTypePresent() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(1, "Lichtsensor Fenster", SensorType.LIGHT)))));
        assertEquals(2, campus.countSensorsByType(SensorType.LIGHT).intValue());

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Temperatursensor Decke", SensorType.TEMPERATURE)))));
        assertEquals(1, campus.countSensorsByType(SensorType.TEMPERATURE).intValue());

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "CO₂-Sensor", SensorType.CO2)))));
        assertEquals(1, campus.countSensorsByType(SensorType.CO2).intValue());
    }

    @Order(2)
    @Test
    void multipleRoomsOnlyOneTypePresent() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Bibliothek", List.of(
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(2, "Lichtsensor Fenster", SensorType.LIGHT))),
                new Room("Mensa", List.of(
                        new Sensor(3, "Lichtsensor Tür", SensorType.LIGHT)))));
        assertEquals(4, campus.countSensorsByType(SensorType.LIGHT).intValue());

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Temperatursensor Decke", SensorType.TEMPERATURE))),
                new Room("Bibliothek", List.of(
                        new Sensor(1, "Temperatursensor Decke", SensorType.TEMPERATURE),
                        new Sensor(2, "Temperatursensor Boden", SensorType.TEMPERATURE)))));
        assertEquals(3, campus.countSensorsByType(SensorType.TEMPERATURE).intValue());
    }

    @Order(3)
    @Test
    void singleRoomEachTypePresent() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(1, "Temperatursensor Decke", SensorType.TEMPERATURE),
                        new Sensor(2, "CO₂-Sensor", SensorType.CO2)))));
        assertEquals(1, campus.countSensorsByType(SensorType.LIGHT).intValue());
        assertEquals(1, campus.countSensorsByType(SensorType.TEMPERATURE).intValue());
        assertEquals(1, campus.countSensorsByType(SensorType.CO2).intValue());

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(1, "Lichtsensor Fenster", SensorType.LIGHT),
                        new Sensor(2, "Temperatursensor Decke", SensorType.TEMPERATURE),
                        new Sensor(3, "Temperatursensor Boden", SensorType.TEMPERATURE),
                        new Sensor(4, "CO₂-Sensor 1", SensorType.CO2),
                        new Sensor(5, "CO₂-Sensor 2", SensorType.CO2)))));
        assertEquals(2, campus.countSensorsByType(SensorType.LIGHT).intValue());
        assertEquals(2, campus.countSensorsByType(SensorType.TEMPERATURE).intValue());
        assertEquals(2, campus.countSensorsByType(SensorType.CO2).intValue());
    }

    @Order(4)
    @Test
    void multipleRooms() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(1, "Temperatursensor Decke", SensorType.TEMPERATURE),
                        new Sensor(2, "CO₂-Sensor", SensorType.CO2))),
                new Room("Bibliothek", List.of(
                        new Sensor(3, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(4, "CO₂-Sensor", SensorType.CO2))),
                new Room("Mensa", List.of(
                        new Sensor(5, "CO₂-Sensor", SensorType.CO2)))));
        assertEquals(2, campus.countSensorsByType(SensorType.LIGHT).intValue());
        assertEquals(1, campus.countSensorsByType(SensorType.TEMPERATURE).intValue());
        assertEquals(3, campus.countSensorsByType(SensorType.CO2).intValue());

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(2, "CO₂-Sensor", SensorType.CO2))),
                new Room("Bibliothek", List.of(
                        new Sensor(3, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(4, "CO₂-Sensor", SensorType.CO2))),
                new Room("Mensa", List.of(
                        new Sensor(5, "CO₂-Sensor", SensorType.CO2),
                        new Sensor(6, "CO₂-Sensor", SensorType.CO2),
                        new Sensor(7, "CO₂-Sensor", SensorType.CO2)))));
        assertEquals(2, campus.countSensorsByType(SensorType.LIGHT).intValue());
        assertEquals(0, campus.countSensorsByType(SensorType.TEMPERATURE).intValue());
        assertEquals(5, campus.countSensorsByType(SensorType.CO2).intValue());
    }

    @Order(5)
    @Test
    void sensorsAndActors() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(1, "Temperatursensor Decke", SensorType.TEMPERATURE),
                        new Sensor(2, "CO₂-Sensor", SensorType.CO2),
                        new Lamp(3, "Deckenlampe gross", 20, 1, true),
                        new Lamp(4, "Deckenlampe klein", 12, 1, true),
                        new Shades(5, "Fensterstoren"))),
                new Room("Bibliothek", List.of(
                        new Sensor(6, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(7, "CO₂-Sensor", SensorType.CO2),
                        new Lamp(8, "Deckenlampe gross", 20, 1, true),
                        new Lamp(9, "Deckenlampe klein", 12, 1, true),
                        new Shades(10, "Fensterstoren"))),
                new Room("Mensa", List.of(
                        new Sensor(11, "Temperatursensor Boden", SensorType.TEMPERATURE),
                        new Sensor(11, "Temperatursensor Decke 1", SensorType.TEMPERATURE),
                        new Sensor(11, "Temperatursensor Decke 2", SensorType.TEMPERATURE),
                        new Lamp(12, "Deckenlampe gross", 20, 1, true),
                        new Lamp(13, "Deckenlampe klein", 12, 1, true),
                        new Shades(14, "Fensterstoren")))));
        assertEquals(2, campus.countSensorsByType(SensorType.LIGHT).intValue());
        assertEquals(4, campus.countSensorsByType(SensorType.TEMPERATURE).intValue());
        assertEquals(2, campus.countSensorsByType(SensorType.CO2).intValue());
    }

    @Order(6)
    @Test
    void emptyOrNoRooms() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of()),
                new Room("Bibliothek", List.of()),
                new Room("Mensa", List.of())));
        assertEquals(0, campus.countSensorsByType(SensorType.LIGHT).intValue());
        assertEquals(0, campus.countSensorsByType(SensorType.TEMPERATURE).intValue());
        assertEquals(0, campus.countSensorsByType(SensorType.CO2).intValue());

        campus = new Campus(List.of());
        assertEquals(0, campus.countSensorsByType(SensorType.LIGHT).intValue());
        assertEquals(0, campus.countSensorsByType(SensorType.TEMPERATURE).intValue());
        assertEquals(0, campus.countSensorsByType(SensorType.CO2).intValue());

        // Teste auch, dass die Methode mal was anderes zurückgibt
        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT)))));
        assertNotEquals(0, campus.countSensorsByType(SensorType.LIGHT).intValue());
    }
}
