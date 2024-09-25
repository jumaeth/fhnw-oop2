package smartcampus;

public enum SensorType {
    LIGHT("Lux"),
    TEMPERATURE("°C"),
    CO2("ppm");

    public final String unit;

    SensorType(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return switch (this) {
            case LIGHT -> "light sensor";
            case TEMPERATURE -> "temperature sensor";
            case CO2 -> "CO₂ sensor";
        };
    }
}
