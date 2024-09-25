import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

public class TryCatch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("File to copy? ");
        String srcRaw = scanner.nextLine();
        System.out.print("Destination directory? ");
        String dirRaw = scanner.nextLine();

        Path src = Path.of(srcRaw).toAbsolutePath().normalize();
        Path dir = Path.of(dirRaw).toAbsolutePath().normalize();
        Path dst = dir.resolve(src.getFileName());

        System.out.println("Copying " + src + " to " + dst + "...");
        try {
            Files.copy(src, dst);
            System.out.println("Done.");
        } catch (NoSuchFileException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Could not copy file due to " + e.getClass().getSimpleName());
        }

        // or:
        // System.out.println("Copying " + src + " to " + dst + "...");
        // try {
        //     Files.copy(src, dst);
        // } catch (NoSuchFileException e) {
        //     System.err.println("File not found: " + e.getMessage());
        //     return;
        // } catch (IOException e) {
        //     System.err.println("Could not copy file due to " + e.getClass().getSimpleName());
        //     return;
        // }
        // System.out.println("Done.");

        // or:
        // System.out.println("Copying " + src + " to " + dst + "...");
        // try {
        //     Files.copy(src, dst);
        //     System.out.println("Done.");
        // } catch (IOException e) {
        //     if (e instanceof FileNotFoundException) {
        //         System.err.println("File not found: " + e.getMessage());
        //     } else {
        //         System.err.println("Could not copy file due to " + e.getClass().getSimpleName());
        //     }
        // }
    }
}
