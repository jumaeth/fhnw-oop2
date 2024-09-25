package floodedswitzerland;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FloodSimulationTest {

    @Test
    public void testInitialization() {
        var grid = new double[][] {};
        ElevationModel model = new ElevationModel(2, 3, new Coordinate(100, 200), 50, grid);
        Map<Municipality, Coordinate> map = new HashMap<>();

        FloodSimulation fs = new FloodSimulation(model, map);

        assertEquals(150, fs.getWaterLevel());
    }

    @Test
    public void testGetAndSetWaterLevel() {
        var grid = new double[][] {};
        ElevationModel model = new ElevationModel(2, 3, new Coordinate(100, 200), 50, grid);
        Map<Municipality, Coordinate> map = new HashMap<>();
        FloodSimulation fs = new FloodSimulation(model, map);

        fs.setWaterLevel(1001);

        assertEquals(1001, fs.getWaterLevel());
    }

    @Test
    public void testIncreaseWaterLevel() {
        FloodSimulation floodSimulation = new FloodSimulation(null, null);

        floodSimulation.increaseWaterLevel();
        assertEquals(155, floodSimulation.getWaterLevel());

        floodSimulation.setWaterLevel(1000);
        floodSimulation.increaseWaterLevel();
        assertEquals(1010, floodSimulation.getWaterLevel());


        floodSimulation.setWaterLevel(2000);
        floodSimulation.increaseWaterLevel();
        assertEquals(2020, floodSimulation.getWaterLevel());
    }

    @Test
    public void testIsFlooded() {
        var grid = new double[][] {
                { 300.0, 350.0 },
                { 200.0, 250.0 } };
        ElevationModel model = new ElevationModel(2, 2, new Coordinate(100, 200), 50, grid);

        Map<Municipality, Coordinate> map = new HashMap<>();
        map.put(new Municipality("Bern", 10, 20), new Coordinate(100, 200));
        FloodSimulation fs = new FloodSimulation(model, map);
        fs.setWaterLevel(200);

        boolean isFlooded = fs.isFlooded(new Coordinate(100, 200));
        assertTrue(isFlooded);

        isFlooded = fs.isFlooded(new Coordinate(150, 200));
        assertFalse(isFlooded);
    }

    @Test
    public void testTotalPopulation() {
        var grid = new double[][] {};
        ElevationModel model = new ElevationModel(2, 3, new Coordinate(100, 200), 50, grid);
        Map<Municipality, Coordinate> map = new HashMap<>();
        map.put(new Municipality("Bern", 10, 20), new Coordinate(100, 200));
        map.put(new Municipality("Thun", 10, 20), new Coordinate(100, 200));
        FloodSimulation fs = new FloodSimulation(model, map);

        int totalPopulation = fs.totalPopulation();

        assertEquals(20, totalPopulation);
    }

    @Test
    public void testTotalPopulationEmpty() {
        var grid = new double[][] {};
        ElevationModel model = new ElevationModel(2, 3, new Coordinate(100, 200), 50, grid);
        Map<Municipality, Coordinate> map = new HashMap<>();
        FloodSimulation fs = new FloodSimulation(model, map);

        int totalPopulation = fs.totalPopulation();

        assertEquals(0, totalPopulation);
    }

    @Test
    public void testFloodedPopulation() {
        var grid = new double[][] {
                { 300.0, 350.0 },
                { 200.0, 250.0 } };
        ElevationModel model = new ElevationModel(2, 2, new Coordinate(100, 200), 50, grid);

        Map<Municipality, Coordinate> map = new HashMap<>();
        map.put(new Municipality("Bern", 10, 20), new Coordinate(100, 200));
        FloodSimulation fs = new FloodSimulation(model, map);
        fs.setWaterLevel(200);
        int floodedPopulation = fs.floodedPopulation();

        assertEquals(10, floodedPopulation);
    }

    @Test
    public void testFloodedPopulationEmpty() {
        var grid = new double[][] {
                { 300.0, 350.0 },
                { 200.0, 250.0 } };
        ElevationModel model = new ElevationModel(2, 2, new Coordinate(100, 200), 50, grid);

        Map<Municipality, Coordinate> map = new HashMap<>();
        FloodSimulation fs = new FloodSimulation(model, map);
        fs.setWaterLevel(200);
        int floodedPopulation = fs.floodedPopulation();

        assertEquals(0, floodedPopulation);
    }

    @Test
    public void testFloodedPopulationPercent() {
        var grid = new double[][] {
                { 300.0, 350.0 },
                { 200.0, 250.0 } };
        ElevationModel model = new ElevationModel(2, 2, new Coordinate(100, 200), 50, grid);

        Map<Municipality, Coordinate> map = new HashMap<>();
        map.put(new Municipality("Bern", 10, 20), new Coordinate(200, 300));
        map.put(new Municipality("Thun", 10, 20), new Coordinate(100, 200));
        FloodSimulation fs = new FloodSimulation(model, map);
        fs.setWaterLevel(200);
        double floodedPopulationPercent = fs.floodedPopulationPercent();

        assertEquals(50.0, floodedPopulationPercent);
    }

    @Test
    public void testFloodedPopulationPercentEmpty() {
        var grid = new double[][] {
                { 300.0, 350.0 },
                { 200.0, 250.0 } };
        ElevationModel model = new ElevationModel(2, 2, new Coordinate(100, 200), 50, grid);

        Map<Municipality, Coordinate> map = new HashMap<>();
        FloodSimulation fs = new FloodSimulation(model, map);
        fs.setWaterLevel(200);

        double floodedPopulationPercent = fs.floodedPopulationPercent();

        assertEquals(Double.NaN, floodedPopulationPercent);
    }

    @Test
    public void testFloodedArea() {
        var grid = new double[][] {
                { 300.0, 350.0 },
                { 200.0, 250.0 },
                { 100.0, 150.0 }
        };
        ElevationModel model = new ElevationModel(2, 3, new Coordinate(100, 200), 50, grid);

        Map<Municipality, Coordinate> map = new HashMap<>();
        map.put(new Municipality("Bern", 1, 22.2), new Coordinate(150, 300));
        map.put(new Municipality("Thun", 1, 11.1), new Coordinate(100, 250));
        FloodSimulation fs = new FloodSimulation(model, map);

        double area = fs.floodedArea();
        assertEquals(0, area);

        fs.setWaterLevel(200);
        area = fs.floodedArea();
        assertEquals(11.1, area);

        fs.setWaterLevel(500);
        area = fs.floodedArea();
        assertEquals(33.3, area, 0.0001);
    }

    @Test
    public void testFloodedAreaEmpty() {
        var grid = new double[][] {
                { 300.0, 350.0 },
                { 200.0, 250.0 }
        };
        ElevationModel model = new ElevationModel(2, 2, new Coordinate(100, 200), 50, grid);

        Map<Municipality, Coordinate> map = new HashMap<>();
        FloodSimulation fs = new FloodSimulation(model, map);
        fs.setWaterLevel(200);

        double area = fs.floodedArea();

        assertEquals(0, area);
    }
}
