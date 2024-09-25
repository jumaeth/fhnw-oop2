package tv;

public class Season {
    private final TVShow show;
    private final int number;
    private final int episodes;
    private final Date releaseDate;

    Season(TVShow show, int number, int episodes, Date releaseDate) {
        this.show = show;
        this.number = number;
        this.episodes = episodes;
        this.releaseDate = releaseDate;
    }

    public TVShow show() {
        return show;
    }

    public int number() {
        return number;
    }

    public int episodes() {
        return episodes;
    }

    public Date releaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return show.title() + " S" + number;
    }
}
