package oop2.msp.io;

import oop2.msp.model.IPAddress;
import oop2.msp.model.Server;

import java.io.*;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

public class ServerIO {

    public static final String HEADER = "Name;IP Address;CPU count;Memory;Storage";

    public static List<Server> readCsv(InputStream in) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(in))) {
            return reader.lines()
                    .skip(1) // header
                    .map(ServerIO::parseLine)
                    .collect(toList());
        }
    }

    private static Server parseLine(String line) {
        var parts = line.split(";");
        var name = parts[0];
        var address = new IPAddress(parts[1]);
        var cpuCount = parseInt(parts[2]);
        var memory = parseGB(parts[3]);
        var storage = parseGB(parts[4]);
        return new Server(name, address, cpuCount, memory, storage);
    }

    private static int parseGB(String s) {
        var parts = s.split(" ");
        var number = parseInt(parts[0]);
        return switch (parts[1]) {
            case "MB" -> number / 1000;
            case "GB" -> number;
            case "TB" -> number * 1000;
            default -> throw new IllegalArgumentException();
        };
    }

    public static void writeCsv(List<Server> servers, OutputStream out) throws IOException {
        try (var printer = new PrintStream(out)) {
            printer.println(HEADER);
            for (var server : servers) {
                printer.println(
                        server.getName() + ";" +
                        server.getAddress() + ";" +
                        server.getCpuCount() + ";" +
                        server.getMemoryGB() + " GB;" +
                        server.getStorageGB() + " GB");
            }
        }
    }
}
