package examples;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

/*
 * Verwendet die HttpClient-Klasse von Java, um Buchdaten von einer REST-API
 * (https://gutendex.com/) herunterzuladen und diese anzuzeigen. Die API gibt
 * JSON-Daten zurück, welche mittels einer externen JSON-Bibliothek in
 * Java-Objekte umgewandelt werden.
 *
 * Benötigt folgende Dependency in der pom.xml-Datei (für das Parsen von JSON):
 *     <dependency>
 *         <groupId>org.json</groupId>
 *         <artifactId>json</artifactId>
 *         <version>20231013</version>
 *     </dependency>
 */
public class HttpRequestExample {

    public static void main(String[] args) throws Exception {
        new HttpRequestExample().run();
    }

    private void run() throws Exception {
        var client = HttpClient.newHttpClient();

        var url = "https://gutendex.com/books/?languages=de";
        int page = 1;
        while (!url.isEmpty()) {
            System.out.println("(Page " + page + ")");

            var request = HttpRequest.newBuilder(new URI(url)).GET().build();
            var response = client.send(request, BodyHandlers.ofString());
            var json = new JSONObject(response.body());

            var results = json.getJSONArray("results");
            for (var result : results) {
                var book = (JSONObject) result;
                var title = book.getString("title");
                var formats = book.getJSONObject("formats");
                if (formats.has("text/html")) {
                    var htmlUrl = formats.getString("text/html");
                    System.out.println(title + ": " + htmlUrl);
                }
            }

            url = json.optString("next"); // returns empty string if null
            page++;
        }
    }
}
