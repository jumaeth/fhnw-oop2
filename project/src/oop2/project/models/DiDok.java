package oop2.project.models;

import oop2.project.api.DiDokApi;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiDok {
    private GeoPos geoPos;
    private String designationOfficial;
    private Integer number;

    public DiDok(GeoPos geoPos, String designationOfficial, Integer number) {
        this.geoPos = geoPos;
        this.designationOfficial = designationOfficial;
        this.number = number;
    }

    public DiDok(String designationOfficial, int number) {
        this.designationOfficial = designationOfficial;
        this.number = number;
    }

    @Override
    public String toString() {
        return geoPos.getLat() + " " + geoPos.getLon() + " " + designationOfficial + " " + number;
    }

    public String getDesignationOfficial() {
        return designationOfficial;
    }

    public Integer getNumber() {
        return number;
    }

    public static List<DiDok> getAutocompletions(String input) {
        try {
            return DiDokApi.getStations(input);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<DiDok> parseStations(JSONObject input) {
        List<DiDok> result = new ArrayList<>();
        JSONArray records = input.getJSONArray("results");
        for (int i = 0; i < records.length(); i++) {
            JSONObject record = records.getJSONObject(i);
            JSONObject geopos = record.getJSONObject("geopos");
            String designationofficial = record.getString("designationofficial");
            Number number = record.getNumber("number");
            GeoPos geoPos = new GeoPos(geopos.getDouble("lon"), geopos.getDouble("lat"));
            DiDok diDok = new DiDok(geoPos, designationofficial, number.intValue());
            result.add(diDok);
        }
        return result;
    }
}
