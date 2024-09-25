package webtraffic;

import java.util.ArrayList;
import java.util.List;

public class Counter<T> {

    private final List<T> elements = new ArrayList<>();
    private final List<Integer> counts = new ArrayList<>();

    public void add(T s) {
        int index = elements.indexOf(s);
        if (index >= 0) {
            counts.set(index, counts.get(index) + 1);
        } else {
            elements.add(s);
            counts.add(1);
        }
    }

    public List<T> allElements() {
        return new ArrayList<>(elements); // copy to ensure encapsulation
    }

    public List<T> topStrings(int n) {
        var copy = allElements();
        copy.sort((s1, s2) -> countFor(s2) - countFor(s1));
        return copy.subList(0, Math.min(n, copy.size()));
    }

    public int countFor(T s) {
        return counts.get(elements.indexOf(s));
    }
}
