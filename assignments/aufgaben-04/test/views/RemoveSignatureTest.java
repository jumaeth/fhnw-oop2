package views;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveSignatureTest {

    @Test
    public void testSig() {
        List<String> email = new ArrayList<>(List.of(
                "Hi,",
                "How are you?",
                "Best,",
                "Tim",
                "-- ",
                "Tim Johnson",
                "Consultant"));
        List<String> expected = List.of(
                "Hi,",
                "How are you?",
                "Best,",
                "Tim");
        ViewTasks.removeSignature(email);
        assertEquals(expected, email);
    }

    @Test
    public void testEmptyLines() {
        List<String> email = new ArrayList<>(List.of(
                "Hi,",
                "How are you?",
                "",
                "Best,",
                "Tim",
                "-- ",
                "Tim Johnson",
                "Consultant",
                "",
                "The Company"));
        List<String> expected = List.of(
                "Hi,",
                "How are you?",
                "",
                "Best,",
                "Tim");
        ViewTasks.removeSignature(email);
        assertEquals(expected, email);
    }

    @Test
    public void testIncorrectSigSeparatorFirst() {
        List<String> email = new ArrayList<>(List.of(
                "Hi,",
                "How are you?",
                "Best,",
                "Tim",
                "--",
                "Tim Johnson",
                "-- ",
                "Consultant"));
        List<String> expected = List.of(
                "Hi,",
                "How are you?",
                "Best,",
                "Tim",
                "--",
                "Tim Johnson");
        ViewTasks.removeSignature(email);
        assertEquals(expected, email);
    }

    @Test
    public void testNoSig() {
        testSig(); // test basic functionality, so empty implementation doesn't pass

        List<String> email = new ArrayList<>(List.of(
                "Hi,",
                "How are you?",
                "Best,",
                "Tim"));
        List<String> expected = List.of(
                "Hi,",
                "How are you?",
                "Best,",
                "Tim");
        ViewTasks.removeSignature(email);
        assertEquals(expected, email);
    }
}
