package webtraffic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogReader {

    private final Path logFile;

    private int previousLineCount = 0;

    public LogReader(Path logFile) {
        this.logFile = logFile;
    }

    public List<LogEntry> readNewEntries() throws IOException {
        var result = new ArrayList<LogEntry>();
        int lineCount = 0;
        try (Scanner scanner = new Scanner(logFile, StandardCharsets.UTF_8)) {
            for (int i = 0; i < previousLineCount; i++) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                var parts  = line.split("\t");

                var path = parts[1];
                var pageTitle = parts[2];
                var ip = new IPv4Address(parts[3]);

                UserAgent userAgent;
                try {
                    userAgent = UserAgent.valueOf(parts[4]);
                } catch (IllegalArgumentException e) {
                    userAgent = UserAgent.OTHER;
                }

                var entry = new LogEntry(path, pageTitle, ip, userAgent);
                result.add(entry);
                lineCount++;
            }
        }
        System.out.println(lineCount);
        previousLineCount = lineCount;

        // TODO:
        //  a) read lines from 'logFile', print count
        //  b) keep track of previously read lines and print new lines count
        //  c) parse each line as LogEntry object and add to 'result'
        return result;
    }
}
