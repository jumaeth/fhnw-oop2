package smartcampus;

public class Lamp extends Actor {
    private final double power;
    private final double baseConsumption;
    private boolean on;

    public Lamp(int id, String name, double power, double baseConsumption) {
        super(id, name);
        this.power = power;
        this.baseConsumption = baseConsumption;
    }

    public Lamp(int id, String name, double power, double baseConsumption, boolean on) {
        this(id, name, power, baseConsumption);
        this.on = on;
    }

    public double getPower() {
        return power;
    }

    public double getBaseConsumption() {
        return baseConsumption;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    @Override
    public double powerConsumption() {
        if (on) {
            return power + baseConsumption;
        } else {
            return baseConsumption;
        }
    }
}
