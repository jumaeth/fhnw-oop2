package webtraffic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class TrafficMonitor {

    private static final int N = 3;
    private static final int INTERVAL = 5000; // ms

    public static void main(String[] args) throws IOException, InterruptedException {
        new TrafficMonitor().run();
    }

    private final LogReader reader = new LogReader(Path.of("log.tsv"));
    private final Set<String> paths = new HashSet<>();
    private final Counter<String> titleCounter = new Counter<>();
    private final Set<IPv4Address> addresses = new HashSet<>();
    private final Counter<UserAgent> userAgentCounter = new Counter<>();

    private void run() throws IOException, InterruptedException {
        while (true) {
            updateStats();
            printStats();
            Thread.sleep(INTERVAL);
        }
    }

    private void updateStats() throws IOException {
        for (var entry : reader.readNewEntries()) {
            paths.add(entry.getPath());
            titleCounter.add(entry.getPageTitle());
            addresses.add(entry.getClientAddress());
            userAgentCounter.add(entry.getUserAgent());
        }
    }

    private void printStats() {
        System.out.println("Number of distinct paths: " + paths.size() + "\n");

        var topTitles = titleCounter.topElements(N);
        System.out.println("Most seen page titles" + ":");
        for (var title : topTitles) {
            System.out.println("  (" + titleCounter.countFor(title) + ") " + title);
        }
        System.out.println();

        System.out.println("Number of distinct IPs: " + addresses.size() + "\n");

        var topUserAgents = userAgentCounter.topElements(N);
        System.out.println("Most seen user agents" + ":");
        for (var userAgent : topUserAgents) {
            var count = userAgentCounter.countFor(userAgent);
            var formatted = userAgent.company == null
                    ? userAgent.name
                    : userAgent.name + " (" + userAgent.company + ")";
            System.out.println("  (" + count + ") " + formatted);
        }
        System.out.println();
    }
}
