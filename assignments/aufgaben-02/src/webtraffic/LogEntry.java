package webtraffic;

public class LogEntry {

    private final String path;
    private final String pageTitle;
    private final IPv4Address clientAddress;
    private final UserAgent userAgent;

    public LogEntry(String path, String pageTitle, IPv4Address clientAddress,
                    UserAgent userAgent) {
        this.path = path;
        this.pageTitle = pageTitle;
        this.clientAddress = clientAddress;
        this.userAgent = userAgent;
    }

    public String getPath() {
        return path;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public IPv4Address getClientAddress() {
        return clientAddress;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}
