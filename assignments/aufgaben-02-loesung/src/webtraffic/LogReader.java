package webtraffic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        // solution with BufferedReader, could also use 'Files.readAllLines()'
        // or 'new Scanner(logFile, ISO_8859_1)'

        try (var reader = Files.newBufferedReader(logFile, ISO_8859_1)) {
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
        var path = parts[1];
        var pageTitle = parts[2];
        var address = new IPv4Address(parts[3]);
        var userAgent = UserAgent.OTHER;
        try {
            userAgent = UserAgent.valueOf(parts[4]);
        } catch (IllegalArgumentException e) { /* ignore */ }
        return new LogEntry(path, pageTitle, address, userAgent);
    }
}
