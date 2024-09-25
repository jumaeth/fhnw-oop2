package letter.filereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileReader {

    public List<String> readFile(Path file) {
        try {
            return Files.readAllLines(file);
        } catch (IOException e) {
            System.err.println("Error reading file: " + file);
        }
        return null;
    }

    public void readFolder(Path folder, Consumer<Path> pathConsumer) {
        try (Stream<Path> stream = Files.list(folder)) {
            stream.filter(file -> !Files.isDirectory(file)).forEach(pathConsumer);
        } catch (IOException e) {
            System.err.println("Error reading folder: " + folder);
        }
    }
}
