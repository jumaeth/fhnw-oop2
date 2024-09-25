package smartcampus;

public class Sensor extends Device {

    private final SensorType type;
    private double measurement;

    public Sensor(int id, String name, SensorType type) {
        super(id, name);
        this.type = type;
    }

    public SensorType getType() {
        return type;
    }

    public double getMeasurement() {
        return measurement;
    }

    /**
     * Wird periodisch durch Sensorhardware gesetzt
     */
    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }
}
