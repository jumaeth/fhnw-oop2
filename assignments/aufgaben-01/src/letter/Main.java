package letter;

import gui.Window;
import letter.filereader.FileReader;
import letter.gui.Drawer;
import letter.gui.LetterFrequencyGui;
import letter.logic.LetterFrequency;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        LetterFrequencyGui gui = new LetterFrequencyGui();
        Window window = gui.createWindow();

        readAndDrawFolder(args, window);
        window.waitUntilClosed();

        // printLetters(letterFrequency); Uncomment to print the letter frequency
    }

    private static void readAndDrawFolder(String[] args, Window window) {
        FileReader fileReader = new FileReader();
        Drawer drawer = new Drawer(window);
        LetterFrequency letterFrequency = new LetterFrequency(window, drawer);

        if (args.length > 0) {
            Path path = Path.of(args[0]);
            if (Files.isDirectory(path)) {
                System.out.println("Absolute path: " + path);
                fileReader.readFolder(Path.of(args[0]), (file) -> letterFrequency.countLetters(fileReader.readFile(file), file));
            } else {
                System.out.println("Relative path: " + path);
                fileReader.readFolder(Path.of(System.getProperty("user.dir"), args[0]), (file) -> letterFrequency.countLetters(fileReader.readFile(file), file));
            }
        } else {
            System.err.println("No path provided");
            System.exit(0);
        }
    }

    private static void printLetters(LetterFrequency letterFrequency) {
        int[] letters = letterFrequency.getLetterAmount();
        char alphabet = 'a';
        for (int letter : letters) {
            System.out.println(alphabet + ": " + letter);
            alphabet++;
        }
    }
}
