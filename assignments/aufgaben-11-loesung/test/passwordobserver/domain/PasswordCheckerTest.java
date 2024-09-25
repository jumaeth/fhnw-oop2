package passwordobserver.domain;

import org.junit.jupiter.api.Test;
import passwordobserver.io.PasswordIO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordCheckerTest {

    PasswordChecker checker = new PasswordChecker(new PasswordIO());

    @Test
    void setStrengthListener() {
        var strength = new double[1];
        checker.setStrengthListener(s -> strength[0] = s);

        checker.setPassword("1");
        // check that listener has been called
        assertEquals(Math.log(10) / Math.log(2), strength[0], 0.001);

        checker.setPassword("1234");
        assertEquals(Math.log(10_000) / Math.log(2), strength[0], 0.001);
    }
}
