package games;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NamesOfLargeGamesTest {

    Game ping = new Game("Ping", 1);
    Game breakin = new Game("Breakin", 2);
    Game sirtet = new Game("Sirtet", 1);
    Game giovanniBros = new Game("Giovanni Bros.", 99);
    Game superGiovanniBros = new Game("Super Giovanni Bros.", 32);
    Game superGiovanni64 = new Game("Super Giovanni 64", 15);
    Game silverEye = new Game("SilverEye", 20);
    Game sssm = new Game("Super Smash Sisters Melee", 29);
    Game breathOfTheTame = new Game("Breath of the Tame", 1);
    Game theOneWithTen = new Game("The One With Ten", 10);

    @Test
    void testSimple() {
        var platform = new GamePlatform(List.of(ping, breakin, sirtet, giovanniBros,
                superGiovanniBros, superGiovanni64, silverEye, sssm, breathOfTheTame),
                emptyList(), emptyList());

        var result = platform.namesOfLargeGames();
        assertEquals(List.of("Giovanni Bros.", "Super Giovanni Bros.", "Super Giovanni 64",
                "SilverEye", "Super Smash Sisters Melee"), result);
    }

    @Test
    void testTen() {
        var platform = new GamePlatform(List.of(ping, theOneWithTen, giovanniBros),
                emptyList(), emptyList());

        var result = platform.namesOfLargeGames();
        assertEquals(List.of("The One With Ten", "Giovanni Bros."), result);
    }

    @Test
    void testNoneMatch() {
        var platform = new GamePlatform(List.of(ping, breakin, sirtet),
                emptyList(), emptyList());

        var result = platform.namesOfLargeGames();
        assertEquals(emptyList(), result);
    }

    @Test
    void testEmpty() {
        var platform = new GamePlatform(emptyList(), emptyList(), emptyList());
        var result = platform.namesOfLargeGames();
        assertEquals(emptyList(), result);
    }
}
