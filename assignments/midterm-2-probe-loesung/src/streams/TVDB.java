package streams;

import tv.Season;
import tv.TVShow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

public class TVDB {

    private final List<TVShow> shows = new ArrayList<>();

    public TVDB(List<TVShow> shows) {
        this.shows.addAll(shows);
    }

    /**
     * Gibt eine Map zurück, welche für jede Serie einen Eintrag mit dem Titel und der Anzahl der
     * Staffeln enthält. Serien ohne Staffeln kommen nicht in der Map vor. Es wird davon
     * ausgegangen, dass keine zwei Serien denselben Titel haben.
     * <p>
     * Wichtig: Diese Methode muss mit Streams gelöst werden, d. h. es sollen keine Schleifen (oder
     * forEach o. ä.) in der Lösung vorkommen.
     */
    public Map<String, Integer> seasonsPerShow() {
        return shows.stream()
                .filter(show -> show.seasonCount() > 0)
                .collect(toMap(TVShow::title, TVShow::seasonCount));
    }

    /**
     * Gibt die Titel der (bis zu) 'n' Serien mit den meisten Staffeln zurück. Die Reihenfolge der
     * Serien ist absteigend nach der Anzahl Staffeln.
     * <p>
     * Wichtig: Diese Methode muss mit Streams gelöst werden, d. h. es sollen keine Schleifen (oder
     * forEach o. ä.) in der Lösung vorkommen.
     */
    public List<String> showsWithMostSeasons(int n) {
        return shows.stream()
                .sorted(comparingInt(show -> -show.seasons().size()))
                .limit(n)
                .map(TVShow::title)
                .toList();
    }

    /**
     * Gibt die Anzahl der Episoden in der längsten Staffel aller Serien zurück. Falls keine Staffel
     * vorhanden ist, wird 0 zurückgegeben.
     * <p>
     * Wichtig: Diese Methode muss mit Streams gelöst werden, d. h. es sollen keine Schleifen (oder
     * forEach o. ä.) in der Lösung vorkommen.
     */
    public int maxEpisodesPerSeason() {
        return shows.stream()
                .flatMap(s -> s.seasons().stream())
                .mapToInt(Season::episodes)
                .max()
                .orElse(0);

        // oder:
        // return shows.stream()
        //         .flatMap(s -> s.seasons().stream())
        //         .map(Season::episodes)
        //         .max(naturalOrder())
        //         .orElse(0);

        // oder:
        // var sorted = shows.stream()
        //         .flatMap(s -> s.seasons().stream())
        //         .map(Season::episodes)
        //         .sorted(reverseOrder())
        //         .limit(1)
        //         .toList();
        // return sorted.isEmpty() ? 0 : sorted.get(0);

        // oder:
        // var sorted = shows.stream()
        //         .flatMap(s -> s.seasons().stream())
        //         .map(Season::episodes)
        //         .sorted()
        //         .toList();
        // return sorted.isEmpty() ? 0 : sorted.get(sorted.size() - 1);
    }
}
