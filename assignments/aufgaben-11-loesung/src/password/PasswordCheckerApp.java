package password;

import password.domain.PasswordChecker;
import password.io.PasswordIO;
import password.ui.PasswordCheckerUI;

public class PasswordCheckerApp {

    public static void main(String[] args) {
        var io = new PasswordIO();                  // "data" layer
        var checker = new PasswordChecker(io);      // domain layer
        var ui = new PasswordCheckerUI(checker);    // presentation layer
        ui.start();
    }
}
