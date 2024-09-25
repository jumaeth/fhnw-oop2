package floodedswitzerland;

/**
 * Represents a model of the elevation of a region. The model is based on a grid
 * with a given number of columns and rows, and a cell size.
 * <p>
 * Like in an Esri grid file, the grid's origin is the lower left (south-west)
 * corner, given as a CH1903 coordinate, and the grid is stored in row-major
 * order, i.e., the first (north-most) row is stored first, then the second row,
 * and so on.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Esri_grid">Esri grid</a>
 */
public class ElevationModel {

    private final int cols;
    private final int rows;
    private final Coordinate lowerLeft;
    private final double cellSize;
    private final double[][] grid;

    public ElevationModel(int cols, int rows, Coordinate lowerLeft,
                          double cellSize, double[][] grid) {
        this.cols = cols;
        this.rows = rows;
        this.lowerLeft = lowerLeft;
        this.cellSize = cellSize;
        this.grid = grid;
    }

    public int cols() {
        return cols;
    }

    public int rows() {
        return rows;
    }

    public Coordinate lowerLeft() {
        return lowerLeft;
    }

    public double cellSize() {
        return cellSize;
    }

    public double[][] grid() {
        return grid;
    }

    /**
     * Returns the elevation at the given coordinate. If the coordinate does not
     * match a grid point, returns the value of the nearest grid point. If the
     * coordinate is outside the grid, returns {@link Double#NaN}.
     */
    public double elevationAt(Coordinate coordinate) {
        var row = (int) (grid.length - Math.round((coordinate.north() - lowerLeft.north()) / cellSize) - 1);
        var col = (int) (Math.round((coordinate.east() - lowerLeft.east()) / cellSize));
        if (0 <= row && row < grid.length && 0 <= col && col < grid[0].length) {
            return grid[row][col];
        } else {
            return Double.NaN;
        }
    }
}
