package oop2.project.models;

public class GeoPos {
    private double lat;
    private double lon;

    public GeoPos(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
