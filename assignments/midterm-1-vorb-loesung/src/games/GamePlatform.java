package games;

import java.util.*;
import java.util.Map.Entry;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

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
     */
    public List<String> namesOfLargeGames() {
        var names = new ArrayList<String>();
        for (var game : games) {
            if (game.getLevels() >= 10) {
                names.add(game.getName());
            }
        }
        return names;
    }

    /**
     * Gibt alle Players als Set zurück, welche seit dem angegebenen Datum
     * einen Score gemacht oder das Passwort gewechselt haben.
     */
    public Set<Player> activePlayersSince(Date date) {
        var active = new HashSet<Player>();
        for (var score : scores) {
            if (score.getDate().compareTo(date) >= 0) {
                active.add(score.getPlayer());
            }
        }
        for (var player : players) {
            if (player.getLastPwChange().compareTo(date) >= 0) {
                active.add(player);
            }
        }
        return active;
    }

    /**
     * Gibt eine Map zurück, die für jedes Game angibt, wie viele Players
     * dieses Game durchgespielt haben. Das kann anhand der Scores für
     * ein Game ermittelt werden: Wenn das erreichte Level eines Scores
     * gleich der Anzahl Levels für dieses Game ist, wurde das Game von
     * dem entsprechenden Player durchgespielt.
     */
    public Map<Game, Integer> finishedPlayers() {
        var counts = new HashMap<Game, Integer>();
        for (var game : games) {
            var finished = playersWhoFinished(game);
            counts.put(game, finished.size());
        }
        return counts;
    }

    private Set<Player> playersWhoFinished(Game game) {
        var finished = new HashSet<Player>();
        for (var score : scores) {
            if (score.getGame() == game && score.getLevel() == game.getLevels()) {
                finished.add(score.getPlayer());
            }
        }
        return finished;
    }

    /**
     * Gibt alle Players als Set zurück, welche mindestens 3 verschiedene
     * Games durchgespielt haben (Definition: siehe oben).
     */
    public Set<Player> bossBeaters() {
        var counts = new HashMap<Player, Integer>();
        for (var game : games) {
            for (var player : playersWhoFinished(game)) {
                if (counts.containsKey(player)) {
                    counts.put(player, counts.get(player) + 1);
                } else {
                    counts.put(player, 1);
                }
            }
        }
        var bossBeaters = new HashSet<Player>();
        for (var entry : counts.entrySet()) {
            if (entry.getValue() >= 3) {
                bossBeaters.add(entry.getKey());
            }
        }
        return bossBeaters;
    }
}
