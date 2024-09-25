package examples;

import org.wikidata.wdtk.datamodel.interfaces.ItemDocument;
import org.wikidata.wdtk.datamodel.interfaces.PropertyDocument;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;

import java.util.Scanner;

/*
 * Verwendet das Wikidata Toolkit (https://www.mediawiki.org/wiki/Wikidata_Toolkit/de)
 * um auf Wikidata nach «Dingen» zu suchen und Eigenschaften dazu anzuzeigen. Wikidata
 * enthält strukturierte Informationen zu allen möglichen Objekten, wie Personen,
 * Organisationen, Ländern, Filmen, usw. Alles, was eine eigene Wikipedia-Seite hat,
 * ist wahrscheinlich auch auf Wikidata vorhanden.
 *
 * Siehe https://www.wikidata.org/wiki/Wikidata:Introduction/de#Das_Wikidata-Repositorium
 * für eine Einführung, wie Daten auf Wikidata strukturiert sind.
 *
 * Benötigt folgende Dependency in der pom.xml-Datei:
 *     <dependency>
 *         <groupId>org.wikidata.wdtk</groupId>
 *         <artifactId>wdtk-wikibaseapi</artifactId>
 *         <version>0.14.7</version>
 *     </dependency>
 */
public class WikidataExample {

    public static void main(String[] args) throws Exception {
        new WikidataExample().run();
    }

    private final WikibaseDataFetcher fetcher = WikibaseDataFetcher.getWikidataDataFetcher();

    private void run() throws Exception {
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.print("wikidata> ");
            var input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }

            var results = fetcher.searchEntities(input, "de");
            for (var result : results) {
                System.out.println(result.getLabel() + " (" + result.getEntityId() + ")");
                if (result.getDescription() != null) {
                    System.out.println("  " + result.getDescription());
                }
                var item = getItemById(result.getEntityId());
                if (item != null) {
                    var i = item.getAllStatements();
                    while (i.hasNext()) {
                        var statement = i.next();
                        var propertyId = statement.getMainSnak().getPropertyId().getId();
                        var property = getPropertyById(propertyId);
                        var label = property.getLabels().get("de");
                        if (label != null) {
                            System.out.println("    (" + propertyId + ") " + label.getText() + ": " + statement.getValue());
                        }
                    }
                }
            }
        }
    }

    private ItemDocument getItemById(String id) throws Exception {
        var entity = fetcher.getEntityDocument(id);
        if (entity instanceof ItemDocument item) {
            return item;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private PropertyDocument getPropertyById(String id) throws Exception {
        var entity = fetcher.getEntityDocument(id);
        if (entity instanceof PropertyDocument property) {
            return property;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
