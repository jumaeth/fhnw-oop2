import com.google.common.jimfs.Jimfs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;

import static com.google.common.jimfs.Configuration.unix;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectoryTreeTest {

    FileSystem fs = Jimfs.newFileSystem(unix()); // virtuelles Dateisystem

    ByteArrayOutputStream out = new ByteArrayOutputStream(); // sammelt Ausgabe

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(out));
    }

    @Test
    void singleLevel() throws IOException {
        var dir = fs.getPath("studium");
        Files.createDirectory(dir);
        Files.createFile(dir.resolve("eipr-notes.txt"));
        Files.createFile(dir.resolve("oopI1-notes.txt"));
        Files.createFile(dir.resolve("oopI2-spick.pdf"));
        Files.createFile(dir.resolve("mgli-meme.jpg"));
        Files.createFile(dir.resolve("sysad-cheatsheet.sh"));

        DirectoryTree.printDirectoryTree(dir, 0);
        assertEquals("""
                eipr-notes.txt
                mgli-meme.jpg
                oopI1-notes.txt
                oopI2-spick.pdf
                sysad-cheatsheet.sh
                """, out.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    void singleLevelIndentation() throws IOException {
        var dir = fs.getPath("studium");
        Files.createDirectory(dir);
        Files.createFile(dir.resolve("eipr-notes.txt"));
        Files.createFile(dir.resolve("oopI1-notes.txt"));
        Files.createFile(dir.resolve("oopI2-spick.pdf"));
        Files.createFile(dir.resolve("mgli-meme.jpg"));
        Files.createFile(dir.resolve("sysad-cheatsheet.sh"));

        DirectoryTree.printDirectoryTree(dir, 2); // <-- !
        assertEquals("""
                  eipr-notes.txt
                  mgli-meme.jpg
                  oopI1-notes.txt
                  oopI2-spick.pdf
                  sysad-cheatsheet.sh
                """, out.toString().replaceAll("\r\n", "\n"));

        out.reset();
        DirectoryTree.printDirectoryTree(dir, 4); // <-- !
        assertEquals("""
                    eipr-notes.txt
                    mgli-meme.jpg
                    oopI1-notes.txt
                    oopI2-spick.pdf
                    sysad-cheatsheet.sh
                """, out.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    void singleLevelWithEmptyDir() throws IOException {
        var dir = fs.getPath("studium");
        Files.createDirectory(dir);
        Files.createFile(dir.resolve("eipr-notes.txt"));
        Files.createDirectory(dir.resolve("oopI1"));
        Files.createDirectory(dir.resolve("oopI2"));
        Files.createFile(dir.resolve("mgli-meme.jpg"));
        Files.createFile(dir.resolve("sysad-cheatsheet.sh"));

        DirectoryTree.printDirectoryTree(dir, 0);
        assertEquals("""
                eipr-notes.txt
                mgli-meme.jpg
                oopI1
                oopI2
                sysad-cheatsheet.sh
                """, out.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    void twoLevels() throws IOException {
        var dir = fs.getPath("studium");
        Files.createDirectory(dir);

        Files.createDirectory(dir.resolve("oopI1"));
        Files.createFile(dir.resolve("oopI1/HelloWorld.java"));
        Files.createFile(dir.resolve("oopI1/Notizen.txt"));
        Files.createFile(dir.resolve("oopI1/Zusammenfassung 1.pdf"));
        Files.createFile(dir.resolve("oopI1/Zusammenfassung 2.pdf"));
        Files.createFile(dir.resolve("oopI1/Zusammenfassung 3.pdf"));

        Files.createDirectory(dir.resolve("oopI2"));
        Files.createFile(dir.resolve("oopI2/DirectoryTree.java"));
        Files.createFile(dir.resolve("oopI2/DirectoryTreeTest.java"));
        Files.createFile(dir.resolve("oopI2/Drehbuch.pdf"));

        DirectoryTree.printDirectoryTree(dir, 0);
        assertEquals("""
                oopI1
                  HelloWorld.java
                  Notizen.txt
                  Zusammenfassung 1.pdf
                  Zusammenfassung 2.pdf
                  Zusammenfassung 3.pdf
                oopI2
                  DirectoryTree.java
                  DirectoryTreeTest.java
                  Drehbuch.pdf
                """, out.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    void deep() throws IOException {
        var dir = fs.getPath("studium");
        Files.createDirectory(dir);

        Files.createDirectory(dir.resolve("oopI1"));
        Files.createFile(dir.resolve("oopI1/Drehbuch.pdf"));
        Files.createFile(dir.resolve("oopI1/Notizen.txt"));
        Files.createDirectory(dir.resolve("oopI1/W01"));
        Files.createFile(dir.resolve("oopI1/W01/aufgaben.adoc"));
        Files.createFile(dir.resolve("oopI1/W01/Daten und einfache Schleifen.pdf"));
        Files.createFile(dir.resolve("oopI1/W01/Einführung.pdf"));
        Files.createFile(dir.resolve("oopI1/W01/README.adoc"));
        Files.createDirectory(dir.resolve("oopI1/W02"));
        Files.createFile(dir.resolve("oopI1/W02/Begrenzte Schleifen.pdf"));
        Files.createFile(dir.resolve("oopI1/W02/programmieraufgaben.adoc"));
        Files.createDirectory(dir.resolve("oopI1/W02/res"));
        Files.createFile(dir.resolve("oopI1/W02/README.adoc"));
        Files.createFile(dir.resolve("oopI1/W02/res/diagramm.png"));
        Files.createFile(dir.resolve("oopI1/W02/uebungsaufgaben.adoc"));

        Files.createDirectory(dir.resolve("oopI2"));
        Files.createDirectory(dir.resolve("oopI2/W01"));
        Files.createDirectory(dir.resolve("oopI2/W01/code"));
        Files.createDirectory(dir.resolve("oopI2/W01/code/src"));
        Files.createFile(dir.resolve("oopI2/W01/code/src/DirectoryTree.java"));
        Files.createDirectory(dir.resolve("oopI2/W01/code/test"));
        Files.createFile(dir.resolve("oopI2/W01/code/test/DirectoryTreeTest.java"));

        DirectoryTree.printDirectoryTree(dir, 0);
        assertEquals("""
                oopI1
                  Drehbuch.pdf
                  Notizen.txt
                  W01
                    Daten und einfache Schleifen.pdf
                    Einführung.pdf
                    README.adoc
                    aufgaben.adoc
                  W02
                    Begrenzte Schleifen.pdf
                    README.adoc
                    programmieraufgaben.adoc
                    res
                      diagramm.png
                    uebungsaufgaben.adoc
                oopI2
                  W01
                    code
                      src
                        DirectoryTree.java
                      test
                        DirectoryTreeTest.java
                """, out.toString().replaceAll("\r\n", "\n"));
    }
}
