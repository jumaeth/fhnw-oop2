package generics;

import java.util.Scanner;

public class TypeParamsAndArgs {

    public static class Optional<T> {
        private final T value;

        public Optional(T value) {
            this.value = value;
        }

        public T get() {
            if (value == null) {
                throw new IllegalStateException("no value present");
            }
            return value;
        }

        public boolean isPresent() {
            return value != null;
        }
    }

    public static void main(String[] args) {
//        Optional o = new Optional("One");
        Optional<String> s = new Optional<>("Two");
//        Optional<int> i = new Optional<>(3);
        Optional<Character> c = new Optional<>('4');
        Optional<Scanner> scan = new Optional<>(new Scanner("Five"));
//        Optional<T> t = new Optional<>(new T());
//        Optional<E> e = new Optional<>(null);
//        Optional<String, String> ss = new Optional<>("Six");
    }
}
