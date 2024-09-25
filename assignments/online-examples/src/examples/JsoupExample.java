package examples;

import org.jsoup.Jsoup;

import java.io.IOException;

/*
 * Verwendet die jsoup-Bibliothek (https://jsoup.org/) um eine Wikipedia-Seite
 * herunterzuladen und eine Liste von Filmen daraus zu extrahieren. Die
 * Bibliothek funktioniert für alle möglichen Websites und wandelt den
 * heruntergeladenen HTML-Code in Java-Objekte um, die durchsucht werden können.
 *
 * Benötigt folgende Dependency in der pom.xml-Datei:
 *     <dependency>
 *         <groupId>org.jsoup</groupId>
 *         <artifactId>jsoup</artifactId>
 *         <version>1.17.2</version>
 *     </dependency>
 */
public class JsoupExample {

    public static void main(String[] args) throws IOException {
        new JsoupExample().run();
    }

    private static final String URL = "https://en.wikipedia.org/wiki/List_of_highest-grossing_films";

    private void run() throws IOException {
        var page = Jsoup.connect(URL).get();
        var table = page.getElementsByClass("wikitable").get(0);
        var links = table.getElementsByTag("a");
        int rank = 1;
        for (var link : links) {
            if (link.attr("href").startsWith("/wiki/")) {
                System.out.println(rank + ": " + link.text());
                rank++;
            }
        }
    }
}
