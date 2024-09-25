package webtraffic;

public enum UserAgent {
    CHROME("Chrome", "Google"),
    EDGE("Edge", "Microsoft"),
    SAFARI("Safari", "Apple"),
    FIREFOX("Firefox", "Mozilla"),
    OTHER("Other browser", null);

    public final String name;
    public final String company;

    UserAgent(String name, String company) {
        this.name = name;
        this.company = company;
    }
}
