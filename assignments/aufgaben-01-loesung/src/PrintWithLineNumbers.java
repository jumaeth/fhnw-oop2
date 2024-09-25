import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class PrintWithLineNumbers {
    public static void main(String[] args) {
        Path srcFile = Path.of("src/PrintWithLineNumbers.java");
        try (Scanner scanner = new Scanner(srcFile)) {
            int lineNum = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.printf("%2d: %s\n", lineNum, line);
                lineNum++;
            }
        } catch (IOException e) {
            System.err.println("Could not open file " + srcFile.toAbsolutePath());
        }
    }
}
