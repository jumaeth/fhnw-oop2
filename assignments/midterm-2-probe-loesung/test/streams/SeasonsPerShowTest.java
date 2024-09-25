package streams;

import org.junit.jupiter.api.Test;
import tv.Date;
import tv.TVShow;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SeasonsPerShowTest {

    Date irrelevant = new Date(1970, 1, 1);

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

    TVShow theWire = new TVShow("The Wire") {{
        addSeason(13, irrelevant);
        addSeason(12, irrelevant);
        addSeason(12, irrelevant);
        addSeason(13, irrelevant);
        addSeason(10, irrelevant);
    }};

    TVShow sherlock = new TVShow("Sherlock") {{
        addSeason(3, irrelevant);
        addSeason(3, irrelevant);
        addSeason(3, irrelevant);
        addSeason(3, irrelevant);
    }};

    TVShow duckTales = new TVShow("DuckTales") {{
        addSeason(65, irrelevant);
        addSeason(10, irrelevant);
        addSeason(18, irrelevant);
        addSeason(7, irrelevant);
    }};

    @Test
    void seasonsPerShowMapSize() {
        var tvdb = new TVDB(List.of(breakingBad, betterCallSaul, theWire, sherlock, duckTales));
        var result = tvdb.seasonsPerShow();
        assertNotNull(result);
        assertEquals(5, result.size());

        tvdb = new TVDB(List.of(breakingBad, betterCallSaul, theWire, sherlock));
        result = tvdb.seasonsPerShow();
        assertNotNull(result);
        assertEquals(4, result.size());

        tvdb = new TVDB(emptyList());
        result = tvdb.seasonsPerShow();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void seasonsPerShowMapSizeNoSeasons() {
        var futureShow = new TVShow("Future Show"); // no seasons
        var tvdb = new TVDB(List.of(breakingBad, betterCallSaul, futureShow));
        var result = tvdb.seasonsPerShow();
        assertNotNull(result);
        assertEquals(2, result.size());

        tvdb = new TVDB(List.of(futureShow));
        result = tvdb.seasonsPerShow();
        assertEquals(0, result.size());
    }

    @Test
    void seasonsPerShowMapContent() {
        var tvdb = new TVDB(List.of(breakingBad, betterCallSaul, theWire, sherlock, duckTales));
        var result = tvdb.seasonsPerShow();
        assertEquals(Map.of(
                "Breaking Bad", 5,
                "Better Call Saul", 6,
                "The Wire", 5,
                "Sherlock", 4,
                "DuckTales", 4
        ), result);

        tvdb = new TVDB(List.of(theWire, sherlock));
        result = tvdb.seasonsPerShow();
        assertEquals(Map.of(
                "The Wire", 5,
                "Sherlock", 4
        ), result);
    }

    @Test
    void seasonsPerShowMapContentNoSeasons() {
        var futureShow1 = new TVShow("Future Show 1"); // no seasons
        var futureShow2 = new TVShow("Future Show 2"); // no seasons

        var tvdb = new TVDB(List.of(breakingBad, betterCallSaul, futureShow1));
        var result = tvdb.seasonsPerShow();
        assertEquals(Map.of(
                "Breaking Bad", 5,
                "Better Call Saul", 6
        ), result);

        tvdb = new TVDB(List.of(futureShow1, futureShow2, duckTales));
        result = tvdb.seasonsPerShow();
        assertEquals(Map.of(
                "DuckTales", 4
        ), result);

        tvdb = new TVDB(List.of(futureShow1, futureShow2));
        result = tvdb.seasonsPerShow();
        assertEquals(emptyMap(), result);
    }
}
