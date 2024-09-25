package nonnulllist;

import java.util.ArrayList;
import java.util.List;

public class NonNullList<E> {
    private final List<E> delegate = new ArrayList<>();

    public NonNullList() {
    }

    public NonNullList(List<E> elements) {
        for (var e : elements) {
            if (e == null) {
                throw new NullPointerException();
            }
        }
        delegate.addAll(elements);
    }

    public void add(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        delegate.add(element);
    }

    public E get(int index) {
        return delegate.get(index);
    }

    public int size() {
        return delegate.size();
    }
}
