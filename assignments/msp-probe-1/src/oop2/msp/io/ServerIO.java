package oop2.msp.io;

import oop2.msp.model.Server;

import java.io.*;
import java.util.List;

public class ServerIO {

    public static List<Server> readCsv(InputStream in) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(in))) {
            reader.lines();
        }
    }

    public static void writeCsv(List<Server> servers, OutputStream out) throws IOException {
        try (var printer = new PrintStream(out)) {

        }
    }
}
