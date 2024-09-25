package games;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Ein Game besteht aus einem Namen und aus einer Anzahl Levels.
 */
public class Game {

    private final String name;
    private final int levels;

    public Game(String name, int levels) {
        this.name = requireNonNull(name);
        this.levels = levels;
    }

    public String getName() {
        return name;
    }

    public int getLevels() {
        return levels;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Game other
                && levels == other.levels
                && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levels, name);
    }

    @Override
    public String toString() {
        return "Game " + name;
    }
}
