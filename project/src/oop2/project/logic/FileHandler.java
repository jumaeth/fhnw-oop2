package oop2.project.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {

    public static void saveFile(String path, String config) throws IOException {
        Path configPath = Path.of(path);
        Files.writeString(configPath, config);
    }

    public static String loadFile(String path) throws IOException {
        Path configPath = Path.of(path);
        return Files.readString(configPath);
    }
}
