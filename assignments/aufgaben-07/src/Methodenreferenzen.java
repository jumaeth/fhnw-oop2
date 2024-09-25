import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Methodenreferenzen {

    public static void main(String[] args) {
        var words = new ArrayList<>(List.of("Hello", " ", "World", "!", "", "Java", "OOP"));
        words.removeIf(String::isBlank);

        words.replaceAll(String::toLowerCase);

        var blacklist = possiblyNull();
        blacklist = Objects.requireNonNullElseGet(blacklist, ArrayList::new);

        blacklist.forEach(words::remove);

        words.forEach(System.out::println);
    }

    private static ArrayList<String> possiblyNull() {
        // this code makes no sense, but suppose it does...
        if (Math.random() < 1.0) {
            return new ArrayList<>(List.of("java", "oop"));
        } else {
            return null;
        }
    }
}
