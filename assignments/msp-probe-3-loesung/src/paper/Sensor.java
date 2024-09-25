package paper;

public class Sensor extends Device {

    private final SensorType type;

    public Sensor(int id, String name, SensorType type) {
        super(id, name);
        this.type = type;
    }

    public SensorType getType() {
        return type;
    }
}
