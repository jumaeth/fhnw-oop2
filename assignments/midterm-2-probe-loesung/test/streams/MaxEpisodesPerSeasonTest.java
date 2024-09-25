package streams;

import org.junit.jupiter.api.Test;
import tv.Date;
import tv.TVShow;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaxEpisodesPerSeasonTest {

    Date irrelevant = new Date(1970, 1, 1);

    TVShow betterCallSaul = new TVShow("Better Call Saul") {{
        addSeason(10, irrelevant);
        addSeason(10, irrelevant);
        addSeason(10, irrelevant);
        addSeason(10, irrelevant);
        addSeason(10, irrelevant);
        addSeason(13, irrelevant);
    }};

    TVShow breakingBad = new TVShow("Breaking Bad") {{
        addSeason(7, irrelevant);
        addSeason(13, irrelevant);
        addSeason(13, irrelevant);
        addSeason(13, irrelevant);
        addSeason(16, irrelevant);
    }};

    TVShow chernobyl = new TVShow("Chernobyl") {{
        addSeason(5, irrelevant);
    }};

    TVShow duckTales = new TVShow("DuckTales") {{
        addSeason(65, irrelevant);
        addSeason(10, irrelevant);
        addSeason(18, irrelevant);
        addSeason(7, irrelevant);
    }};

    TVShow sherlock = new TVShow("Sherlock") {{
        addSeason(3, irrelevant);
        addSeason(3, irrelevant);
        addSeason(3, irrelevant);
        addSeason(3, irrelevant);
    }};

    TVShow theLeftovers = new TVShow("The Leftovers") {{
        addSeason(10, irrelevant);
        addSeason(10, irrelevant);
        addSeason(8, irrelevant);
    }};

    @Test
    void maxEpisodesPerSeasonNoShows() {
        var tvdb = new TVDB(List.of());
        assertEquals(0, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(betterCallSaul, breakingBad, chernobyl, duckTales, sherlock, theLeftovers));
        assertTrue(tvdb.maxEpisodesPerSeason() > 0);
    }

    @Test
    void maxEpisodesPerSeasonNoSeasons() {
        var emptyShow = new TVShow("Empty Show");
        var voidShow = new TVShow("Void Show");
        var nullShow = new TVShow("Null Show");
        var zeroShow = new TVShow("Zero Show");
        var tvdb = new TVDB(List.of(emptyShow, voidShow, nullShow, zeroShow));
        assertEquals(0, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(emptyShow, betterCallSaul, zeroShow, nullShow));
        assertTrue(tvdb.maxEpisodesPerSeason() > 0);

        tvdb = new TVDB(List.of(emptyShow, voidShow, nullShow, duckTales, zeroShow));
        assertTrue(tvdb.maxEpisodesPerSeason() > 0);
    }

    @Test
    void maxEpisodesPerSeasonSingle() {
        var tvdb = new TVDB(List.of(betterCallSaul));
        assertEquals(13, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(breakingBad));
        assertEquals(16, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(chernobyl));
        assertEquals(5, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(duckTales));
        assertEquals(65, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(sherlock));
        assertEquals(3, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(theLeftovers));
        assertEquals(10, tvdb.maxEpisodesPerSeason());
    }

    @Test
    void maxEpisodesPerSeasonFirstSeason() {
        var tvdb = new TVDB(List.of(sherlock));
        assertEquals(3, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(chernobyl, sherlock));
        assertEquals(5, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(chernobyl, sherlock, theLeftovers));
        assertEquals(10, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(chernobyl, sherlock, theLeftovers, duckTales));
        assertEquals(65, tvdb.maxEpisodesPerSeason());
    }

    @Test
    void maxEpisodesPerSeason() {
        var tvdb = new TVDB(List.of(sherlock, breakingBad));
        assertEquals(16, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(betterCallSaul));
        assertEquals(13, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(betterCallSaul, breakingBad, chernobyl));
        assertEquals(16, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(betterCallSaul, breakingBad, chernobyl, duckTales));
        assertEquals(65, tvdb.maxEpisodesPerSeason());

        tvdb = new TVDB(List.of(betterCallSaul, duckTales, breakingBad, chernobyl));
        assertEquals(65, tvdb.maxEpisodesPerSeason());
    }
}
