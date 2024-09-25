package oop2.project.gui;

import oop2.project.guilib.*;
import oop2.project.logic.Logic;
import oop2.project.models.Config;
import oop2.project.models.Connection;
import static oop2.project.guilib.Position.below;
import static oop2.project.guilib.Position.rightOf;
import static oop2.project.guilib.TextAlignment.LEFT;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.github.humbleui.skija.Color4f;
import oop2.project.models.DiDok;

public class Gui {

    private static final int OFFSET = 14;
    private static final Color4f ERROR_COLOR = new Color4f(0xFFFF7B72);

    private static final Color4f BACKGROUND_COLOR = new Color4f(0xFF0d1117);

    private Logic logic;

    private TextField start;
    private TextField destination;
    private Group departureGroup;


    public Group initGui(Logic logic, ScheduledExecutorService executorService) {
        this.logic = logic;

        var startLabel = new Label(20, 20, 50, "Von");
        start = new TextField(rightOf(startLabel).offset(0, -5), 200);

        start.setTextColor(BACKGROUND_COLOR);
        var endLabel = new Label(below(startLabel).offset(0, 20), 50, "Nach");
        destination = new TextField(rightOf(endLabel).offset(0, -5), 200);
        destination.setTextColor(BACKGROUND_COLOR);

        var labelGroup = new Group();

        // Generate connections
        departureGroup = new Group();
        var departureLabel = new Label(below(endLabel).offset(0, 30), 200, "");
        departureLabel.setFontSize(20);

        start.setOnTextChanged(() -> createSuggestions(Station.START, labelGroup, start, start,
                () -> logic.generateConnections(destination, departureGroup)));
        destination.setOnTextChanged(() -> createSuggestions(Station.END, labelGroup, destination, start,
                () -> logic.generateConnections(destination, departureGroup)));

        executorService.scheduleAtFixedRate(
                () -> logic.generateConnections(destination, departureGroup),
                0, 20, TimeUnit.SECONDS);

        return new Group(startLabel, start, endLabel, destination, labelGroup, departureGroup,
                departureLabel);
    }

    public void setConfig(Config config) {
        start.setText(config.getFrom() == null ? "" : config.getFrom().getDesignationOfficial());
        destination.setText(config.getTo() == null ? "" : config.getTo().getDesignationOfficial());
        logic.generateConnections(destination, departureGroup);
    }

    public void generateConnections(List<Connection> connections, Control refObject, Group group) {
        if (connections.isEmpty()) {
            return;
        }
        group.removeChildren();
        var label = new Label(below(refObject).offset(0, 30), 200, connections.get(0).getFrom());

        var last = label;
        for (Connection connection : connections) {
            var name = new Label(below(last).offset(0, OFFSET), 40, connection.getName());
            var toName = new Label(rightOf(name).offset(OFFSET, 0), 150, connection.getTo());
            var platform = new Label(rightOf(toName).offset(OFFSET, 0), 100, connection.getPlatform());
            var time = new Label(rightOf(platform).offset(OFFSET, 0), connection.timeToDeparture());
            var delay = new Label(rightOf(time).offset(OFFSET, 0),
                    connection.getDelay() == 0 ? "" : "+" + connection.getDelay());
            delay.setTextColor(ERROR_COLOR);
            group.addChildren(name, toName, platform, time, delay, label);
            last = name;
        }
    }

    public void createSuggestions(Station station, Group group, TextField input, TextField placement, Runnable afterAction) {
        group.removeChildren();
        List<DiDok> autocompletions = DiDok.getAutocompletions(input.getText());
        int offset = 0;
        for (DiDok autocompletion : autocompletions) {
            Button button = createSuggestionButton(station, group, input, placement, afterAction, autocompletion, offset);
            group.addChild(button);
            offset += 30;
        }
    }

    private Button createSuggestionButton(Station station, Group group, TextField input, TextField placement, Runnable afterAction, DiDok autocompletion, int offset) {
        Button button = new Button(rightOf(placement).offset(10, offset), 300,
                autocompletion.getDesignationOfficial());
        button.setBackgroundColor(BACKGROUND_COLOR);
        button.setTextAlignment(LEFT);
        button.setOnAction(() -> {
            input.setText(autocompletion.getDesignationOfficial());
            clearSuggestions(group);
            switch (station) {
                case START:
                    logic.setStartStation(autocompletion);
                    break;
                case END:
                    logic.setEndStation(autocompletion);
                    break;
            }
            afterAction.run();
        });
        return button;
    }

    private void clearSuggestions(Group suggestionGroup) {
        suggestionGroup.removeChildren();
    }
}
