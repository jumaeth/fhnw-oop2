package webtraffic;

import java.util.ArrayList;
import java.util.List;

public class Counter<E> {

    private final List<E> elements = new ArrayList<>();
    private final List<Integer> counts = new ArrayList<>();

    public void add(E elem) {
        int index = elements.indexOf(elem);
        if (index >= 0) {
            counts.set(index, counts.get(index) + 1);
        } else {
            elements.add(elem);
            counts.add(1);
        }
    }

    public List<E> allElements() {
        return new ArrayList<>(elements); // copy to ensure encapsulation
    }

    public List<E> topElements(int n) {
        var copy = allElements();
        copy.sort((e1, e2) -> countFor(e2) - countFor(e1));
        return copy.subList(0, Math.min(n, copy.size()));
    }

    public int countFor(E elem) {
        return counts.get(elements.indexOf(elem));
    }
}
