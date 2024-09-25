package games;

import java.util.*;
import java.util.Map.Entry;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

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
        return games.stream()
                .filter(g -> g.getLevels() >= 10)
                .map(Game::getName)
                .toList();
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
        return scores.stream()
                .filter(s -> s.getGame() == game)
                .sorted(reverseOrder(comparing(Score::getPoints))
                        .thenComparing(Score::getDate))
                .limit(n)
                .toList();
    }

    /**
     * Gibt alle Players als Set zurück, welche seit dem angegebenen Datum
     * einen Score gemacht oder das Passwort gewechselt haben.
     * <p>
     * Diese Aufgabe darf mit mehreren Streams gelöst werden.
     */
    public Set<Player> activePlayersSince(Date date) {
        var active = scores.stream()
                .filter(s -> s.getDate().compareTo(date) >= 0)
                .map(Score::getPlayer)
                .collect(toCollection(HashSet::new));
        players.stream()
                .filter(p -> p.getLastPwChange().compareTo(date) >= 0)
                .forEach(active::add);
        return active;
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
        var counts = new HashMap<Game, Long>();
        for (var game : games) {
            var count = scores.stream()
                    .filter(s -> s.getGame() == game)
                    .filter(s -> s.getLevel() == game.getLevels())
                    .map(Score::getPlayer)
                    .distinct()
                    .count();
            counts.put(game, count);
        }
        return counts;
    }

    /**
     * Gibt alle Players als Set zurück, welche mindestens 3 verschiedene
     * Games durchgespielt haben (Definition: siehe oben).
     * <p>
     * Diese Aufgabe darf mit mehreren Streams und Schleifen gelöst werden.
     */
    public Set<Player> bossBeaters() {
        var counts = new HashMap<Player, Integer>();
        for (var game : games) {
            scores.stream()
                    .filter(s -> s.getGame() == game
                            && s.getLevel() == game.getLevels())
                    .map(Score::getPlayer)
                    .distinct()
                    .forEach(p -> counts.merge(p, 1, Integer::sum));
        }
        return counts.entrySet().stream()
                .filter(e -> e.getValue() >= 3)
                .map(Entry::getKey)
                .collect(toSet());
    }
}
