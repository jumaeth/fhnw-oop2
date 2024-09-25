package games;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BossBeatersTest {

    Game ping = new Game("Ping", 1);
    Game giovanniBros = new Game("Giovanni Bros.", 99);
    Game superGiovanniBros = new Game("Super Giovanni Bros.", 32);
    Game superGiovanni64 = new Game("Super Giovanni 64", 15);
    Game silverEye = new Game("SilverEye", 20);
    List<Game> games = List.of(ping, giovanniBros, superGiovanniBros, superGiovanni64, silverEye);

    Date date = new Date(1, 1, 1970);

    Player playerOne = new Player("One", date);
    Player playerTwo = new Player("Two", date);
    Player playerFoo = new Player("Foo", date);
    Player playerBar = new Player("Bar", date);
    Player playerBaz = new Player("Baz", date);
    List<Player> players = List.of(playerOne, playerTwo, playerFoo, playerBar, playerBaz);

    List<Score> scores = List.of(
            new Score(playerOne, ping, date, 0, 0),
            new Score(playerOne, ping, date, 50, 0),
            new Score(playerTwo, ping, date, 100, 1),                // finished
            new Score(playerBaz, ping, date, 200, 1),                // finished
            new Score(playerOne, giovanniBros, date, 213, 7),
            new Score(playerOne, giovanniBros, date, 213, 12),
            new Score(playerOne, giovanniBros, date, 213, 18),
            new Score(playerBaz, giovanniBros, date, 213, 99),       // finished
            new Score(playerOne, superGiovanniBros, date, 8500, 32), // finished
            new Score(playerTwo, superGiovanniBros, date, 8700, 32), // finished
            new Score(playerFoo, superGiovanniBros, date, 7200, 32), // finished
            new Score(playerBar, superGiovanniBros, date, 9000, 32), // finished
            new Score(playerBaz, superGiovanniBros, date, 9300, 32), // finished
            new Score(playerOne, superGiovanni64, date, 34, 14),
            new Score(playerOne, superGiovanni64, date, 119, 15),    // finished
            new Score(playerTwo, superGiovanni64, date, 57, 12),
            new Score(playerTwo, superGiovanni64, date, 43, 14),
            new Score(playerTwo, superGiovanni64, date, 75, 15),     // finished
            new Score(playerTwo, superGiovanni64, date, 3, 1),
            new Score(playerFoo, superGiovanni64, date, 18, 5),
            new Score(playerBar, superGiovanni64, date, 69, 14),
            new Score(playerBaz, superGiovanni64, date, 71, 15),     // finished
            new Score(playerFoo, silverEye, date, 9001, 18),
            new Score(playerFoo, silverEye, date, 550, 3),
            new Score(playerBaz, silverEye, date, 21931, 20),        // finished
            // finished multiple times:
            new Score(playerOne, superGiovanni64, date, 70, 15),     // finished
            new Score(playerOne, superGiovanni64, date, 70, 15));    // finished

    @Test
    void testSome() {
        var platform = new GamePlatform(games, players, scores.subList(0, 25));
        var result = platform.bossBeaters();
        assertEquals(Set.of(playerTwo, playerBaz), result);
    }

    @Test
    void testSamePlayerMultipleTimes() {
        var platform = new GamePlatform(games, players, scores);
        var result = platform.bossBeaters();
        assertEquals(Set.of(playerTwo, playerBaz), result);
    }

    @Test
    void testNone() {
        var platform = new GamePlatform(games, players, scores.subList(0, 3));
        var result = platform.bossBeaters();
        assertEquals(emptySet(), result);
    }

    @Test
    void testEmptyScores() {
        var platform = new GamePlatform(games, players, emptyList());
        var result = platform.bossBeaters();
        assertEquals(emptySet(), result);
    }

    @Test
    void testEmptyGames() {
        var platform = new GamePlatform(emptyList(), players, emptyList());
        var result = platform.bossBeaters();
        assertEquals(emptySet(), result);
    }
}
