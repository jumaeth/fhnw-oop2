package guithreading;

import guilib.Application;
import guilib.Group;
import guilib.Label;
import guilib.TextField;
import org.wikidata.wdtk.datamodel.interfaces.QuantityValue;

import java.util.Optional;

import static guilib.Position.below;
import static guilib.TextAlignment.CENTER;
import static java.lang.String.format;

public class PlacesSearchUnsynchronized extends Application {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 220;
    private static final float PADDING = 15;

    private static final String POPULATION = "P1082";
    private static final String AREA = "P2046";

    public static void main(String[] args) {
        new PlacesSearchUnsynchronized().start("Wikidata Places Search", WIDTH, HEIGHT);
    }

    private final WikiData wikiData = new WikiData();
    private TextField searchField;
    private Label nameLabel;
    private Label popuLabel;
    private Label areaLabel;

    @Override
    protected Group createContent() {
        var fullWidth = WIDTH - 2 * PADDING;
        var title = new Label(PADDING, PADDING, fullWidth, "Wikidata Places Search");
        title.setFontSize(24);
        title.setTextAlignment(CENTER);

        searchField = new TextField(below(title).offset(0, PADDING), fullWidth);
        searchField.setOnTextChanged(() -> search(searchField.getText()));

        nameLabel = new Label(below(searchField).offset(0, PADDING), fullWidth, "—");
        popuLabel = new Label(below(nameLabel).offset(0, PADDING), fullWidth, "—");
        areaLabel = new Label(below(popuLabel).offset(0, PADDING), fullWidth, "—");

        return new Group(title, searchField, nameLabel, popuLabel, areaLabel);
    }

    private void search(String text) {
        if (text == null || text.isBlank()) {
            return;
        }
        // perform search in background thread to not block the UI:
        new Thread(() -> {
            var result = wikiData.search(text, "de").findFirst();
            result.ifPresent(item -> {
                var name = Optional.ofNullable(item.getLabels().get("de"))
                        .map(v -> v.getText());
                var population = WikiData.statementValues(item, POPULATION)
                        .map(v -> ((QuantityValue) v).getNumericValue())
                        .map(v -> format("%,d", v.longValue()))
                        .findFirst();
                var area = WikiData.statementValues(item, AREA)
                        .map(v -> ((QuantityValue) v).getNumericValue())
                        .map(v -> format("%,d", v.longValue()))
                        .findFirst();

                // does this work?
                nameLabel.setText(name.orElse("—"));
                popuLabel.setText("Einwohner: " + population.orElse("—"));
                areaLabel.setText("Fläche: " + area.orElse("—"));
            });
        }).start();
    }
}
