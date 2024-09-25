package smartcampus;

/**
 * Fensterstoren
 */
public class Shades extends Actor {
    private int percentClosed = 0;

    public Shades(int id, String name) {
        super(id, name);
    }

    public int getPercentClosed() {
        return percentClosed;
    }

    public void setPercentClosed(int percentClosed) {
        if (percentClosed < 0 || percentClosed > 100) {
            throw new IllegalArgumentException();
        }
        this.percentClosed = percentClosed;
    }

    @Override
    public double powerConsumption() {
        return 0.5; // Verbrauch während Öffnen/Schliessen vernachlässigbar
    }
}
