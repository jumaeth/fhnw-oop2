package oop2.project.api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TransportAPI {
    private static final String BASE_URL = "http://transport.opendata.ch/v1/connections";
    private static final int CONNECTION_LIMIT = 5;

    public static String getConnection(int from, int to) throws Exception {
        String urlStr = BASE_URL + "?from=" + from + "&to=" + to + "&limit=" + CONNECTION_LIMIT;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        return content.toString();
    }
}