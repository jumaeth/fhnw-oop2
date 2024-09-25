package oop2.project.logic;

import oop2.project.models.DiDok;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataProcessorTest {

    @Test
    public void testFixNegativeDelay() {
        long negativeDelay = -10;
        long result = DataProcessor.fixNegativeDelay(negativeDelay);
        assertEquals(0, result);

        long positiveDelay = 10;
        result = DataProcessor.fixNegativeDelay(positiveDelay);
        assertEquals(positiveDelay, result);
    }

    @Test
    public void testSortStations() {
        DiDok station1 = new DiDok("Zurich", 0);
        DiDok station2 = new DiDok("Bern", 0);
        DiDok station3 = new DiDok("Geneva", 0);

        List<DiDok> stations = Arrays.asList(station1, station2, station3);
        DataProcessor.sortStations(stations);

        assertEquals("Bern", stations.get(0).getDesignationOfficial());
        assertEquals("Geneva", stations.get(1).getDesignationOfficial());
        assertEquals("Zurich", stations.get(2).getDesignationOfficial());
    }
}
