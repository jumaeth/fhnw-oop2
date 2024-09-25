package streams;

import java.util.List;
import java.util.stream.Stream;

public class FlatMapExamples {

    public static void main(String[] args) {
        List<String> results = List.of("3:0", "2:1", "0:1");

        List<String> scores = results.stream()
                .flatMap(s -> Stream.of(s.split(":")))
                .toList();

        System.out.println("Scores: " + scores);

        List<Integer> numbers = List.of(1, 2, 3);

        List<Integer> multiples = numbers.stream()
                .flatMap(n -> Stream.of(n, n * 2, n * 3))
                .toList();

        System.out.println("Multiples: " + multiples);
    }
}
