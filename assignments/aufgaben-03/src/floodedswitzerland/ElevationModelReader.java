package floodedswitzerland;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

/**
 * Reads an elevation model from an Esri grid file.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Esri_grid">Esri grid</a>
 */
public class ElevationModelReader {

    public static ElevationModel read(InputStream in) {
        try (var scanner = new Scanner(in).useLocale(Locale.ROOT)) {
            scanner.next(); // skip "NCOLS"
            int cols = scanner.nextInt();
            scanner.next(); // skip "NROWS"
            int rows = scanner.nextInt();
            scanner.next(); // skip "XLLCORNER"
            double lowerLeftX = scanner.nextDouble();
            scanner.next(); // skip "YLLCORNER"
            double lowerLeftY = scanner.nextDouble();
            scanner.next(); // skip "CELLSIZE"
            double cellSize = scanner.nextDouble();
            scanner.next(); // skip "NODATA_VALUE"
            double noDataValue = scanner.nextDouble();

            double[][] grid = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    var value = scanner.nextDouble();
                    grid[i][j] = value != noDataValue ? value : Double.NaN;
                }
            }
            return new ElevationModel(cols, rows,
                    new Coordinate(lowerLeftX, lowerLeftY), cellSize, grid);
        }
    }
}
