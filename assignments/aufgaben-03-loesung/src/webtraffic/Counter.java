package webtraffic;

import java.util.*;

public class Counter<E> {

    private final Map<E, Integer> counts = new HashMap<>();

    public void add(E elem) {
        var count = counts.get(elem);
        if (count == null) {
            counts.put(elem, 0);
        } else {
            counts.put(elem, count + 1);
        }

        // or:
        // var count = counts.getOrDefault(elem, 0);
        // counts.put(elem, count + 1);

        // or:
        // counts.merge(elem, 1, Integer::sum);
    }

    public List<E> allElements() {
        return new ArrayList<>(counts.keySet());
    }

    public List<E> topElements(int n) {
        var copy = allElements();
        copy.sort((e1, e2) -> countFor(e2) - countFor(e1));
        return copy.subList(0, Math.min(n, copy.size()));
    }

    public int countFor(E elem) {
        return counts.get(elem);
    }
}
