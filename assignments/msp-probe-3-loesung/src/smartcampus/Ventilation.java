package smartcampus;

public class Ventilation extends Actor {
    private int level = 0; // 0 bis 10

    public Ventilation(int id, String name) {
        super(id, name);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 0 || level > 10) {
            throw new IllegalArgumentException();
        }
        this.level = level;
    }

    @Override
    public double powerConsumption() {
        return 10 + level * 200;
    }
}
