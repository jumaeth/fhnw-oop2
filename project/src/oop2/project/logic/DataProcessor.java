package oop2.project.logic;

import oop2.project.models.DiDok;

import java.util.Comparator;
import java.util.List;

public class DataProcessor {

    public static long fixNegativeDelay(long departure) {
        if (departure < 0) {
            return 0;
        }
        return departure;
    }

    public static void sortStations(List<DiDok> stations) {
        stations.sort(Comparator.comparing(DiDok::getDesignationOfficial));
    }
}
