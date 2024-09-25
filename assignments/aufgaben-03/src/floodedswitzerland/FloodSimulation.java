package floodedswitzerland;

import java.util.Map;

/**
 * Simulates a flood in Switzerland. For a given water level, it can calculate
 * the flooded area (specific coordinates and total area), the flooded
 * population, and the percentage of the population that is flooded. It requires
 * an elevation model and a map of municipalities with their coordinates.
 * <p>
 * The total flooded population and area are calculated by summing up the
 * population and area of all municipalities that are flooded. This is a
 * simplification, as it does not take into account the shape of the flooded
 * area. For example, a municipality might be partially flooded, but the entire
 * population and area is counted as flooded.
 */
public class FloodSimulation {

    private final ElevationModel elevationModel;
    private final Map<Municipality, Coordinate> municipalities;
    private int waterLevel;

    public FloodSimulation(ElevationModel elevationModel,
                           Map<Municipality, Coordinate> municipalities) {
        this.elevationModel = elevationModel;
        this.municipalities = municipalities;
        this.waterLevel = 150;
    }

    /**
     * Returns the water level in meters above sea level. At the beginning, the
     * water level is 150 m.
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * Sets the water level in meters above sea level. This is useful for
     * testing.
     */
    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    /**
     * Increases the water level step by step. For low elevations, the water
     * level is increased more slowly than for high elevations: up to 1000 m,
     * the water level is increased by 5 m; between 1000 m and 2000 m, by 10 m;
     * and above 2000 m, by 20 m.
     */
    public void increaseWaterLevel() {
        if (waterLevel < 1000) {
            waterLevel += 5;
        } else if (waterLevel < 2000) {
            waterLevel += 10;
        } else {
            waterLevel += 20;
        }
    }

    /**
     * Returns whether the given coordinate is flooded. A coordinate is flooded
     * if the elevation at that coordinate is less than or equal to the water
     * level, as given by the elevation model.
     */
    public boolean isFlooded(Coordinate coordinate) {
        return elevationModel.elevationAt(coordinate) <= waterLevel;
    }

    /**
     * Returns the total population of all municipalities.
     */
    public int totalPopulation() {
        int totalPopulation = 0;
        for (Municipality municipality : municipalities.keySet()) {
            totalPopulation += municipality.population();
        }
        return totalPopulation;
    }

    /**
     * Returns the population of all municipalities that are flooded. Any
     * municipality is either completely flooded or not flooded at all,
     * according to its coordinate.
     */
    public int floodedPopulation() {
        int count = 0;
        for (Map.Entry<Municipality, Coordinate> entry : municipalities.entrySet()) {
            if (isFlooded(entry.getValue())) {
                count += entry.getKey().population();
            }
        }
        return count;
    }

    /**
     * Returns the percentage of the population that is flooded.
     */
    public double floodedPopulationPercent() {
        return (double) floodedPopulation() / totalPopulation() * 100;
    }

    /**
     * Returns the area of all municipalities that are flooded. Any municipality
     * is either completely flooded or not flooded at all, according to its
     * coordinate.
     */
    public double floodedArea() {
        double count = 0;
        for (Map.Entry<Municipality, Coordinate> entry : municipalities.entrySet()) {
            if (isFlooded(entry.getValue())) {
                count += entry.getKey().area();
            }
        }
        return count;
    }
}
