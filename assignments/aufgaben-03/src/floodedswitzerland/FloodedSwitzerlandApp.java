package floodedswitzerland;

import gui.Color;
import gui.Window;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.newInputStream;

public class FloodedSwitzerlandApp {

    private static final Path DEM_FILE = Path.of("aufgaben-03/data/DHM200.asc");
    private static final Path MUNI_FILE = Path.of("aufgaben-03/data/municipalities.csv");
    private static final Path COORD_FILE = Path.of("aufgaben-03/data/coordinates.csv");

    private static final Coordinate BG_LOWER_LEFT = new Coordinate(480_000, 62_000);
    private static final int BG_WIDTH = 360_000;
    private static final int BG_HEIGHT = 238_000;
    private static final double BG_PIXEL_WIDTH = 3000;

    private static final Coordinate MASK_LOWER_LEFT = new Coordinate(444_000, 26_000);
    private static final int MASK_WIDTH = 432_000;
    private static final int MASK_HEIGHT = 310_000;
    private static final double MASK_PIXEL_WIDTH = 3600;

    private static final Color WATER_COLOR = new Color(0, 0, 255, 128);

    public static void main(String[] args) {
        ElevationModel elevationModel;
        try {
            elevationModel = ElevationModelReader.read(newInputStream(DEM_FILE));
        } catch (IOException e) {
            System.out.println("Could not read DEM file: " + e.getMessage());
            return;
        }

        List<Municipality> municipalityList;
        try {
            municipalityList = MunicipalityReader.read(newInputStream(MUNI_FILE));
        } catch (IOException e) {
            System.out.println("Could not read municipalities file: " + e.getMessage());
            return;
        }

        Map<Municipality, Coordinate> municipalities;
        try {
            municipalities = CoordinateReader.read(newInputStream(COORD_FILE), municipalityList);
        } catch (IOException e) {
            System.out.println("Could not read coordinates file: " + e.getMessage());
            return;
        }

        var simulation = new FloodSimulation(elevationModel, municipalities);
        new FloodedSwitzerlandApp(simulation).run();
    }

    private final FloodSimulation sim;
    private final Window window;

    public FloodedSwitzerlandApp(FloodSimulation sim) {
        this.sim = sim;
        window = new Window("Flooded Switzerland", 1000, 700);
        window.setResizable(true);
    }

    private void run() {
        window.open();

        window.setColor(WATER_COLOR);
        window.setFontSize(16);
        window.setLineSpacing(1.5);
        while (window.isOpen()) {
            drawBackground();
            for (double x = 0; x < window.getWidth(); x += 0.5) {
                for (double y = 0; y < window.getHeight(); y += 0.5) {
                    var coordinate = toCh1903(x, y);
                    if (sim.isFlooded(coordinate)) {
                        window.fillRect(x, y, 0.5, 0.5);
                    }
                }
            }
            drawMask();

            var pop = sim.floodedPopulation() / 1000 * 1000;
            var popPercent = sim.floodedPopulationPercent();
            var area = (int) sim.floodedArea();
            var stats = """
                    Water level: %d m
                    Flooded population: %,d (%,.1f%%)
                    Flooded area: %,d km²
                    """.formatted(sim.getWaterLevel(), pop, popPercent, area);
            window.drawString(stats, 18, 30);

            sim.increaseWaterLevel();
            window.refreshAndClear(50);
        }
    }

    private void drawBackground() {
        var x = toGuiX(BG_LOWER_LEFT.east());
        var y = toGuiY(BG_LOWER_LEFT.north() + BG_HEIGHT);
        var map = "aufgaben-03/data/Switzerland_satellite.png";
        window.drawImage(map, x, y, BG_WIDTH / BG_PIXEL_WIDTH * scale());
    }

    private void drawMask() {
        var x = toGuiX(MASK_LOWER_LEFT.east());
        var y = toGuiY(MASK_LOWER_LEFT.north() + MASK_HEIGHT);
        var map = "aufgaben-03/data/Switzerland_mask.png";
        window.drawImage(map, x, y, MASK_WIDTH / MASK_PIXEL_WIDTH * scale());
    }

    /**
     * Transforms the given CH1903 east coordinate into a GUI x coordinate.
     */
    private double toGuiX(double east) {
        var east0 = BG_LOWER_LEFT.east() - (window.getWidth() / scale() - BG_WIDTH) / 2;
        return (east - east0) * scale();
    }

    /**
     * Transforms the given CH1903 north coordinate into a GUI y coordinate.
     */
    private double toGuiY(double north) {
        var north0 = BG_LOWER_LEFT.north() + BG_HEIGHT
                     + (window.getHeight() / scale() - BG_HEIGHT) / 2;
        return (north0 - north) * scale();
    }

    /**
     * Transforms the given GUI coordinate into a CH1903 coordinate.
     */
    private Coordinate toCh1903(double x, double y) {
        var east0 = BG_LOWER_LEFT.east() - (window.getWidth() / scale() - BG_WIDTH) / 2;
        var east = (int) (x / scale() + east0);
        var north0 = BG_LOWER_LEFT.north() + BG_HEIGHT
                     + (window.getHeight() / scale() - BG_HEIGHT) / 2;
        var north = (int) (north0 - y / scale());
        return new Coordinate(east, north);
    }

    /**
     * Computes the scaling factor for the map.
     */
    private double scale() {
        return Math.min(window.getWidth() / BG_WIDTH, window.getHeight() / BG_HEIGHT);
    }
}
