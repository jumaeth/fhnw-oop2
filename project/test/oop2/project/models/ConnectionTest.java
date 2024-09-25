package oop2.project.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import oop2.project.models.Connection;

public class ConnectionTest {

    @Test
    public void testTimeToDeparture() {
        // Departure in 1 minute
        long time = System.currentTimeMillis() / 1000 + 60;
        int delay = 0;
        String platform = "1";
        String name = "S12";
        String to = "Zürich";
        String from = "Brugg";

        Connection connection = new Connection(time, delay, platform, name, to, from);
        String expectedTimeToDeparture = "1'";
        String actualTimeToDeparture = connection.timeToDeparture();

        Assertions.assertEquals(expectedTimeToDeparture, actualTimeToDeparture);
    }

    @Test
    public void testParseConnections() {
        String rawData = "{\"connections\":[{\"from\":{\"departureTimestamp\":1631232000,\"delay\":0,\"platform\":\"1\",\"station\":{\"name\":\"Brugg\"}},\"products\":[\"S12\"],\"sections\":[{\"journey\":{\"to\":\"Zürich\"}}]}]}";
        List<Connection> expectedConnections = new ArrayList<>();
        expectedConnections.add(new Connection(1631232000, 0, "1", "S12", "Zürich", "Brugg"));

        List<Connection> actualConnections = Connection.parseConnections(rawData);

        Assertions.assertEquals(expectedConnections.size(), actualConnections.size());
        for (int i = 0; i < expectedConnections.size(); i++) {
            Connection expectedConnection = expectedConnections.get(i);
            Connection actualConnection = actualConnections.get(i);
            Assertions.assertEquals(expectedConnection.getDepartureTimestamp(),
                    actualConnection.getDepartureTimestamp());
            Assertions.assertEquals(expectedConnection.getDelay(), actualConnection.getDelay());
            Assertions.assertEquals(expectedConnection.getPlatform(), actualConnection.getPlatform());
            Assertions.assertEquals(expectedConnection.getName(), actualConnection.getName());
            Assertions.assertEquals(expectedConnection.getTo(), actualConnection.getTo());
            Assertions.assertEquals(expectedConnection.getFrom(), actualConnection.getFrom());
        }
    }
}