package webtraffic;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static webtraffic.UserAgent.*;

public class TrafficSimulator {

    private static final Path DEFAULT_LOG_FILE = Path.of("log.tsv");

    private static final List<String> PATH_SEGMENTS = List.of(
            "order", "user", "details", "article", "category", "tag", "cart", "account",
            "create", "update", "delete", "manage", "edit", "new");

    private static final List<String> TITLE_PARTS = List.of(
            "Übersicht", "Benutzer", "Warenkorb", "Bestellung", "Konto", "Zahlungsmittel",
            "Merkliste", "Neu", "Bearbeiten", "Löschen");

    private static final List<Object> USER_AGENTS = List.of(
            CHROME, EDGE, SAFARI, FIREFOX, "SAMSUNG_INTERNET", "INTERNET_EXPLORER", "OPERA", "UC_BROWSER");

    public static void main(String[] args) throws Exception {
        var logFile = DEFAULT_LOG_FILE;
        if (args.length > 0) {
            logFile = Path.of(args[0]);
        }
        new TrafficSimulator(logFile).start();
    }

    private final Path logFile;
    private final Random random = new Random();

    private final List<String> paths = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();
    private final List<IPv4Address> addresses = new ArrayList<>();

    public TrafficSimulator(Path logFile) {
        this.logFile = logFile;
    }

    private void start() throws Exception {
        try (var writer = Files.newBufferedWriter(logFile, StandardCharsets.ISO_8859_1)) {
            while (true) {
                Thread.sleep(random.nextInt(100, 500));
                writer.write(randomLogEntry() + "\n");
                writer.flush();
            }
        }
    }

    private String randomLogEntry() {
        return Instant.now() + "\t"
                + randomPath() + "\t"
                + randomTitle() + "\t"
                + randomClientAddress() + "\t"
                + randomUserAgent();
    }

    private String randomPath() {
        if (random.nextDouble() < 0.15 || paths.size() < 3) {
            var path = generatePath();
            paths.add(path);
            return path;
        } else {
            return paths.get(random.nextInt(paths.size()));
        }
    }

    private String generatePath() {
        var segments = random.nextInt(0, 5);
        var path = "/";
        for (int i = 0; i < segments; i++) {
            if (random.nextDouble() < 0.8) {
                path += PATH_SEGMENTS.get(random.nextInt(PATH_SEGMENTS.size())) + "/";
            } else {
                path += random.nextInt(100) + "/";
            }
        }
        return path;
    }

    private String randomTitle() {
        if (random.nextDouble() < 0.1 || titles.size() < 2) {
            var title = generateTitle();
            titles.add(title);
            return title;
        } else {
            return titles.get(random.nextInt(titles.size()));
        }
    }

    private String generateTitle() {
        var title = TITLE_PARTS.get(random.nextInt(TITLE_PARTS.size()));
        if (random.nextDouble() < 0.6) {
            title += " · " + TITLE_PARTS.get(random.nextInt(TITLE_PARTS.size()));
        }
        return title;
    }

    private IPv4Address randomClientAddress() {
        if (random.nextDouble() < 0.25 && !addresses.isEmpty()) {
            addresses.remove(random.nextInt(addresses.size()));
        }
        if (random.nextDouble() < 0.25 || addresses.isEmpty()) {
            var bytes = new byte[4];
            random.nextBytes(bytes);
            var address = new IPv4Address(bytes);
            addresses.add(address);
            return address;
        } else {
            return addresses.get(random.nextInt(addresses.size()));
        }
    }

    private Object randomUserAgent() {
        int i = 0;
        while (random.nextDouble() < 0.6 && i < USER_AGENTS.size() - 1) {
            i++;
        }
        return USER_AGENTS.get(i);
    }
}
