package floodedswitzerland;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevationModelTest {

    @Test
    void constructorGetter() {
        var grid = new double[][]{
                {300.0, 330.0},
                {200.0, 220.0},
                {100.0, 110.0}};
        var dem = new ElevationModel(2, 3, new Coordinate(100, 200), 50, grid);

        assertEquals(2, dem.cols());
        assertEquals(3, dem.rows());
        assertEquals(new Coordinate(100, 200), dem.lowerLeft());
        assertEquals(50, dem.cellSize());
        assertEquals(grid, dem.grid());
    }

    @Test
    void elevationAt1x1() {
        var grid = new double[][]{{100.0}};
        var dem = new ElevationModel(1, 1, new Coordinate(100, 200), 50, grid);
        assertEquals(100.0, dem.elevationAt(new Coordinate(100, 200)));
    }

    @Test
    void elevationAtExact() {
        var grid = new double[][]{
                {300.0, 330.0},
                {200.0, 220.0},
                {100.0, 110.0}};
        var dem = new ElevationModel(2, 3, new Coordinate(100, 200), 50, grid);

        assertEquals(100.0, dem.elevationAt(new Coordinate(100, 200)));
        assertEquals(110.0, dem.elevationAt(new Coordinate(150, 200)));
        assertEquals(200.0, dem.elevationAt(new Coordinate(100, 250)));
        assertEquals(220.0, dem.elevationAt(new Coordinate(150, 250)));
        assertEquals(300.0, dem.elevationAt(new Coordinate(100, 300)));
        assertEquals(330.0, dem.elevationAt(new Coordinate(150, 300)));
    }

    @Test
    void elevationAtBetween() {
        var grid = new double[][]{
                {300.0, 330.0},
                {200.0, 220.0},
                {100.0, 110.0}};
        var dem = new ElevationModel(2, 3, new Coordinate(100, 200), 50, grid);

        // if coordinate is in between grid points, returns the value of the
        // nearest grid point
        assertEquals(100.0, dem.elevationAt(new Coordinate(100, 200)));
        assertEquals(100.0, dem.elevationAt(new Coordinate(101, 200)));
        assertEquals(100.0, dem.elevationAt(new Coordinate(102, 200)));
        assertEquals(100.0, dem.elevationAt(new Coordinate(100, 201)));
        assertEquals(100.0, dem.elevationAt(new Coordinate(100, 202)));
        assertEquals(100.0, dem.elevationAt(new Coordinate(124, 224)));

        assertEquals(110.0, dem.elevationAt(new Coordinate(125, 224)));
        assertEquals(200.0, dem.elevationAt(new Coordinate(124, 225)));
        assertEquals(220.0, dem.elevationAt(new Coordinate(125, 225)));
    }

    @Test
    void elevationAtOutside() {
        var grid = new double[][]{
                {300.0, 330.0},
                {200.0, 220.0},
                {100.0, 110.0}};
        var dem = new ElevationModel(2, 3, new Coordinate(100, 200), 50, grid);

        // if nearest grid point is outside the grid, returns Double.NaN
        assertEquals(Double.NaN, dem.elevationAt(new Coordinate(74, 200)));
        assertEquals(Double.NaN, dem.elevationAt(new Coordinate(100, 174)));
        assertEquals(Double.NaN, dem.elevationAt(new Coordinate(150, 325)));
        assertEquals(Double.NaN, dem.elevationAt(new Coordinate(175, 300)));
    }
}
