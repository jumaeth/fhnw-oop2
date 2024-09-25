package floodedswitzerland;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevationModelReaderTest {

    @Test
    void readHeader() {
        var content = """
                NCOLS 0
                NROWS 0
                XLLCORNER 479900.
                YLLCORNER 61900.
                CELLSIZE 200.
                NODATA_VALUE -9999.
                """;
        var dem = ElevationModelReader.read(asStream(content));
        assertEquals(0, dem.cols());
        assertEquals(0, dem.rows());
        assertEquals(479900, dem.lowerLeft().east());
        assertEquals(61900, dem.lowerLeft().north());
        assertEquals(200, dem.cellSize());
    }

    @Test
    void read1x1() {
        var content = """
                NCOLS 1
                NROWS 1
                XLLCORNER 479900.
                YLLCORNER 61900.
                CELLSIZE 200.
                NODATA_VALUE -9999.
                322.5
                """;
        var dem = ElevationModelReader.read(asStream(content));
        assertEquals(1, dem.cols());
        assertEquals(1, dem.rows());

        var grid = dem.grid();
        assertEquals(1, grid.length);
        assertEquals(1, grid[0].length);

        assertEquals(322.5, grid[0][0]);
    }

    @Test
    void read2x2() {
        var content = """
                NCOLS 2
                NROWS 2
                XLLCORNER 479900.
                YLLCORNER 61900.
                CELLSIZE 200.
                NODATA_VALUE -9999.
                322.5 307.8 295.2 319.2
                """;
        var dem = ElevationModelReader.read(asStream(content));
        assertEquals(2, dem.cols());
        assertEquals(2, dem.rows());

        var grid = dem.grid();
        assertEquals(2, grid.length);
        assertEquals(2, grid[0].length);

        assertEquals(322.5, grid[0][0]);
        assertEquals(307.8, grid[0][1]);
        assertEquals(295.2, grid[1][0]);
        assertEquals(319.2, grid[1][1]);
    }

    @Test
    void readRectangular() {
        var content = """
                NCOLS 2
                NROWS 3
                XLLCORNER 479900.
                YLLCORNER 61900.
                CELLSIZE 200.
                NODATA_VALUE -9999.
                322.5 307.8 295.2 319.2 103. 301.7
                """;
        var dem = ElevationModelReader.read(asStream(content));
        assertEquals(2, dem.cols());
        assertEquals(3, dem.rows());

        var grid = dem.grid();
        assertEquals(3, grid.length);
        assertEquals(2, grid[0].length);

        assertEquals(322.5, grid[0][0]);
        assertEquals(307.8, grid[0][1]);
        assertEquals(295.2, grid[1][0]);
        assertEquals(319.2, grid[1][1]);
        assertEquals(103.0, grid[2][0]);
        assertEquals(301.7, grid[2][1]);
    }

    @Test
    void readNoData() {
        var content = """
                NCOLS 2
                NROWS 2
                XLLCORNER 479900.
                YLLCORNER 61900.
                CELLSIZE 200.
                NODATA_VALUE -9999.
                322.5 307.8 -9999. 319.2
                """;
        var dem = ElevationModelReader.read(asStream(content));
        assertEquals(2, dem.grid().length);
        assertEquals(2, dem.grid()[0].length);

        assertEquals(322.5, dem.grid()[0][0]);
        assertEquals(307.8, dem.grid()[0][1]);
        assertEquals(Double.NaN, dem.grid()[1][0]);
        assertEquals(319.2, dem.grid()[1][1]);
    }

    private InputStream asStream(String text) {
        return new ByteArrayInputStream(text.getBytes(US_ASCII));
    }
}
