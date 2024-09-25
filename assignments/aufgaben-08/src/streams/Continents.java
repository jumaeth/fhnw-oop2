package streams;

import java.util.Set;

public class Continents {

    private static final Set<String> EUROPEAN = Set.of(
            "AD", "AL", "AT", "BA", "BE", "BG", "BY", "CH", "CY", "CZ",
            "DE", "DK", "EE", "ES", "FI", "FR", "GB", "GR", "HR", "HU",
            "IE", "IS", "IT", "LI", "LT", "LU", "LV", "MC", "MD", "ME",
            "MK", "MT", "NL", "NO", "PL", "PT", "RO", "RS", "RU", "SE",
            "SI", "SK", "SM", "TR", "UA", "VA");

    public static boolean inEurope(Country country) {
        return EUROPEAN.contains(country.getCode());
    }

    public static boolean inAfrica(Country country) {
        throw new UnsupportedOperationException(); // too lazy
    }

    public static boolean inAsia(Country country) {
        throw new UnsupportedOperationException(); // too lazy
    }

    public static boolean inNorthAmerica(Country country) {
        throw new UnsupportedOperationException(); // too lazy
    }

    public static boolean inSouthAmerica(Country country) {
        throw new UnsupportedOperationException(); // too lazy
    }

    public static boolean inOceania(Country country) {
        throw new UnsupportedOperationException(); // too lazy
    }
}
