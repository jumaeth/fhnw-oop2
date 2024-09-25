package streams;

import java.util.List;

import static java.util.stream.Collectors.toSet;

public class ReduceStreamsExamples {

    public static void main(String[] args) {
        var names = List.of("Alice", "Bob", "Charlie", "Dave", "Eve", "Fred", "Grace");

        System.out.println(names.stream()
                .filter(s -> s.length() <= 4)
                .limit(3)
                .toList());

        System.out.println(names.stream()
                .limit(3)
                .filter(s -> s.length() <= 4)
                .toList());

        System.out.println(names.stream()
                .takeWhile(s -> s.length() < 7)
                .toList());

        System.out.println(names.stream()
                .map(s -> s.charAt(0))
                .dropWhile(c -> c < 'D')
                .toList());

        System.out.println(names.stream()
                .map(String::length)
                .distinct()
                .toList());

        System.out.println(names.stream()
                .map(String::length)
                .collect(toSet()));
    }
}
