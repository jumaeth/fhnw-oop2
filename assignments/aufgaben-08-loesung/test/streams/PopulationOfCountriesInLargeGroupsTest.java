package streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PopulationOfCountriesInLargeGroupsTest {

    private static final Country CN = new Country("CN", "China",     9_596_961, 1_439_323_776, false);
    private static final Country IN = new Country("IN", "India",     3_287_263, 1_392_329_000, false);
    private static final Country JP = new Country("JP", "Japan",       377_975,   124_840_000, false);
    private static final Country TH = new Country("TH", "Thailand",    513_120,    69_800_000, false);
    private static final Country KR = new Country("KR", "South Korea", 100_210,    51_709_000, false);

    private static final Country DE = new Country("DE", "Germany", 357_592, 84_260_000, false);
    private static final Country FR = new Country("FR", "France",  643_801, 68_042_000, false);
    private static final Country IT = new Country("IT", "Italy",   301_230, 58_853_000, false);
    private static final Country PL = new Country("PL", "Poland",  312_696, 38_036_000, false);
    private static final Country AT = new Country("AT", "Austria",  83_871,  9_027_000, true);

    private static final Country NO = new Country("NO", "Norway",     323_802, 5_368_000, true);
    private static final Country IS = new Country("IS", "Iceland",    103_000,   364_000, true);
    private static final Country CH = new Country("CH", "Switzerland", 41_285, 8_738_000, true);
    private static final Country LI = new Country("LI", "Liechtenstein",  160,    38_000, true);

    private static final Country AD = new Country("AD", "Andorra",    467, 79_000, true);
    private static final Country VA = new Country("VA", "Vatican City", 0,    453, true);

    private static final CountryGroup ASIA = new CountryGroup("Asia", List.of(CN, IN, JP, TH, KR));
    private static final CountryGroup EU = new CountryGroup("EU", List.of(DE, FR, IT, PL, AT));
    private static final CountryGroup EFTA = new CountryGroup("EFTA", List.of(NO, IS, CH, LI));
    private static final CountryGroup OTHER = new CountryGroup("Other", List.of(AD, VA));

    @Test
    public void testSingleGroup() {
        var result = StreamTasks.populationOfCountriesInLargeGroups(List.of(ASIA));
        var expected = List.of(
                1_439_323_776, 1_392_329_000, 124_840_000, 69_800_000, 51_709_000);
        assertEquals(expected, result);
    }

    @Test
    public void testSingleMatchingGroup() {
        var result = StreamTasks.populationOfCountriesInLargeGroups(List.of(EU, EFTA, OTHER));
        var expected = List.of(
                84_260_000, 68_042_000, 58_853_000, 38_036_000, 9_027_000);
        assertEquals(expected, result);
    }

    @Test
    public void testMultipleGroups() {
        var result = StreamTasks.populationOfCountriesInLargeGroups(List.of(ASIA, EU));
        var expected = List.of(
                1_439_323_776, 1_392_329_000, 124_840_000, 69_800_000, 51_709_000,
                84_260_000, 68_042_000, 58_853_000, 38_036_000, 9_027_000);
        assertEquals(expected, result);
    }

    @Test
    public void testMultipleMatchingGroups() {
        var result = StreamTasks.populationOfCountriesInLargeGroups(List.of(ASIA, EU, EFTA, OTHER));
        var expected = List.of(
                1_439_323_776, 1_392_329_000, 124_840_000, 69_800_000, 51_709_000,
                84_260_000, 68_042_000, 58_853_000, 38_036_000, 9_027_000);
        assertEquals(expected, result);
    }

    @Test
    public void testNoMatch() {
        var result = StreamTasks.populationOfCountriesInLargeGroups(List.of(EFTA, OTHER));
        assertEquals(emptyList(), result);
    }

    @Test
    public void testNoGroups() {
        var result = StreamTasks.populationOfCountriesInLargeGroups(emptyList());
        assertEquals(emptyList(), result);
    }
}
