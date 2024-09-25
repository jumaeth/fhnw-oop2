package oop2.project.models;

import java.util.ArrayList;
import java.util.List;

import oop2.project.logic.DataProcessor;
import org.json.JSONArray;
import org.json.JSONObject;

import oop2.project.api.TransportAPI;

public class Connection {

    private long departureTimestamp;
    private int delay;
    private String platform;
    private String name;
    private String to;
    private String from;

    public Connection(long departureTimestamp, int delay, String platform, String name, String to, String from) {
        this.departureTimestamp = departureTimestamp;
        this.delay = delay;
        this.platform = platform;
        this.name = name;
        this.to = to;
        this.from = from;
    }

    public long getDepartureTimestamp() {
        return departureTimestamp;
    }

    public int getDelay() {
        return delay;
    }

    public String getPlatform() {
        return platform;
    }

    public String getName() {
        return name;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String timeToDeparture() {
        long currentTime = System.currentTimeMillis() / 1000;
        long timeToDeparture = departureTimestamp - currentTime;
        return timeToDeparture / 60 + "'";
    }

    public static List<Connection> getConnections(DiDok from, DiDok to) {
        try {
            String rawData = TransportAPI.getConnection(from.getNumber(), to.getNumber());
            return parseConnections(rawData);

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Connection> parseConnections(String rawData) {
        List<Connection> result = new ArrayList<>();

        JSONObject jsonObj = new JSONObject(rawData);
        JSONArray connections = jsonObj.getJSONArray("connections");

        for (int i = 0; i < connections.length(); i++) {
            JSONObject connection = connections.getJSONObject(i);
            JSONObject jsonFrom = connection.getJSONObject("from");

            String name = connection.getJSONArray("products").getString(0);
            long departureTimestamp = DataProcessor.fixNegativeDelay(jsonFrom.getLong("departureTimestamp"));
            int delay = jsonFrom.optInt("delay", 0);
            String platform = jsonFrom.optString("platform", "");
            String to = connection.getJSONArray("sections").getJSONObject(0).getJSONObject("journey")
                    .getString("to");

            String from = jsonFrom.getJSONObject("station").getString("name");

            result.add(new Connection(departureTimestamp, delay, platform, name, to, from));
        }
        return result;
    }
}