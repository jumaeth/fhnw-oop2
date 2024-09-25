package tv;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Collections.unmodifiableList;

public class TVShow {
    private final String title;
    private final List<Season> seasons = new ArrayList<>();

    private final List<Consumer<Season>> watchers = new ArrayList<>();

    public TVShow(String title) {
        this.title = title;
    }

    /**
     * Gibt den Titel dieser Serie zurück.
     */
    public String title() {
        return title;
    }

    /**
     * Gibt die Anzahl der Staffeln in dieser Serie zurück.
     */
    public int seasonCount() {
        return seasons.size();
    }

    /**
     * Gibt die Staffel mit der gegebenen Nummer zurück. Diese Nummer ist 1-basiert,
     * d. h., die erste Staffel hat die Nummer 1, die zweite 2, usw.
     */
    public Season getSeason(int number) {
        if (number > seasonCount()) {
            throw new IllegalArgumentException("season does not exist");
        }
        return seasons.get(number - 1);
    }

    /**
     * Gibt eine Liste aller Staffeln dieser Serie zurück. Die zurückgegebene List erlaubt keine
     * Änderungen (wirft {@link UnsupportedOperationException}).
     */
    public List<Season> seasons() {
        return unmodifiableList(seasons);
    }

    /**
     * Fügt eine neue Staffel zu dieser Serie hinzu, mit der gegebenen Anzahl Episoden und dem
     * gegebenen Veröffentlichungsdatum. Die Nummer der Staffel wird automatisch vergeben.
     * <p>
     * Nach dem Hinzufügen einer Staffel werden alle registrierten Watcher benachrichtigt.
     */
    public void addSeason(int episodes, Date releaseDate) {
        var number = seasonCount() + 1;
        var season = new Season(this, number, episodes, releaseDate);
        seasons.add(season);
        watchers.forEach(w -> w.accept(season));
    }

    public void addWatcher(Consumer<Season> watcher) {
        if (watcher == null) {
            throw new NullPointerException();
        }
        watchers.add(watcher);
    }
}
