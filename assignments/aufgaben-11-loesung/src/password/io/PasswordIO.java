package password.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.Collections.emptyList;

public class PasswordIO {

    private static final Path PASSWORDS_FILE = Path.of("passwords.txt").toAbsolutePath();

    public void saveNewPassword(String password) throws IOException {
        List<?> existingPasswords = emptyList();
        if (Files.exists(PASSWORDS_FILE)) {
            existingPasswords = Files.readAllLines(PASSWORDS_FILE);
        }
        if (!existingPasswords.contains(password)) {
            Files.writeString(PASSWORDS_FILE, password + "\n", CREATE, APPEND);
        }
    }
}
