package oop2.msp.model;

import static java.util.Objects.requireNonNull;

public class App {

    private String name;
    private String version;

    public App(String name, String version) {
        this.name = requireNonNull(name);
        this.version = requireNonNull(version);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = requireNonNull(name);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = requireNonNull(version);
    }

    @Override
    public String toString() {
        return "App " + name + " (" + version + ")";
    }
}
