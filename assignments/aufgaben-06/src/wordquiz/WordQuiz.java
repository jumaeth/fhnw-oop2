package wordquiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordQuiz {

    private static final ArrayList<String> WORDS = new ArrayList<>(List.of(
            "OBJEKT", "PROGRAMM", "JAVA", "NORDWEST", "HOCHSCHULE", "WINDISCH",
            "SEMESTER", "LAMBDA", "METHODE", "OPERATOR", "CODEZEILE", "INFORMATIK",
            "GURKE", "FUCHS", "VERREISEN", "MASCHINE", "TRAKTOR", "PANZER"));

    public static void main(String[] args) {
        new WordQuiz().start();
    }

    private final Random random = new Random();
    private final String originalWord;
    private String currentWord;

    public WordQuiz() {
        originalWord = WORDS.get(random.nextInt(WORDS.size()));
        currentWord = scramble(originalWord);
    }

    private String scramble(String word) {
        String rest = word;
        String scrambled = "";
        while (!rest.isEmpty()) {
            int i = random.nextInt(rest.length());
            scrambled += rest.charAt(i);
            rest = rest.substring(0, i) + rest.substring(i + 1);
        }
        return scrambled;
    }

    private void start() {
        // TODO
    }

    private void swap(int first, int second) {
        currentWord = currentWord.substring(0, first)
                      + currentWord.charAt(second)
                      + currentWord.substring(first + 1, second)
                      + currentWord.charAt(first)
                      + currentWord.substring(second + 1);
    }
}
