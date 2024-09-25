package floodedswitzerland;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinateReaderTest {

    private final static String NOLINE = """
            """;

    private final static String ONELINE = """
            Municipality;East;North
            Aadorf;709940;261380
            """;

    private final static String TWOLINE = """
            Municipality;East;North
            Aadorf;709940;261380
            Aarau;645731;249290
                """;

    private final static String SPECIAL = """
            Municipality;East;North
            NotHere;11;11
            """;

    @Test
    public void testEastCoordinate() throws IOException {
        Map<Municipality, Coordinate> map = CoordinateReader.read(asStream(ONELINE), getMunicipalities());

        assertEquals(map.get(getMunicipalities().get(0)).east(), 709940d);
    }

    @Test
    public void testNorthCoordinate() throws IOException {
        Map<Municipality, Coordinate> map = CoordinateReader.read(asStream(ONELINE), getMunicipalities());

        assertEquals(map.get(getMunicipalities().get(0)).north(), 261380d);
    }

    @Test
    public void testMunicipalityNotInCSV() {
        assertThrows(IOException.class, () -> CoordinateReader.read(asStream(SPECIAL), getMunicipalities()));
    }

    @Test
    public void testEmpty() throws IOException {
        Map<Municipality, Coordinate> map = CoordinateReader.read(asStream(NOLINE), getMunicipalities());

        assertEquals(map.size(), 0);
    }

    @Test
    public void testOneLine() throws IOException {
        Map<Municipality, Coordinate> map = CoordinateReader.read(asStream(ONELINE), getMunicipalities());

        assertEquals(map.size(), 1);
    }

    @Test
    public void testMultipleLines() throws IOException {
        Map<Municipality, Coordinate> map = CoordinateReader.read(asStream(TWOLINE), getMunicipalities());

        assertEquals(map.size(), 2);
    }

    private InputStream asStream(String text) {
        return new ByteArrayInputStream(text.getBytes(US_ASCII));
    }

    private List<Municipality> getMunicipalities() {
        return List.of(
                new Municipality("Aadorf", 9422, 19.93),
                new Municipality("Aarau", 21807, 12.36)
        );
    }
}
