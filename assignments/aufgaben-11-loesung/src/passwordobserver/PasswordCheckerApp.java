package passwordobserver;

import passwordobserver.domain.PasswordChecker;
import passwordobserver.io.PasswordIO;
import passwordobserver.ui.PasswordCheckerUI;

public class PasswordCheckerApp {

    public static void main(String[] args) {
        var io = new PasswordIO();                  // "data" layer
        var checker = new PasswordChecker(io);      // domain layer
        var ui = new PasswordCheckerUI(checker);    // presentation layer
        ui.start();
    }
}
