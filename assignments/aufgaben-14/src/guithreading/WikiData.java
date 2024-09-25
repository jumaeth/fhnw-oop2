package guithreading;

import org.wikidata.wdtk.datamodel.interfaces.EntityDocument;
import org.wikidata.wdtk.datamodel.interfaces.ItemDocument;
import org.wikidata.wdtk.datamodel.interfaces.Statement;
import org.wikidata.wdtk.datamodel.interfaces.Value;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Spliterator.*;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;
import static org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher.getWikidataDataFetcher;

public class WikiData {

    private final WikibaseDataFetcher fetcher;

    public WikiData() {
        this.fetcher = getWikidataDataFetcher();
    }

    public Optional<ItemDocument> getItemById(String id) {
        EntityDocument entity;
        try {
            entity = fetcher.getEntityDocument(id);
        } catch (MediaWikiApiErrorException | IOException e) {
            throw new RuntimeException("error retrieving item " + id, e);
        }
        if (entity instanceof ItemDocument) {
            return Optional.of((ItemDocument) entity);
        } else {
            return Optional.empty();
        }
    }

    public Stream<ItemDocument> search(String search, String language) {
        try {
            var results = fetcher.searchEntities(search, language);
            return results.stream()
                    .map(r -> r.getEntityId())
                    .flatMap(id -> getItemById(id).stream());
        } catch (MediaWikiApiErrorException | IOException e) {
            throw new RuntimeException("error searching for \"" + search + "\"", e);
        }
    }

    public static Stream<Statement> statements(ItemDocument item) {
        return stream(spliteratorUnknownSize(
                item.getAllStatements(), IMMUTABLE | NONNULL | ORDERED), false);
    }

    public static Predicate<Statement> concerns(String property) {
        return stmt -> stmt.getMainSnak().getPropertyId().getId().equals(property);
    }

    public static Stream<Value> statementValues(ItemDocument item, String property) {
        return statements(item)
                .filter(concerns(property))
                .sorted(comparing(Statement::getRank))
                .map(Statement::getValue)
                .filter(Objects::nonNull);
    }
}
