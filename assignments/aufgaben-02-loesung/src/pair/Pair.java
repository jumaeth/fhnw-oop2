package pair;

public class Pair<F, S> {

    private final F first;
    private final S second;

    public Pair(F first, S second) {
        if (first == null || second == null) {
            throw new NullPointerException();
        }
        this.first = first;
        this.second = second;
    }

    public F first() {
        return first;
    }

    public S second() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
