package games;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Ein Player besteht aus einem Username und einem Datum, welches
 * angibt, wann dieser Player zuletzt sein Passwort geändert hat.
 */
public class Player {

    private final String username;
    private final Date lastPwChange;

    public Player(String username, Date lastPwChange) {
        this.username = requireNonNull(username);
        this.lastPwChange = requireNonNull(lastPwChange);
    }

    public String getUsername() {
        return username;
    }

    public Date getLastPwChange() {
        return lastPwChange;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Player player
                && username.equals(player.username)
                && lastPwChange.equals(player.lastPwChange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, lastPwChange);
    }

    @Override
    public String toString() {
        return "Player " + username;
    }
}
