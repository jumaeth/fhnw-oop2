package streams;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class CountryExample2 {

    public static void main(String[] args) {
        var countries = List.of(
                new Country("IT", "Italy",       301_230,    58_853_000, false),
                new Country("JP", "Japan",       377_975,   124_840_000, false),
                new Country("FR", "France",      643_801,    68_042_000, false),
                new Country("CH", "Switzerland",  41_285,     8_738_000, true),
                new Country("DE", "Germany",     357_592,    84_260_000, false),
                new Country("IN", "India",     3_287_263, 1_392_329_000, false),
                new Country("PL", "Poland",      312_696,    38_036_000, false));

        var result = findLargest3InEurope(countries);
        result.forEach(System.out::println);
    }

    private static List<String> findLargest3InEurope(List<Country> countries) {
        return countries.stream()
                .filter(Continents::inEurope)
                .filter(c -> c.getArea() > 100_000)
                .sorted(comparing(Country::getArea))
                .limit(3)
                .map(Country::getCode)
                .collect(toList());
    }
}
