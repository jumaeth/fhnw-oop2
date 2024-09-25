package oop2.project.api;

import oop2.project.logic.DataProcessor;
import oop2.project.models.DiDok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class DiDokApi {

    private static final String STATIONS_URL = "https://data.sbb.ch/api/explore/v2.1/catalog/datasets/dienststellen-gemass-opentransportdataswiss/records?select=geopos%2C%20designationofficial%2C%20number&refine=isocountrycode%3A%22CH%22&limit=10&where=%22";

    public static List<DiDok> getStations(String input) {
        List<DiDok> result = new ArrayList<>();
        try {
            URL url = new URL(STATIONS_URL + URLEncoder.encode(input, StandardCharsets.UTF_8) + "%22");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            result = DiDok.parseStations(jsonResponse);
            DataProcessor.sortStations(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
