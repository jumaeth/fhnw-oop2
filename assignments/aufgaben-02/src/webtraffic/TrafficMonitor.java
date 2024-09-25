package webtraffic;

import java.io.IOException;
import java.nio.file.Path;

public class TrafficMonitor {

    private static final int N = 3;
    private static final int INTERVAL = 5000; // ms

    public static void main(String[] args) throws IOException, InterruptedException {
        new TrafficMonitor().run();
    }

    private final LogReader reader = new LogReader(Path.of("log.tsv"));
    private final Counter<String> pathCounter = new Counter<>();

    private final Counter<IPv4Address> ipCounter = new Counter<>();
    private final Counter<String> titleCounter = new Counter<>();

    private void run() throws IOException, InterruptedException {
        while (true) {
            updateStats();
            printStats();
            Thread.sleep(INTERVAL);
        }
    }

    private void updateStats() throws IOException {
        for (var entry : reader.readNewEntries()) {
            pathCounter.add(entry.getPath());
            ipCounter.add(entry.getClientAddress());
            titleCounter.add(entry.getPageTitle());
        }
    }

    private void printStats() {
        var pathCount = pathCounter.allElements().size();
        System.out.println("Number of distinct paths: " + pathCount + "\n");

        var ipCount = ipCounter.allElements().size();
        System.out.println("Number of distinct ips: " + ipCount + "\n");

        var topTitles = titleCounter.topStrings(N);
        System.out.println("Most seen page titles:");
        for (var title : topTitles) {
            System.out.println("  (" + titleCounter.countFor(title) + ") " + title);
        }
        System.out.println();
    }
}
