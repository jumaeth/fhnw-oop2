package webtraffic;

import java.time.Instant;

public class LogEntry {

    private final Instant time;
    private final String path;
    private final String pageTitle;
    private final long sessionID;
    private final IPv4Address clientAddress;
    private final UserAgent userAgent;

    public LogEntry(Instant time, String path, String pageTitle,
                    long sessionID, IPv4Address clientAddress,
                    UserAgent userAgent) {
        this.time = time;
        this.path = path;
        this.pageTitle = pageTitle;
        this.sessionID = sessionID;
        this.clientAddress = clientAddress;
        this.userAgent = userAgent;
    }

    public Instant getTime() {
        return time;
    }

    public String getPath() {
        return path;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public long getSessionID() {
        return sessionID;
    }

    public IPv4Address getClientAddress() {
        return clientAddress;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}
