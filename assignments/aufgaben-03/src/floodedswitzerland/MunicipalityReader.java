package floodedswitzerland;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MunicipalityReader {

    /**
     * Reads a list of municipalities from a CSV file. The file is expected to
     * have the following format:
     * <pre>
     * Municipality;Population;Area
     * Aadorf;9422;19.93
     * Aarau;21'807;12.36
     * ...
     * Zwischbergen;73;85.94
     * </pre>
     */
    public static List<Municipality> read(InputStream in) throws IOException {
        List<Municipality> municipalities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            int skipLines = 1;
            while ((line = reader.readLine()) != null) {
                if (skipLines > 0) {
                    skipLines--;
                    continue;
                }
                String[] parts = line.split(";");
                int population = Integer.parseInt(parts[1].replace("'", ""));
                municipalities.add(new Municipality(parts[0], population, Double.parseDouble(parts[2])));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + in);
        }
        return municipalities;
    }
}
