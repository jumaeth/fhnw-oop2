package games;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HighScoresForTest {

    Game superGiovanni64 = new Game("Super Giovanni 64", 15);
    Game silverEye = new Game("SilverEye", 20);
    List<Game> games = List.of(superGiovanni64, silverEye);

    Player playerOne = new Player("One", new Date(1, 1, 1970));
    List<Player> players = List.of(playerOne);

    List<Score> scores = List.of(
            new Score(playerOne, superGiovanni64, new Date(24, 12, 2020), 70, 15),  // 0
            new Score(playerOne, superGiovanni64, new Date(30, 11, 2020), 57, 12),  // 1
            new Score(playerOne, superGiovanni64, new Date(2, 1, 2021), 3, 1),      // 2
            new Score(playerOne, superGiovanni64, new Date(4, 11, 2020), 113, 15),  // 3
            new Score(playerOne, superGiovanni64, new Date(2, 11, 2020), 113, 15),  // 4
            new Score(playerOne, superGiovanni64, new Date(11, 11, 2020), 113, 15), // 5
            new Score(playerOne, superGiovanni64, new Date(5, 2, 2021), 119, 15),   // 6
            new Score(playerOne, superGiovanni64, new Date(1, 3, 1997), 120, 15),   // 7
            new Score(playerOne, superGiovanni64, new Date(30, 3, 2021), 99, 15),   // 8
            new Score(playerOne, superGiovanni64, new Date(31, 1, 2021), 118, 15),  // 9
            new Score(playerOne, silverEye, new Date(25, 8, 1997), 9001, 18),       // 10
            new Score(playerOne, silverEye, new Date(20, 7, 2000), 550, 3),         // 11
            new Score(playerOne, silverEye, new Date(1, 1, 2021), 21931, 20));      // 12

    GamePlatform platform = new GamePlatform(games, players, scores);

    @Test
    void testSingle() {
        var result = platform.highScoresFor(superGiovanni64, 1);
        assertEquals(List.of(scores.get(7)), result);

        result = platform.highScoresFor(silverEye, 1);
        assertEquals(List.of(scores.get(12)), result);
    }

    @Test
    void testSome() {
        var result = platform.highScoresFor(superGiovanni64, 2);
        assertEquals(List.of(scores.get(7), scores.get(6)), result);

        result = platform.highScoresFor(superGiovanni64, 3);
        assertEquals(List.of(scores.get(7), scores.get(6), scores.get(9)), result);

        result = platform.highScoresFor(silverEye, 3);
        assertEquals(List.of(scores.get(12), scores.get(10), scores.get(11)), result);
    }

    @Test
    void testSortedByDate() {
        var result = platform.highScoresFor(superGiovanni64, 6);
        assertEquals(List.of(scores.get(7), scores.get(6), scores.get(9),
                scores.get(4), scores.get(3), scores.get(5)), result);
    }

    @Test
    void testNotEnough() {
        var result = platform.highScoresFor(silverEye, 4);
        assertEquals(List.of(scores.get(12), scores.get(10), scores.get(11)), result);

        result = platform.highScoresFor(silverEye, 100);
        assertEquals(List.of(scores.get(12), scores.get(10), scores.get(11)), result);
    }

    @Test
    void testNoneMatch() {
        var platform = new GamePlatform(games, players, scores.subList(0, 10));
        var result = platform.highScoresFor(silverEye, 3);
        assertEquals(emptyList(), result);
    }

    @Test
    void testEmptyScores() {
        var platform = new GamePlatform(games, players, emptyList());
        var result = platform.highScoresFor(silverEye, 3);
        assertEquals(emptyList(), result);
    }
}
