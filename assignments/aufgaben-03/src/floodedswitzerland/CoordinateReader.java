package floodedswitzerland;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CoordinateReader {

    /**
     * Reads a list of CH1903 coordinates from a CSV file. The file is expected
     * to have the following format:
     * <pre>
     * Municipality;East;North
     * Aadorf;709940;261380
     * Aarau;645731;249290
     * ...
     * Zwischbergen;652397;112729
     * </pre>
     * The result is a map from municipality to coordinate. If the CSV file
     * contains a municipality that is not in the list, an IOException is
     * thrown. On the other hand, if a municipality in the list is not in the
     * CSV file, it is ignored and not included in the result.
     */
    public static Map<Municipality, Coordinate> read(InputStream in,
                                                     List<Municipality> municipalities) throws IOException {
        Map<Municipality, Coordinate> map = new HashMap<>();
        if (municipalities == null || municipalities.isEmpty()) {
            return map;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            int skipLines = 1;
            while ((line = reader.readLine()) != null) {
                if (skipLines > 0) {
                    skipLines--;
                    continue;
                }
                String[] parts = line.split(";");
                Municipality m = municipalities.stream().filter(municipality -> Objects.equals(municipality.name(), parts[0])).findFirst().orElseThrow(IOException::new);
                map.put(m, new Coordinate(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
            }
        }
        return map;
    }
}
