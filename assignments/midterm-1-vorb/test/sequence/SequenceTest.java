package sequence;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SequenceTest {

    @Test
    void testMinLength() {
        // given
        Sequence s0 = new Sequence(List.of());
        Sequence s1 = new Sequence(List.of("Montag"));
        Sequence s2 = new Sequence(List.of("Montag", "Dienstag"));

        // when
        int step0 = s0.stepSize();
        int step1 = s1.stepSize();
        int step2 = s2.stepSize();

        // then
        assertEquals(-1, step0);
        assertEquals(-1, step1);
        assertNotEquals(-1, step2);
    }

    @Test
    void testLength2() {
        // given
        Sequence s1 = new Sequence(List.of("Dienstag", "Samstag"));
        Sequence s2 = new Sequence(List.of("Montag", "Mittwoch"));

        // when
        int step1 = s1.stepSize();
        int step2 = s2.stepSize();

        // then
        assertEquals(4, step1);
        assertEquals(2, step2);
    }

    @Test
    void testLength2WithWrap() {
        // given
        Sequence s1 = new Sequence(List.of("Freitag", "Montag"));
        Sequence s2 = new Sequence(List.of("Samstag", "Mittwoch"));
        Sequence s3 = new Sequence(List.of("Sonntag", "Montag"));

        // when
        int step1 = s1.stepSize();
        int step2 = s2.stepSize();
        int step3 = s3.stepSize();

        // then
        assertEquals(3, step1);
        assertEquals(4, step2);
        assertEquals(1, step3);
    }

    @Test
    void testLongerStep1() {
        // given
        Sequence s1 = new Sequence(List.of("Montag", "Dienstag", "Mittwoch", "Donnerstag"));
        Sequence s2 = new Sequence(List.of("Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"));
        Sequence s3 = new Sequence(List.of("Mittwoch", "Donnerstag", "Freitag"));
        Sequence s4 = new Sequence(List.of("Mittwoch", "Freitag", "Sonntag"));

        // when
        int step1 = s1.stepSize();
        int step2 = s2.stepSize();
        int step3 = s3.stepSize();
        int step4 = s4.stepSize();

        // then
        assertEquals(1, step1);
        assertEquals(1, step2);
        assertEquals(1, step3);
        assertNotEquals(1, step4);
    }

    @Test
    void testLongerDifferentSteps() {
        // given
        Sequence s1 = new Sequence(List.of("Montag", "Mittwoch", "Freitag"));
        Sequence s2 = new Sequence(List.of("Dienstag", "Donnerstag", "Samstag"));
        Sequence s3 = new Sequence(List.of("Montag", "Donnerstag", "Sonntag"));
        Sequence s4 = new Sequence(List.of("Mittwoch", "Sonntag"));

        // when
        int step1 = s1.stepSize();
        int step2 = s2.stepSize();
        int step3 = s3.stepSize();
        int step4 = s4.stepSize();

        // then
        assertEquals(2, step1);
        assertEquals(2, step2);
        assertEquals(3, step3);
        assertEquals(4, step4);
    }

    @Test
    void testLongerWithWrap() {
        // given
        Sequence s1 = new Sequence(List.of("Freitag", "Montag", "Donnerstag"));
        Sequence s2 = new Sequence(List.of("Samstag", "Montag", "Mittwoch", "Freitag"));
        Sequence s3 = new Sequence(List.of("Sonntag", "Freitag", "Mittwoch"));

        // when
        int step1 = s1.stepSize();
        int step2 = s2.stepSize();
        int step3 = s3.stepSize();

        // then
        assertEquals(3, step1);
        assertEquals(2, step2);
        assertEquals(5, step3);
    }

    @Test
    void testStep0() {
        // given
        Sequence s1 = new Sequence(List.of("Freitag", "Freitag", "Freitag"));
        Sequence s2 = new Sequence(List.of("Samstag", "Samstag", "Samstag", "Samstag"));
        Sequence s3 = new Sequence(List.of("Montag", "Montag"));
        Sequence s4 = new Sequence(List.of("Montag", "Dienstag", "Mittwoch"));

        // when
        int step1 = s1.stepSize();
        int step2 = s2.stepSize();
        int step3 = s3.stepSize();
        int step4 = s4.stepSize();

        // then
        assertEquals(0, step1);
        assertEquals(0, step2);
        assertEquals(0, step3);
        assertNotEquals(0, step4);
    }

    @Test
    void testNonConstantSequence() {
        // given
        Sequence s1 = new Sequence(List.of("Montag", "Mittwoch", "Montag"));
        Sequence s2 = new Sequence(List.of("Montag", "Dienstag", "Mittwoch", "Freitag"));
        Sequence s3 = new Sequence(List.of("Sonntag", "Montag", "Sonntag"));
        Sequence s4 = new Sequence(List.of("Montag", "Dienstag", "Mittwoch"));

        // when
        int step1 = s1.stepSize();
        int step2 = s2.stepSize();
        int step3 = s3.stepSize();
        int step4 = s4.stepSize();

        // then
        assertEquals(-1, step1);
        assertEquals(-1, step2);
        assertEquals(-1, step3);
        assertNotEquals(-1, step4);
    }
}
