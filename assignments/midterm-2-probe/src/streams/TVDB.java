package streams;

import tv.TVShow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        // TODO
        return null;
    }

    /**
     * Gibt die Titel der (bis zu) 'n' Serien mit den meisten Staffeln zurück. Die Reihenfolge der
     * Serien ist absteigend nach der Anzahl Staffeln.
     * <p>
     * Wichtig: Diese Methode muss mit Streams gelöst werden, d. h. es sollen keine Schleifen (oder
     * forEach o. ä.) in der Lösung vorkommen.
     */
    public List<String> showsWithMostSeasons(int n) {
        // TODO
        return null;
    }

    /**
     * Gibt die Anzahl der Episoden in der längsten Staffel aller Serien zurück. Falls keine Staffel
     * vorhanden ist, wird 0 zurückgegeben.
     * <p>
     * Wichtig: Diese Methode muss mit Streams gelöst werden, d. h. es sollen keine Schleifen (oder
     * forEach o. ä.) in der Lösung vorkommen.
     */
    public int maxEpisodesPerSeason() {
        // TODO
        return 0;
    }
}
