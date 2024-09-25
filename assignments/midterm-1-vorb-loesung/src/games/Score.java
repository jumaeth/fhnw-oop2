package games;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Ein Score-Object repräsentiert einen Spieldurchlauf eines {@link Player}s
 * für ein bestimmtes {@link Game} und enthält dazu das Datum, an dem der
 * Durchlauf gemacht wurde, die erreichten Punkte (der eigentliche "Score")
 * und das erreichte Level (das höchste abgeschlossene).
 */
public class Score {

    private final Player player;
    private final Game game;
    private final Date date;
    private final int points;
    private final int level;

    public Score(Player player, Game game, Date date, int points, int level) {
        this.player = requireNonNull(player);
        this.game = requireNonNull(game);
        this.date = requireNonNull(date);
        this.points = points;
        this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public Date getDate() {
        return date;
    }

    public int getPoints() {
        return points;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Score other
                && date.equals(other.date)
                && game.equals(other.game)
                && player.equals(other.player)
                && points == other.points
                && level == other.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, game, player, points, level);
    }

    @Override
    public String toString() {
        return "Score{" + player + ", " + game + ", " + date + ", " + points + " points, Level " + level + "}";
    }
}
