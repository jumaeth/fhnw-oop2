package lambdas;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.*;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

public class LambdaTasks {

    public static void removeLongStrings(Set<String> set, int maxLength) {
        set.removeIf(s -> s.length() > maxLength);
    }

    public static void printList(List<?> list, PrintStream output) {
        list.forEach(output::println);

        // Oder:
        // list.forEach(e -> output.println(e));
    }

    public static void replaceWeekends(List<LocalDate> dates) {
        dates.replaceAll(date -> switch (date.getDayOfWeek()) {
            case SATURDAY -> date.plusDays(2);
            case SUNDAY -> date.plusDays(1);
            default -> date;
        });

        // Oder:
        dates.replaceAll(date -> {
            if (date.getDayOfWeek() == SATURDAY) {
                return date.plusDays(2);
            } else if (date.getDayOfWeek() == SUNDAY) {
                return date.plusDays(1);
            } else {
                return date;
            }
        });
    }

    public static void incrementAll(Map<String, Integer> map) {
        map.replaceAll((key, value) -> value + 1);
    }

    public static Map<String, Integer> frequencies(List<String> words) {
        Map<String, Integer> freqs = new HashMap<>();
        for (String word : words) {
            freqs.merge(word, 1, (i, j) -> i + j);
            // Oder: freqs.merge(word, 1, Integer::sum);
        }
        return freqs;
    }

    public static void sortByLength(List<String> list) {
        list.sort(Comparator.comparingInt(String::length));

        // Oder:
        list.sort(Comparator.comparingInt(s -> s.length()));

        // Ganz schön, mit "import static java.util.Comparator.comparingInt;":
        list.sort(comparingInt(String::length));
    }

    public static void sortByLastThenFirstName(List<Person> list) {
        list.sort(comparing(Person::getLastName)
                .thenComparing(Person::getFirstName));
    }
}
