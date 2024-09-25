package streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupByNameLengthTest {

    private static final Country IN = new Country("IN", "India",    3_287_263, 1_392_329_000, false);
    private static final Country JP = new Country("JP", "Japan",      377_975,   124_840_000, false);
    private static final Country DE = new Country("DE", "Germany",    357_592,    84_260_000, false);
    private static final Country FR = new Country("FR", "France",     643_801,    68_042_000, false);
    private static final Country IT = new Country("IT", "Italy",      301_230,    58_853_000, false);
    private static final Country PL = new Country("PL", "Poland",     312_696,    38_036_000, false);
    private static final Country AT = new Country("AT", "Austria",     83_871,     9_027_000, true);
    private static final Country CH = new Country("CH", "Switzerland", 41_285,     8_738_000, true);
    private static final Country AD = new Country("AD", "Andorra",        467,        79_000, true);
    private static final Country LI = new Country("LI", "Liechtenstein",  160,        38_000, true);
    private static final Country VA = new Country("VA", "Vatican City",     0,           453, true);

    private static final List<Country> ALL = List.of(IN, JP, DE, FR, IT, PL, AT, CH, AD, LI, VA);

    @Test
    public void testAll() {
        var result = StreamTasks.groupByNameLength(ALL);
        var expected = Map.of(
                5, List.of(IN, JP, IT),
                6, List.of(FR, PL),
                7, List.of(DE, AT, AD),
                11, List.of(CH),
                12, List.of(VA),
                13, List.of(LI));
        assertEquals(expected, result);
    }

    @Test
    public void testSome() {
        var result = StreamTasks.groupByNameLength(ALL.subList(0, 7));
        var expected = Map.of(
                5, List.of(IN, JP, IT),
                6, List.of(FR, PL),
                7, List.of(DE, AT));
        assertEquals(expected, result);
    }

    @Test
    public void testSingle() {
        var result = StreamTasks.groupByNameLength(List.of(CH));
        var expected = Map.of(11, List.of(CH));
        assertEquals(expected, result);
    }

    @Test
    public void testEmpty() {
        var result = StreamTasks.groupByNameLength(emptyList());
        assertEquals(emptyMap(), result);
    }
}
