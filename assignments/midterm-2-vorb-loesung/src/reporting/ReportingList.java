package reporting;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class ReportingList<E> {

    private final ArrayList<E> delegate = new ArrayList<>();

    private BiConsumer<Integer, E> onAdded;
    private Runnable onCleared;

    public boolean add(E e) {
        delegate.add(e);
        if (onAdded != null) {
            onAdded.accept(delegate.size() - 1, e);
        }
        return true;
    }

    public void clear() {
        delegate.clear();
        if (onCleared != null) {
            onCleared.run();
        }
    }

    public E get(int index) {
        return delegate.get(index);
    }

    public int size() {
        return delegate.size();
    }

    public void setOnAdded(BiConsumer<Integer, E> onAdded) {
        this.onAdded = onAdded;
    }

    public void setOnCleared(Runnable onCleared) {
        this.onCleared = onCleared;
    }
}
