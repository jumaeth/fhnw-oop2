package floodedswitzerland;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MunicipalityReaderTest {

    private final static String NOLINE = """
                """;

    private final static String ONELINE = """
                Municipality;Population;Area
                Aadorf;9422;19.93
                """;

    private final static String TWOLINE = """
                Municipality;Population;Area
                Aadorf;9422;19.93
                Aarau;21'807;12.36
                """;

    @Test
    public void testEmpty() throws IOException {
        List<Municipality> list = MunicipalityReader.read(asStream(NOLINE));

        assertEquals(list.size(), 0);
    }

    @Test
    public void testOneLine() throws IOException {
        List<Municipality> list = MunicipalityReader.read(asStream(ONELINE));

        assertEquals(list.size(), 1);
    }

    @Test
    public void testMultipleLines() throws IOException {
        List<Municipality> list = MunicipalityReader.read(asStream(TWOLINE));

        assertEquals(list.size(), 2);
    }

    @Test
    public void testMunicipality() throws IOException {
        List<Municipality> list = MunicipalityReader.read(asStream(TWOLINE));

        assertEquals(list.get(0).name(), "Aadorf");
    }

    @Test
    public void testPopulation() throws IOException {
        List<Municipality> list = MunicipalityReader.read(asStream(ONELINE));

        assertEquals(list.get(0).population(), 9422);
    }

    @Test
    public void testArea() throws IOException {
        List<Municipality> list = MunicipalityReader.read(asStream(ONELINE));

        assertEquals(list.get(0).area(), 19.93);
    }

    private InputStream asStream(String text) {
        return new ByteArrayInputStream(text.getBytes(US_ASCII));
    }
}
