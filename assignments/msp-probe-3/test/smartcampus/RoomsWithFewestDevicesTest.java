package smartcampus;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class RoomsWithFewestDevicesTest {

    @Order(1)
    @Test
    void roomNames() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Bibliothek", List.of(
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Mensa", List.of(
                        new Sensor(2, "Lichtsensor Tür", SensorType.LIGHT)))));
        assertEquals(Set.of("Aula", "Bibliothek", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(3)));

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Bibliothek", List.of(
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT)))));
        assertEquals(Set.of("Aula", "Bibliothek"),
                Set.copyOf(campus.roomsWithFewestDevices(2)));
    }

    @Order(2)
    @Test
    void notMoreThanN() {
            Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Bibliothek", List.of(
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Mensa", List.of(
                        new Sensor(2, "Lichtsensor Tür", SensorType.LIGHT)))));
        assertEquals(Set.of("Aula", "Bibliothek", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(3)));
        assertEquals(2, campus.roomsWithFewestDevices(2).size());
        assertEquals(1, campus.roomsWithFewestDevices(1).size());

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(1, "Lichtsensor Fenster", SensorType.LIGHT))),
                new Room("Bibliothek", List.of(
                        new Sensor(2, "Lichtsensor Tür", SensorType.LIGHT)))));
        assertEquals(Set.of("Aula", "Bibliothek"),
                Set.copyOf(campus.roomsWithFewestDevices(3)));
        assertEquals(1, campus.roomsWithFewestDevices(1).size());
    }

    @Order(3)
    @Test
    void notMoreThanNOrRoomCount() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Bibliothek", List.of(
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Mensa", List.of(
                        new Shades(2, "Storen Ost"),
                        new Shades(3, "Storen Nord"),
                        new Ventilation(4, "Lüftung")))));
        assertEquals(Set.of("Aula", "Bibliothek", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(3)));
        assertEquals(Set.of("Aula", "Bibliothek", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(4)));
        assertEquals(Set.of("Aula", "Bibliothek", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(5)));
        assertEquals(Set.of("Aula", "Bibliothek", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(100)));

        assertEquals(2, campus.roomsWithFewestDevices(2).size());
        assertEquals(1, campus.roomsWithFewestDevices(1).size());


        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Sensor(1, "Lichtsensor Fenster", SensorType.LIGHT))),
                new Room("Mensa", List.of(
                        new Shades(2, "Storen Ost"),
                        new Shades(3, "Storen Nord"),
                        new Ventilation(4, "Lüftung")))));
        assertEquals(Set.of("Aula", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(2)));
        assertEquals(Set.of("Aula", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(3)));
        assertEquals(Set.of("Aula", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(4)));
        assertEquals(Set.of("Aula", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(100)));

        assertEquals(1, campus.roomsWithFewestDevices(1).size());
    }

    @Order(4)
    @Test
    void sorted() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Shades(1, "Storen Ost"))),
                new Room("Bibliothek", List.of(
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Mensa", List.of(
                        new Shades(2, "Storen Ost"),
                        new Shades(3, "Storen Nord"),
                        new Ventilation(4, "Lüftung")))));
        assertEquals(List.of("Bibliothek", "Aula", "Mensa"),
                campus.roomsWithFewestDevices(3));

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Bibliothek", List.of(
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT),
                        new Shades(2, "Storen Ost"),
                        new Shades(3, "Storen Nord"),
                        new Ventilation(4, "Lüftung"))),
                new Room("Sekretariat", List.of(
                        new Shades(4, "Storen Ost"),
                        new Shades(5, "Storen Nord"),
                        new Ventilation(4, "Lüftung"))),
                new Room("Mensa", List.of(
                        new Shades(6, "Storen Ost"),
                        new Ventilation(7, "Lüftung")))));
        assertEquals(List.of("Aula", "Mensa", "Sekretariat", "Bibliothek"),
                campus.roomsWithFewestDevices(4));
    }

    @Order(5)
    @Test
    void complete() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Shades(1, "Storen Ost"))),
                new Room("Bibliothek", List.of(
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Mensa", List.of(
                        new Shades(2, "Storen Ost"),
                        new Shades(3, "Storen Nord"),
                        new Ventilation(4, "Lüftung")))));
        assertEquals(List.of("Bibliothek"),
                campus.roomsWithFewestDevices(1));
        assertEquals(List.of("Bibliothek", "Aula"),
                campus.roomsWithFewestDevices(2));
        assertEquals(List.of("Bibliothek", "Aula", "Mensa"),
                campus.roomsWithFewestDevices(3));
        assertEquals(List.of("Bibliothek", "Aula", "Mensa"),
                campus.roomsWithFewestDevices(4));
        assertEquals(List.of("Bibliothek", "Aula", "Mensa"),
                campus.roomsWithFewestDevices(5));
        assertEquals(List.of("Bibliothek", "Aula", "Mensa"),
                campus.roomsWithFewestDevices(100));

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT))),
                new Room("Bibliothek", List.of(
                        new Sensor(1, "Lichtsensor Tür", SensorType.LIGHT),
                        new Shades(2, "Storen Ost"),
                        new Shades(3, "Storen Nord"),
                        new Ventilation(4, "Lüftung"))),
                new Room("Sekretariat", List.of(
                        new Shades(4, "Storen Ost"),
                        new Shades(5, "Storen Nord"),
                        new Ventilation(4, "Lüftung"))),
                new Room("Mensa", List.of(
                        new Shades(6, "Storen Ost"),
                        new Ventilation(7, "Lüftung")))));
        assertEquals(List.of("Aula"),
                campus.roomsWithFewestDevices(1));
        assertEquals(List.of("Aula", "Mensa"),
                campus.roomsWithFewestDevices(2));
        assertEquals(List.of("Aula", "Mensa", "Sekretariat"),
                campus.roomsWithFewestDevices(3));
        assertEquals(List.of("Aula", "Mensa", "Sekretariat", "Bibliothek"),
                campus.roomsWithFewestDevices(4));
        assertEquals(List.of("Aula", "Mensa", "Sekretariat", "Bibliothek"),
                campus.roomsWithFewestDevices(5));
        assertEquals(List.of("Aula", "Mensa", "Sekretariat", "Bibliothek"),
                campus.roomsWithFewestDevices(100));

        campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Shades(1, "Storen Ost"))),
                new Room("Bibliothek", List.of(
                        new Sensor(2, "Lichtsensor Tür", SensorType.LIGHT),
                        new Shades(3, "Storen Ost"),
                        new Shades(4, "Storen Nord"),
                        new Ventilation(5, "Lüftung"),
                        new Sensor(6, "Temperatursensor Decke", SensorType.TEMPERATURE),
                        new Sensor(7, "Temperatursensor Boden", SensorType.TEMPERATURE))),
                new Room("Sekretariat", List.of(
                        new Shades(8, "Storen Ost"),
                        new Shades(9, "Storen Nord"),
                        new Ventilation(10, "Lüftung"))),
                new Room("Mensa", List.of(
                        new Shades(11, "Storen Ost"))),
                new Room("Studiensaal", List.of(
                        new Shades(12, "Storen Ost"),
                        new Shades(13, "Storen Nord"),
                        new Ventilation(14, "Lüftung"),
                        new Sensor(15, "Temperatursensor Decke", SensorType.TEMPERATURE)))));
        assertEquals(List.of("Mensa"),
                campus.roomsWithFewestDevices(1));
        assertEquals(List.of("Mensa", "Aula"),
                campus.roomsWithFewestDevices(2));
        assertEquals(List.of("Mensa", "Aula", "Sekretariat"),
                campus.roomsWithFewestDevices(3));
        assertEquals(List.of("Mensa", "Aula", "Sekretariat", "Studiensaal"),
                campus.roomsWithFewestDevices(4));
        assertEquals(List.of("Mensa", "Aula", "Sekretariat", "Studiensaal", "Bibliothek"),
                campus.roomsWithFewestDevices(5));
        assertEquals(List.of("Mensa", "Aula", "Sekretariat", "Studiensaal", "Bibliothek"),
                campus.roomsWithFewestDevices(6));
        assertEquals(List.of("Mensa", "Aula", "Sekretariat", "Studiensaal", "Bibliothek"),
                campus.roomsWithFewestDevices(1000));
    }

    @Order(6)
    @Test
    void emptyRooms() {
        Campus campus = new Campus(List.of(
                new Room("Aula", List.of(
                        new Sensor(0, "Lichtsensor Tür", SensorType.LIGHT),
                        new Shades(1, "Storen Ost"))),
                new Room("Bibliothek", List.of()),
                new Room("Mensa", List.of(
                        new Shades(2, "Storen Ost"),
                        new Shades(3, "Storen Nord"),
                        new Ventilation(4, "Lüftung")))));
        assertEquals(List.of("Bibliothek"),
                campus.roomsWithFewestDevices(1));
        assertEquals(List.of("Bibliothek", "Aula"),
                campus.roomsWithFewestDevices(2));
        assertEquals(List.of("Bibliothek", "Aula", "Mensa"),
                campus.roomsWithFewestDevices(3));

        campus = new Campus(List.of(
                new Room("Aula", List.of()),
                new Room("Bibliothek", List.of()),
                new Room("Mensa", List.of(
                        new Shades(2, "Storen Ost"),
                        new Shades(3, "Storen Nord"),
                        new Ventilation(4, "Lüftung")))));
        assertEquals(1, campus.roomsWithFewestDevices(1).size());
        assertTrue(campus.roomsWithFewestDevices(1).contains("Aula") ||
                   campus.roomsWithFewestDevices(1).contains("Bibliothek"));

        assertEquals(Set.of("Aula", "Bibliothek"),
                Set.copyOf(campus.roomsWithFewestDevices(2)));

        assertEquals(Set.of("Bibliothek", "Aula", "Mensa"),
                Set.copyOf(campus.roomsWithFewestDevices(3)));
        assertEquals("Mensa", campus.roomsWithFewestDevices(3).get(2));
    }

    @Order(7)
    @Test
    void noRoomsOrZero() {
        Campus campus = new Campus(List.of());
        assertEquals(List.of(), campus.roomsWithFewestDevices(0));
        assertEquals(List.of(), campus.roomsWithFewestDevices(1));
        assertEquals(List.of(), campus.roomsWithFewestDevices(2));
        assertEquals(List.of(), campus.roomsWithFewestDevices(3));

        campus = new Campus(List.of(
                new Room("Aula", List.of())));
        assertEquals(List.of(), campus.roomsWithFewestDevices(0));
        assertNotEquals(List.of(), campus.roomsWithFewestDevices(1));
    }
}
