package oop2.project.models;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiDokTest {
    @Test
    public void testToString() {
        GeoPos geoPos = new GeoPos(47.378177, 8.540192);
        DiDok diDok = new DiDok(geoPos, "Zurich", 1);
        assertEquals("47.378177 8.540192 Zurich 1", diDok.toString());
    }

    @Test
    public void testParseStations() {
        String jsonString = "{\"results\":[{\"geopos\":{\"lon\":8.540192,\"lat\":47.378177},\"designationofficial\":\"Zurich\",\"number\":1}]}";
        JSONObject jsonObject = new JSONObject(jsonString);
        List<DiDok> diDoks = DiDok.parseStations(jsonObject);
        assertEquals(1, diDoks.size());
        DiDok diDok = diDoks.get(0);
        assertEquals("Zurich", diDok.getDesignationOfficial());
        assertEquals(1, diDok.getNumber());
    }
}
