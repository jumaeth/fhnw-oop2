package games;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FinishedPlayersTest {

    Game ping = new Game("Ping", 1);
    Game superGiovanni64 = new Game("Super Giovanni 64", 15);
    Game silverEye = new Game("SilverEye", 20);
    List<Game> games = List.of(ping, superGiovanni64, silverEye);

    Player playerOne = new Player("One", new Date(1, 1, 1970));
    Player playerTwo = new Player("Two", new Date(1, 1, 2021));
    Player playerFoo = new Player("Foo", new Date(10, 1, 2021));
    Player playerBar = new Player("Bar", new Date(12, 1, 2021));
    Player playerBaz = new Player("Baz", new Date(25, 3, 2021));
    List<Player> players = List.of(playerOne, playerTwo, playerFoo, playerBar, playerBaz);

    List<Score> scores = List.of(
            new Score(playerOne, superGiovanni64, new Date(23, 12, 2020), 34, 14),
            new Score(playerOne, superGiovanni64, new Date(24, 12, 2020), 119, 15),
            new Score(playerTwo, superGiovanni64, new Date(30, 11, 2020), 57, 12),
            new Score(playerTwo, superGiovanni64, new Date(30, 11, 2020), 43, 14),
            new Score(playerTwo, superGiovanni64, new Date(30, 11, 2020), 75, 15),
            new Score(playerTwo, superGiovanni64, new Date(5, 1, 2021), 3, 1),
            new Score(playerFoo, superGiovanni64, new Date(5, 1, 2021), 18, 5),
            new Score(playerBar, superGiovanni64, new Date(5, 1, 2021), 69, 14),
            new Score(playerBaz, superGiovanni64, new Date(7, 1, 2021), 71, 15),
            new Score(playerFoo, silverEye, new Date(25, 8, 1997), 9001, 18),
            new Score(playerFoo, silverEye, new Date(20, 7, 2000), 550, 3),
            new Score(playerBaz, silverEye, new Date(3, 4, 2021), 21931, 20),
            // finished multiple times:
            new Score(playerOne, superGiovanni64, new Date(25, 12, 2020), 70, 15), // 12
            new Score(playerOne, superGiovanni64, new Date(26, 12, 2020), 70, 15),
            // not finished:
            new Score(playerOne, ping, new Date(10, 4, 2021), 0, 0),               // 14
            new Score(playerOne, ping, new Date(11, 4, 2021), 50, 0));

    @Test
    void testSome() {
        var platform = new GamePlatform(games.subList(1, 3), players, scores.subList(0, 12));
        var result = platform.finishedPlayers();
        var expected = Map.of(
                superGiovanni64, 3,
                silverEye, 1);
        assertEquals(expected, result);
    }

    @Test
    void testZeroFinished() {
        var platform = new GamePlatform(games, players, scores.subList(13, 16));
        var result = platform.finishedPlayers();
        var expected = Map.of(
                superGiovanni64, 1,
                silverEye, 0,
                ping, 0);
        assertEquals(expected, result);
    }

    @Test
    void testSamePlayerMultipleTimes() {
        var platform = new GamePlatform(games.subList(1, 3), players, scores.subList(0, 14));
        var result = platform.finishedPlayers();
        var expected = Map.of(
                superGiovanni64, 3,
                silverEye, 1);
        assertEquals(expected, result);
    }

    @Test
    void testEmptyScores() {
        var platform = new GamePlatform(games, players, emptyList());
        var result = platform.finishedPlayers();
        var expected = Map.of(
                superGiovanni64, 0,
                silverEye, 0,
                ping, 0);
        assertEquals(expected, result);
    }

    @Test
    void testEmptyGames() {
        var platform = new GamePlatform(emptyList(), players, emptyList());
        var result = platform.finishedPlayers();
        assertEquals(emptyMap(), result);
    }
}
