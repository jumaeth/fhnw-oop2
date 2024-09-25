package smartcampus;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private TimeOfDay openingTime;
    private final List<Device> devices = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public Room(String name, List<Device> devices) {
        this(name);
        this.devices.addAll(devices);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Device> getDevices() {
        return devices; // returns internal list, so it can be modified
    }

    public TimeOfDay getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(TimeOfDay openingTime) {
        this.openingTime = openingTime;
    }
}
