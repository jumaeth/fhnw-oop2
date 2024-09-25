package smartcampus;

public abstract class Actor extends Device {
    public Actor(int id, String name) {
        super(id, name);
    }

    public abstract double powerConsumption();
}
