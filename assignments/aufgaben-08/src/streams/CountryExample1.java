package streams;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class CountryExample1 {

    public static void main(String[] args) {
        var countries = List.of(
                new Country("IT", "Italy",        301_230, 58_853_000, false),
                new Country("AD", "Andorra",          467,     79_000, true),
                new Country("AT", "Austria",       83_871,  9_027_000, true),
                new Country("FR", "France",       643_801, 68_042_000, false),
                new Country("CH", "Switzerland",   41_285,  8_738_000, true),
                new Country("DE", "Germany",      357_592, 84_260_000, false),
                new Country("VA", "Vatican City",       0,        453, true),
                new Country("PL", "Poland",       312_696, 38_036_000, false),
                new Country("LI", "Liechtenstein",    160,     38_000, true));

        var result = findSmallCountries(countries);
        result.forEach(System.out::println);
    }

    private static List<String> findSmallCountries(List<Country> countries) {
        // TODO
        return null;
    }
}
