package sort;

import org.junit.jupiter.api.Test;
import tv.Date;
import tv.Season;
import tv.TVShow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SeasonSorterTest {

    TVShow breakingBad = new TVShow("Breaking Bad") {{
        addSeason(7, new Date(2008, 1, 20));    // earliest
        addSeason(13, new Date(2009, 3, 8));
        addSeason(13, new Date(2010, 3, 21));
        addSeason(13, new Date(2011, 7, 17));
    }};

    TVShow betterCallSaul = new TVShow("Better Call Saul") {{
        addSeason(10, new Date(2015, 2, 8));    // earliest
        addSeason(10, new Date(2016, 2, 15));
        addSeason(10, new Date(2017, 4, 10));
        addSeason(10, new Date(2018, 8, 6));
    }};

    TVShow theLeftovers = new TVShow("The Leftovers") {{
        addSeason(10, new Date(2014, 6, 29));
        addSeason(10, new Date(2015, 10, 4));
        addSeason(8, new Date(2017, 4, 16));    // earliest
    }};

    TVShow manifest = new TVShow("Manifest") {{
        addSeason(16, new Date(2018, 9, 24));
        addSeason(13, new Date(2019, 1, 6));    // earliest
        addSeason(13, new Date(2020, 4, 13));
        addSeason(20, new Date(2022, 11, 4));
    }};

    TVShow threeBodyProblem = new TVShow("3 Body Problem") {{
        addSeason(8, new Date(2024, 3, 21));
    }};

    TVShow sherlock = new TVShow("Sherlock") {{
        addSeason(3, new Date(2010, 7, 25));
        addSeason(3, new Date(2012, 1, 1));     // earliest
        addSeason(3, new Date(2014, 1, 1));     // earliest
        addSeason(3, new Date(2016, 1, 1));     // earliest
    }};

    @Test
    void sortByReleaseDateInYearAnyReordering() {
        var seasons = new ArrayList<>(manifest.seasons());

        SeasonSorter.sortByReleaseDateInYear(seasons);

        assertNotEquals(manifest.seasons(), seasons);                       // changed order
        assertEquals(Set.copyOf(manifest.seasons()), Set.copyOf(seasons));  // but same elements
    }

    @Test
    void sortByReleaseDateInYearSimple() {
        // simple cases, as later seasons were also released later *in the year*
        var seasons = new ArrayList<>(List.of(
                breakingBad.getSeason(3),
                breakingBad.getSeason(2),
                breakingBad.getSeason(1),
                breakingBad.getSeason(4)));
        SeasonSorter.sortByReleaseDateInYear(seasons);
        assertEquals(breakingBad.seasons(), seasons);

        seasons = new ArrayList<>(List.of(
                betterCallSaul.getSeason(4),
                betterCallSaul.getSeason(3),
                betterCallSaul.getSeason(1),
                betterCallSaul.getSeason(2)));
        SeasonSorter.sortByReleaseDateInYear(seasons);
        assertEquals(betterCallSaul.seasons(), seasons);
    }

    @Test
    void sortByReleaseDateInYearIgnoreYear() {
        var seasons = new ArrayList<>(manifest.seasons());
        SeasonSorter.sortByReleaseDateInYear(seasons);
        var expected = List.of(
                manifest.getSeason(2),
                manifest.getSeason(3),
                manifest.getSeason(1),
                manifest.getSeason(4));
        assertEquals(expected, seasons);

        seasons = new ArrayList<>(theLeftovers.seasons());
        SeasonSorter.sortByReleaseDateInYear(seasons);
        expected = List.of(
                theLeftovers.getSeason(3),
                theLeftovers.getSeason(1),
                theLeftovers.getSeason(2));
        assertEquals(expected, seasons);
    }

    @Test
    void sortByReleaseDateInYearSameDay() {
        var seasons = new ArrayList<>(sherlock.seasons());
        SeasonSorter.sortByReleaseDateInYear(seasons);
        var expected = List.of(
                sherlock.getSeason(2),
                sherlock.getSeason(3),
                sherlock.getSeason(4),
                sherlock.getSeason(1));
        assertEquals(expected, seasons);

        seasons = new ArrayList<>(List.of(
                sherlock.getSeason(4),
                sherlock.getSeason(1),
                sherlock.getSeason(2),
                sherlock.getSeason(3)));
        SeasonSorter.sortByReleaseDateInYear(seasons);
        expected = List.of(
                sherlock.getSeason(4),
                sherlock.getSeason(2),
                sherlock.getSeason(3),
                sherlock.getSeason(1));
        assertEquals(expected, seasons);
    }

    @Test
    void sortByReleaseDateInYearSpecialCases() {
        // empty list
        var seasons = new ArrayList<Season>();
        SeasonSorter.sortByReleaseDateInYear(seasons);
        assertEquals(emptyList(), seasons);

        // single season
        seasons = new ArrayList<>(threeBodyProblem.seasons());
        SeasonSorter.sortByReleaseDateInYear(seasons);
        assertEquals(threeBodyProblem.seasons(), seasons);

        // test also a more general case (to prevent free points)
        seasons = new ArrayList<>(List.of(
                sherlock.getSeason(1),
                sherlock.getSeason(2)));
        SeasonSorter.sortByReleaseDateInYear(seasons);
        assertEquals(List.of(
                sherlock.getSeason(2),
                sherlock.getSeason(1)), seasons);
        // sorting again should leave the list unchanged
        SeasonSorter.sortByReleaseDateInYear(seasons);
        assertEquals(List.of(
                sherlock.getSeason(2),
                sherlock.getSeason(1)), seasons);
    }
}
