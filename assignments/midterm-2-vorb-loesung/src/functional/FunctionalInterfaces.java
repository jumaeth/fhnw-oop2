package functional;

import java.util.List;

public class FunctionalInterfaces {

    interface Iterator<T> {
        boolean hasNext();
        T next();
    }

    @FunctionalInterface
    interface Iterable<T> {
        Iterator<T> iterator();
    }

    @FunctionalInterface
    interface InfiniteIterator<T> extends Iterator<T> {
        default boolean hasNext() {
            return true;
        }
    }

    @FunctionalInterface
    interface SuperInfiniteIterator<T> extends InfiniteIterator<T> {
        default List<T> nextTwo() {
            return List.of(next(), next());
        }
    }

    abstract class EndlessIterator<T> implements Iterator<T> {
        public boolean hasNext() {
            return true;
        }
    }

    interface Serializable {
    }

    @FunctionalInterface
    interface SeriousSerializable extends Serializable {
        void serialize();
    }

    interface EarnestSerializable extends SeriousSerializable {
        void serializeForReal();
    }
}
