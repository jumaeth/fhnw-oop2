package password.domain;

import org.junit.jupiter.api.Test;
import password.domain.PasswordChecker;
import password.io.PasswordIO;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    PasswordChecker checker = new PasswordChecker(new PasswordIO());

    @Test
    void constructor() {
        assertEquals("", checker.getPassword());
        assertEquals(80, checker.getMinStrength());
    }

    @Test
    void getStrength() {
        assertEquals(0, checker.getStrength());

        checker.setPassword("1");
        assertEquals(Math.log(10) / Math.log(2), checker.getStrength(), 0.001);

        checker.setPassword("1234");
        assertEquals(Math.log(10_000) / Math.log(2), checker.getStrength(), 0.001);
    }

    @Test
    void getStrengthRatioIsStrongEnough() {
        assertEquals(0, checker.getStrengthRatio());
        assertFalse(checker.isStrongEnough());

        checker.setPassword("1234");
        assertTrue(checker.getStrengthRatio() > 0);
        assertFalse(checker.isStrongEnough());

        checker.setPassword("superStrongPassw0rd!!!11elf");
        assertTrue(checker.getMinStrength() >= 80);
        assertEquals(1, checker.getStrengthRatio());
        assertTrue(checker.isStrongEnough());
    }
}
