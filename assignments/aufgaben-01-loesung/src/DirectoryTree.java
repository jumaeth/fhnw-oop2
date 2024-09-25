import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryTree {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java DirectoryTree <dir>");
            return;
        }

        var dir = Path.of(args[0]);
        try {
            printDirectoryTree(dir, 0);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void printDirectoryTree(Path dir, int indentation) throws IOException {
        try (var contents = Files.newDirectoryStream(dir)) {
            for (var entry : contents) {
                System.out.println(" ".repeat(indentation) + entry.getFileName());
                if (Files.isDirectory(entry)) {
                    printDirectoryTree(entry, indentation + 2);
                }
            }
        }
    }
}
