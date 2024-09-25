package games;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Enthält die Daten für eine Online-Plattform für Videogames. Die
 * Plattform enthält eine Liste von Spielen ({@link Game}s), eine
 * Liste von Spielern ({@link Player}s) und eine Liste von Spieldurchläufen
 * ({@link Score}s).
 */
public class GamePlatform {

    private final List<Game> games;
    private final List<Player> players;
    private final List<Score> scores;

    public GamePlatform(List<Game> games, List<Player> players, List<Score> scores) {
        this.games = games;
        this.players = players;
        this.scores = scores;
    }

    /**
     * Gibt die Namen aller Games mit mindestens 10 Levels zurück.
     * <p>
     * Diese Aufgabe soll mit einer einzigen Stream-Pipeline gelöst werden.
     */
    public List<String> namesOfLargeGames() {
        // TODO
        return null;
    }

    /**
     * Gibt die (bis zu) 'n' höchsten Scores (nach Punkten) für das 'game'
     * zurück. Die Liste ist absteigend nach Punkten sortiert. Falls mehrere
     * Scores mit derselben Punktzahl existieren, kommen jene Scores zuerst,
     * welche zu einem früheren Datum erreicht wurden.
     * <p>
     * Diese Aufgabe soll mit einer einzigen Stream-Pipeline gelöst werden.
     */
    public List<Score> highScoresFor(Game game, int n) {
        // TODO
        return null;
    }

    /**
     * Gibt alle Players als Set zurück, welche seit dem angegebenen Datum
     * einen Score gemacht oder das Passwort gewechselt haben.
     * <p>
     * Diese Aufgabe darf mit mehreren Streams gelöst werden.
     */
    public Set<Player> activePlayersSince(Date date) {
        // TODO
        return null;
    }

    /**
     * Gibt eine Map zurück, die für jedes Game angibt, wie viele Players
     * dieses Game durchgespielt haben. Das kann anhand der Scores für
     * ein Game ermittelt werden: Wenn das erreichte Level eines Scores
     * gleich der Anzahl Levels für dieses Game ist, wurde das Game von
     * dem entsprechenden Player durchgespielt.
     * <p>
     * Diese Aufgabe darf mit mehreren Streams und Schleifen gelöst werden.
     */
    public Map<Game, ? extends Number> finishedPlayers() {
        // TODO
        return null;
    }

    /**
     * Gibt alle Players als Set zurück, welche mindestens 3 verschiedene
     * Games durchgespielt haben (Definition: siehe oben).
     * <p>
     * Diese Aufgabe darf mit mehreren Streams und Schleifen gelöst werden.
     */
    public Set<Player> bossBeaters() {
        // TODO
        return null;
    }
}
