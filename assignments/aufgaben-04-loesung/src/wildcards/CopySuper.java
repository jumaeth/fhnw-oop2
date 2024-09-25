package wildcards;

import java.util.ArrayList;
import java.util.List;

public class CopySuper {

    public static void main(String[] args) {
        List<Double> doubles = new ArrayList<>(List.of(2.5, 7.1, 5.4));
        List<Number> numbers = new ArrayList<>(List.of(-10, 1, 9.7, 42));
        copy(doubles, numbers);
        System.out.println(numbers);
    }

    static <T> void copy(List<T> src, List<? super T> dest) {
        dest.addAll(src);
    }
}
