package smartcampus;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class ReadOpeningHoursTest {

    @Order(1)
    @Test
    void singleRoom() throws IOException {
        var mensa = new Room("Mensa");
        var campus = new Campus(List.of(mensa));

        var in = new ByteArrayInputStream("""
                Mensa 11:00
                """.getBytes(UTF_8));
        campus.readOpeningHours(in);
        assertEquals("11:00", mensa.getOpeningTime().toString());

        in = new ByteArrayInputStream("""
                Mensa 11:30
                """.getBytes(UTF_8));
        campus.readOpeningHours(in);
        assertEquals("11:30", mensa.getOpeningTime().toString());
    }

    @Order(2)
    @Test
    void multipleRooms() throws IOException {
        var mensa = new Room("Mensa");
        var bibliothek = new Room("Bibliothek");
        var sekretariat = new Room("Sekretariat");
        var campus = new Campus(List.of(mensa, bibliothek, sekretariat));

        var in = new ByteArrayInputStream("""
                Bibliothek 08:45
                Sekretariat 09:15
                Mensa 11:30
                """.getBytes(UTF_8));
        campus.readOpeningHours(in);
        assertEquals("08:45", bibliothek.getOpeningTime().toString());
        assertEquals("09:15", sekretariat.getOpeningTime().toString());
        assertEquals("11:30", mensa.getOpeningTime().toString());

        in = new ByteArrayInputStream("""
                Bibliothek 08:30
                Sekretariat 09:30
                """.getBytes(UTF_8));

        campus.readOpeningHours(in);
        assertEquals("08:30", bibliothek.getOpeningTime().toString());
        assertEquals("09:30", sekretariat.getOpeningTime().toString());
        assertEquals("11:30", mensa.getOpeningTime().toString());
    }

    @Order(3)
    @Test
    void multipleSpaces() throws IOException {
        var mensa = new Room("Mensa");
        var bibliothek = new Room("Bibliothek");
        var sekretariat = new Room("Sekretariat");
        var campus = new Campus(List.of(mensa, bibliothek, sekretariat));

        var in = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat 09:15
                Mensa       11:30
                """.getBytes(UTF_8));
        campus.readOpeningHours(in);
        assertEquals("08:45", bibliothek.getOpeningTime().toString());
        assertEquals("09:15", sekretariat.getOpeningTime().toString());
        assertEquals("11:30", mensa.getOpeningTime().toString());

        in = new ByteArrayInputStream("""
                Bibliothek    08:30
                Sekretariat   09:30
                """.getBytes(UTF_8));

        campus.readOpeningHours(in);
        assertEquals("08:30", bibliothek.getOpeningTime().toString());
        assertEquals("09:30", sekretariat.getOpeningTime().toString());
        assertEquals("11:30", mensa.getOpeningTime().toString());
    }

    @Order(4)
    @Test
    void roomNamesWithSpaces() throws IOException {
        var mensa = new Room("Mensa");
        var studiensaalA = new Room("Studiensaal A");
        var studiensaalB = new Room("Studiensaal B");
        var allGenderWc = new Room("All Gender WC");
        var campus = new Campus(List.of(mensa, studiensaalA, studiensaalB, allGenderWc));

        var in = new ByteArrayInputStream("""
                Mensa         08:45
                Studiensaal A 08:00
                Studiensaal B 08:00
                All Gender WC 06:00
                """.getBytes(UTF_8));
        campus.readOpeningHours(in);
        assertEquals("08:45", mensa.getOpeningTime().toString());
        assertEquals("08:00", studiensaalA.getOpeningTime().toString());
        assertEquals("08:00", studiensaalB.getOpeningTime().toString());
        assertEquals("06:00", allGenderWc.getOpeningTime().toString());

        in = new ByteArrayInputStream("""
                Studiensaal A 08:30
                All Gender WC 06:15
                """.getBytes(UTF_8));

        campus.readOpeningHours(in);
        assertEquals("08:45", mensa.getOpeningTime().toString());
        assertEquals("08:30", studiensaalA.getOpeningTime().toString());
        assertEquals("08:00", studiensaalB.getOpeningTime().toString());
        assertEquals("06:15", allGenderWc.getOpeningTime().toString());
    }

    @Order(5)
    @Test
    void invalidOrMissingOpeningHours() {
        var mensa = new Room("Mensa");
        var bibliothek = new Room("Bibliothek");
        var sekretariat = new Room("Sekretariat");
        var campus = new Campus(List.of(mensa, bibliothek, sekretariat));

        var in1 = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat 09:15
                Mensa
                """.getBytes(UTF_8));
        assertThrows(ParseException.class, () -> campus.readOpeningHours(in1));

        var in2 = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat 09:15
                Mensa       24:00
                """.getBytes(UTF_8));
        assertThrows(ParseException.class, () -> campus.readOpeningHours(in2));

        var in3 = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat 09:15
                Mensa       23:60
                """.getBytes(UTF_8));
        assertThrows(ParseException.class, () -> campus.readOpeningHours(in3));

        var in4 = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat 9:0:0
                Mensa       11:30
                """.getBytes(UTF_8));
        assertThrows(ParseException.class, () -> campus.readOpeningHours(in4));

        var in5 = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat XX:YY
                Mensa       11:30
                """.getBytes(UTF_8));
        assertThrows(ParseException.class, () -> campus.readOpeningHours(in5));

        // also test that no exception is thrown if the input is valid
        var in6 = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat 09:15
                Mensa       23:59
                """.getBytes(UTF_8));
        assertDoesNotThrow(() -> campus.readOpeningHours(in6));
    }

    @Order(6)
    @Test
    void invalidRoomNames() {
        var mensa = new Room("Mensa");
        var bibliothek = new Room("Bibliothek");
        var sekretariat = new Room("Sekretariat");
        var campus = new Campus(List.of(mensa, bibliothek, sekretariat));

        var in1 = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat 09:15
                Studiensaal 11:30
                """.getBytes(UTF_8));
        assertThrows(ParseException.class, () -> campus.readOpeningHours(in1));

        var in2 = new ByteArrayInputStream("""
                WC  11:30
                """.getBytes(UTF_8));
        assertThrows(ParseException.class, () -> campus.readOpeningHours(in2));

        // also test that no exception is thrown if all names are valid
        var in3 = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat 09:15
                """.getBytes(UTF_8));
        assertDoesNotThrow(() -> campus.readOpeningHours(in3));
    }

    @Order(7)
    @Test
    void emptyInput() throws IOException {
        var mensa = new Room("Mensa");
        var campus = new Campus(List.of(mensa));

        campus.readOpeningHours(new EmptyStream());
        assertNull(mensa.getOpeningTime());

        var openingTime = new TimeOfDay(11, 30);
        mensa.setOpeningTime(openingTime);
        campus.readOpeningHours(new EmptyStream());
        assertSame(openingTime, mensa.getOpeningTime());

        // also test that method does *something*
        mensa.setOpeningTime(null);
        var in = new ByteArrayInputStream("""
                Mensa 11:00
                """.getBytes(UTF_8));
        campus.readOpeningHours(in);
        assertNotNull(mensa.getOpeningTime());
    }

    @Order(8)
    @Test
    void invalidInputIoExceptionsNotCaught() {
        var mensa = new Room("Mensa");
        var bibliothek = new Room("Bibliothek");
        var sekretariat = new Room("Sekretariat");
        var campus = new Campus(List.of(mensa, bibliothek, sekretariat));

        var in1 = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat 09:15
                Studiensaal 11:30
                """.getBytes(UTF_8));
        assertThrows(ParseException.class, () -> campus.readOpeningHours(in1));

        var in2 = new ByteArrayInputStream("""
                Bibliothek  08:45
                Sekretariat 09:15
                Mensa       24:00
                """.getBytes(UTF_8));
        assertThrows(ParseException.class, () -> campus.readOpeningHours(in2));

        var e = new IOException();
        var in = new ThrowingStream(e);
        var caught = assertThrows(IOException.class, () -> {
            campus.readOpeningHours(in);
        });
        assertSame(e, caught);
    }

    @Order(9)
    @Test
    void streamClosed() throws IOException {
        var in1 = new EmptyStream();
        new Campus(List.of()).readOpeningHours(in1);
        assertTrue(in1.closed);

        var in2 = new ThrowingStream(new IOException());
        assertThrows(IOException.class, () -> {
            new Campus(List.of()).readOpeningHours(in2);
        });
        assertTrue(in2.closed);
    }

    static class EmptyStream extends InputStream {
        boolean closed;
        public int read() { return -1; }
        public void close() { closed = true; }
    }

    static class ThrowingStream extends InputStream {
        boolean closed;
        private IOException e;

        public ThrowingStream(IOException e) {
            this.e = e;
        }

        public int read() throws IOException { throw e; }
        public void close() { closed = true; }
    }
}
