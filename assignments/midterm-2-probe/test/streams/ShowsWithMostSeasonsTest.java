package streams;

import org.junit.jupiter.api.Test;
import tv.Date;
import tv.TVShow;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

public class ShowsWithMostSeasonsTest {

    Date irrelevant = new Date(1970, 1, 1);

    TVShow chernobyl = new TVShow("Chernobyl") {{
        addSeason(5, irrelevant);
    }};

    TVShow theLeftovers = new TVShow("The Leftovers") {{
        addSeason(10, irrelevant);
        addSeason(10, irrelevant);
        addSeason(8, irrelevant);
    }};

    TVShow sherlock = new TVShow("Sherlock") {{
        addSeason(3, irrelevant);
        addSeason(3, irrelevant);
        addSeason(3, irrelevant);
        addSeason(3, irrelevant);
    }};

    TVShow breakingBad = new TVShow("Breaking Bad") {{
        addSeason(7, irrelevant);
        addSeason(13, irrelevant);
        addSeason(13, irrelevant);
        addSeason(13, irrelevant);
        addSeason(16, irrelevant);
    }};

    TVShow betterCallSaul = new TVShow("Better Call Saul") {{
        addSeason(10, irrelevant);
        addSeason(10, irrelevant);
        addSeason(10, irrelevant);
        addSeason(10, irrelevant);
        addSeason(10, irrelevant);
        addSeason(13, irrelevant);
    }};

    @Test
    void showWithMostEpisodesEmpty() {
        var tvdb = new TVDB(List.of());
        assertEquals(emptyList(), tvdb.showsWithMostSeasons(0));
        assertEquals(emptyList(), tvdb.showsWithMostSeasons(1));
        assertEquals(emptyList(), tvdb.showsWithMostSeasons(2));
        assertEquals(emptyList(), tvdb.showsWithMostSeasons(3));

        tvdb = new TVDB(List.of(chernobyl, theLeftovers, sherlock, breakingBad, betterCallSaul));
        assertNotEquals(emptyList(), tvdb.showsWithMostSeasons(3));
    }

    @Test
    void showsWithMostEpisodesLimitNegative() {
        var tvdb = new TVDB(List.of(chernobyl, theLeftovers, sherlock, breakingBad, betterCallSaul));
        assertThrows(IllegalArgumentException.class, () -> tvdb.showsWithMostSeasons(-1));
        assertThrows(IllegalArgumentException.class, () -> tvdb.showsWithMostSeasons(-10));

        assertDoesNotThrow(() -> tvdb.showsWithMostSeasons(0));
        assertDoesNotThrow(() -> tvdb.showsWithMostSeasons(1));
        assertDoesNotThrow(() -> tvdb.showsWithMostSeasons(10));
    }

    @Test
    void showsWithMostEpisodesLimit() {
        var tvdb = new TVDB(List.of(chernobyl, theLeftovers, sherlock, breakingBad, betterCallSaul));
        assertNotNull(tvdb.showsWithMostSeasons(0));
        assertEquals(0, tvdb.showsWithMostSeasons(0).size());
        assertEquals(1, tvdb.showsWithMostSeasons(1).size());
        assertEquals(2, tvdb.showsWithMostSeasons(2).size());
        assertEquals(3, tvdb.showsWithMostSeasons(3).size());
        assertEquals(5, tvdb.showsWithMostSeasons(5).size());
        assertEquals(5, tvdb.showsWithMostSeasons(6).size());
        assertEquals(5, tvdb.showsWithMostSeasons(10).size());
    }

    @Test
    void showsWithMostEpisodesSortSingle() {
        var tvdb = new TVDB(List.of(chernobyl, theLeftovers, sherlock, breakingBad, betterCallSaul));
        assertEquals(List.of("Better Call Saul"), tvdb.showsWithMostSeasons(1));

        tvdb = new TVDB(List.of(chernobyl, theLeftovers, sherlock, breakingBad));
        assertEquals(List.of("Breaking Bad"), tvdb.showsWithMostSeasons(1));

        tvdb = new TVDB(List.of(sherlock, chernobyl, theLeftovers));
        assertEquals(List.of("Sherlock"), tvdb.showsWithMostSeasons(1));
    }

    @Test
    void showsWithMostEpisodesSortMultiple() {
        var tvdb = new TVDB(List.of(sherlock, breakingBad, betterCallSaul));
        assertEquals(List.of("Better Call Saul", "Breaking Bad", "Sherlock"),
                tvdb.showsWithMostSeasons(3));

        tvdb = new TVDB(List.of(sherlock, breakingBad, theLeftovers));
        assertEquals(List.of("Breaking Bad", "Sherlock", "The Leftovers"),
                tvdb.showsWithMostSeasons(3));

        tvdb = new TVDB(List.of(sherlock, chernobyl));
        assertEquals(List.of("Sherlock", "Chernobyl"),
                tvdb.showsWithMostSeasons(2));

        tvdb = new TVDB(List.of(chernobyl, theLeftovers, sherlock, breakingBad, betterCallSaul));
        assertEquals(List.of("Better Call Saul", "Breaking Bad", "Sherlock", "The Leftovers", "Chernobyl"),
                tvdb.showsWithMostSeasons(5));
    }

    @Test
    void showsWithMostEpisodesSortLimit() {
        var tvdb = new TVDB(List.of(chernobyl, betterCallSaul, theLeftovers, sherlock, breakingBad));

        assertEquals(List.of("Better Call Saul", "Breaking Bad"),
                tvdb.showsWithMostSeasons(2));
        assertEquals(List.of("Better Call Saul", "Breaking Bad", "Sherlock"),
                tvdb.showsWithMostSeasons(3));
        assertEquals(List.of("Better Call Saul", "Breaking Bad", "Sherlock", "The Leftovers"),
                tvdb.showsWithMostSeasons(4));
        assertEquals(List.of("Better Call Saul", "Breaking Bad", "Sherlock", "The Leftovers", "Chernobyl"),
                tvdb.showsWithMostSeasons(5));
    }

    @Test
    void showsWithMostEpisodesSortLimitTooLarge() {
        var tvdb = new TVDB(List.of(theLeftovers, sherlock, chernobyl, breakingBad, betterCallSaul));
        assertEquals(List.of("Better Call Saul", "Breaking Bad", "Sherlock", "The Leftovers", "Chernobyl"),
                tvdb.showsWithMostSeasons(6));
        assertEquals(List.of("Better Call Saul", "Breaking Bad", "Sherlock", "The Leftovers", "Chernobyl"),
                tvdb.showsWithMostSeasons(10));

        tvdb = new TVDB(List.of(sherlock, chernobyl, breakingBad));
        assertEquals(List.of("Breaking Bad", "Sherlock", "Chernobyl"),
                tvdb.showsWithMostSeasons(3));
        assertEquals(List.of("Breaking Bad", "Sherlock", "Chernobyl"),
                tvdb.showsWithMostSeasons(100));
    }
}
