package sort;

import tv.Date;
import tv.Season;

import java.util.List;

import static java.util.Comparator.comparing;

public class SeasonSorter {

    /**
     * Sortiert die gegebene Liste von Staffeln danach, wie früh im Jahr eine Staffel veröffentlicht
     * wurde. Wenn z. B. eine Staffel A am 12.02.2023 und eine Staffel B am 27.01.2024
     * veröffentlicht wurde, zählt Staffel B als die frühere, denn das Jahr wird nicht
     * berücksichtigt.
     * <p>
     * Falls mehrere Staffeln am selben Tag veröffentlicht wurden, gelten Sie als "gleich" und
     * sollen nicht umsortiert werden (relativ zueinander).
     */
    public static void sortByReleaseDateInYear(List<Season> seasons) {
        seasons.sort(comparing(season -> season.releaseDate(),
                comparing(Date::month).thenComparing(Date::day)));

        // oder:
        seasons.sort((s1, s2) -> {
            var d1 = s1.releaseDate();
            var d2 = s2.releaseDate();
            if (d1.month() != d2.month()) {
                return Integer.compare(d1.month(), d2.month());
            } else {
                return Integer.compare(d1.day(), d2.day());
            }
        });

        // oder:
        var sorted = seasons.stream()
                .sorted(comparing(season -> season.releaseDate(),
                        comparing(Date::month).thenComparing(Date::day)))
                .toList();
        seasons.clear();
        seasons.addAll(sorted);
    }
}
