package webtraffic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class LogReader {

    private final Path logFile;
    private long prevLineCount = 0;

    public LogReader(Path logFile) {
        this.logFile = logFile;
    }

    public List<LogEntry> readNewEntries() throws IOException {
        try (var in = Files.newInputStream(logFile)) {
            var reader = new BufferedReader(new InputStreamReader(in, ISO_8859_1)); // or change TrafficSimulator

            // first, skip lines read previously
            int lineCount = 0;
            while (lineCount < prevLineCount) {
                reader.readLine(); // discard
                lineCount++;
            }

            var result = new ArrayList<LogEntry>();
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(parseLine(line));
                lineCount++;
            }
            prevLineCount = lineCount;
            return result;
        }
    }

    private LogEntry parseLine(String line) {
        var parts = line.split("\t");
        var time = Instant.parse(parts[0]);
        var path = parts[1];
        var pageTitle = parts[2];
        var sessionID = Long.parseLong(parts[3]);
        var address = new IPv4Address(parts[4]);
        var userAgent = UserAgent.OTHER;
        try {
            userAgent = UserAgent.valueOf(parts[5]);
        } catch (IllegalArgumentException e) { /* ignore */ }
        return new LogEntry(time, path, pageTitle, sessionID, address, userAgent);
    }
}
