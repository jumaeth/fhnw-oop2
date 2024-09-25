package tv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TVShowTest {

    @Test
    void constructor() {
        // Gibt keine Punkte, aber Abzug, falls nicht mehr funktioniert
        var show = new TVShow("Breaking Bad");
        assertEquals("Breaking Bad", show.title());
        assertEquals(0, show.seasonCount());

        show = new TVShow("The Wire");
        assertEquals("The Wire", show.title());
        assertEquals(0, show.seasonCount());
    }

    @Test
    void addSeason() {
        // Gibt keine Punkte, aber Abzug, falls nicht mehr funktioniert
        var show = new TVShow("Better Call Saul");

        show.addSeason(10, new Date(2015, 2, 8));
        assertEquals(1, show.seasonCount());
        assertEquals(1, show.getSeason(1).number());
        assertEquals(10, show.getSeason(1).episodes());
        assertEquals(new Date(2015, 2, 8), show.getSeason(1).releaseDate());
        assertEquals("Better Call Saul S1", show.getSeason(1).toString());

        show.addSeason(10, new Date(2016, 2, 15));
        assertEquals(2, show.seasonCount());
        assertEquals(2, show.getSeason(2).number());
        assertEquals(10, show.getSeason(2).episodes());
        assertEquals(new Date(2016, 2, 15), show.getSeason(2).releaseDate());
        assertEquals("Better Call Saul S2", show.getSeason(2).toString());
    }

    @Test
    void addWatcher() {
        var show = new TVShow("Sherlock");

        // the following should compile:
        show.addWatcher((Season season) -> {});
    }

    @Test
    void addWatcherNull() {
        var show = new TVShow("Sherlock");
        assertThrows(NullPointerException.class, () -> {
            show.addWatcher(null);
        });
        assertDoesNotThrow(() -> {
            show.addWatcher(season -> {});
        });
    }

    @Test
    void addWatcherAddSeason() {
        var show = new TVShow("The Leftovers");

        boolean[] called = {false};
        show.addWatcher(ignored -> {
            called[0] = true;
        });

        show.addSeason(10, new Date(2014, 6, 29));

        assertTrue(called[0]);
    }

    @Test
    void addWatcherCorrectArgument() {
        var show = new TVShow("The Leftovers");

        var builder = new StringBuilder();
        show.addWatcher(season -> {
            builder.append(season).append(": ")
                    .append(season.episodes()).append(" Episodes\n");
        });

        show.addSeason(10, new Date(2014, 6, 29));
        show.addSeason(10, new Date(2015, 10, 4));
        show.addSeason(8, new Date(2017, 4, 16));

        assertEquals("""
                The Leftovers S1: 10 Episodes
                The Leftovers S2: 10 Episodes
                The Leftovers S3: 8 Episodes
                """, builder.toString());
    }

    int seasonCount = 0;
    int episodeCount = 0;
    Date lastRelease = null;

    @Test
    void addMultipleWatchers() {
        var show = new TVShow("DuckTales");

        show.addWatcher(season -> seasonCount++);
        show.addWatcher(season -> episodeCount += season.episodes());
        show.addWatcher(season -> lastRelease = season.releaseDate());

        show.addSeason(65, new Date(1987, 9, 18));
        show.addSeason(10, new Date(1988, 11, 24));
        show.addSeason(18, new Date(1989, 9, 18));
        show.addSeason(7, new Date(1990, 9, 10));

        assertEquals(4, seasonCount);
        assertEquals(100, episodeCount);
        assertEquals(new Date(1990, 9, 10), lastRelease);
    }
}
