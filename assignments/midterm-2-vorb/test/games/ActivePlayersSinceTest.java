package games;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivePlayersSinceTest {

    Game superGiovanni64 = new Game("Super Giovanni 64", 15);
    Game silverEye = new Game("SilverEye", 20);
    List<Game> games = List.of(superGiovanni64, silverEye);

    Player playerOne = new Player("One", new Date(1, 1, 1970));
    Player playerTwo = new Player("Two", new Date(1, 1, 2021));
    Player playerFoo = new Player("Foo", new Date(10, 1, 2021));
    Player playerBar = new Player("Bar", new Date(12, 1, 2021));
    Player playerBaz = new Player("Baz", new Date(25, 3, 2021));
    List<Player> players = List.of(playerOne, playerTwo, playerFoo, playerBar, playerBaz);

    List<Score> scores = List.of(
            new Score(playerOne, superGiovanni64, new Date(24, 12, 2020), 70, 15),
            new Score(playerTwo, superGiovanni64, new Date(30, 11, 2020), 57, 12),
            new Score(playerTwo, superGiovanni64, new Date(5, 1, 2021), 3, 1),
            new Score(playerFoo, silverEye, new Date(25, 8, 1997), 9001, 18),
            new Score(playerFoo, silverEye, new Date(20, 7, 2000), 550, 3),
            new Score(playerBaz, silverEye, new Date(3, 4, 2021), 21931, 20));

    GamePlatform platform = new GamePlatform(games, players, scores);

    @Test
    void testPwChanged() {
        var result = platform.activePlayersSince(new Date(11, 1, 2021));
        assertEquals(Set.of(playerBar, playerBaz), result);

        result = platform.activePlayersSince(new Date(20, 3, 2021));
        assertEquals(Set.of(playerBaz), result);
    }

    @Test
    void testScored() {
        var result = platform.activePlayersSince(new Date(4, 1, 2021));
        assertEquals(Set.of(playerTwo, playerFoo, playerBar, playerBaz), result);
    }

    @Test
    void testAll() {
        var result = platform.activePlayersSince(new Date(24, 12, 2020));
        assertEquals(new HashSet<>(players), result);
    }

    @Test
    void testNoneMatch() {
        var result = platform.activePlayersSince(new Date(11, 4, 2021));
        assertEquals(emptySet(), result);
    }

    @Test
    void testEmptyScores() {
        var platform = new GamePlatform(games, players, emptyList());
        var result = platform.activePlayersSince(new Date(1, 12, 2020));
        assertEquals(Set.of(playerTwo, playerFoo, playerBar, playerBaz), result);
    }

    @Test
    void testEmptyPlayers() {
        var platform = new GamePlatform(games, emptyList(), emptyList());

        var result = platform.activePlayersSince(new Date(1, 12, 2020));
        assertEquals(emptySet(), result);

        result = platform.activePlayersSince(new Date(1, 1, 1970));
        assertEquals(emptySet(), result);
    }
}
