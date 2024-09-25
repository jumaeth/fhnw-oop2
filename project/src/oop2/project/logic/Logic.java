package oop2.project.logic;

import oop2.project.gui.Gui;
import oop2.project.guilib.Control;
import oop2.project.guilib.Group;
import oop2.project.models.Config;
import oop2.project.models.Connection;
import oop2.project.models.DiDok;

public class Logic {

    private Gui gui;

    private Config config;

    private DiDok startStation;
    private DiDok endStation;

    public void initLogic(Gui gui) {
        this.gui = gui;
        initConfig();
    }

    public void generateConnections(Control refLabel, Group group) {
        if (startStation == null || endStation == null) {
            return;
        }

        var connections = Connection.getConnections(startStation, endStation);
        gui.generateConnections(connections, refLabel, group);
    }

    public void setStartStation(DiDok startStation) {
        this.startStation = startStation;
        updateConfig();
    }

    public void setEndStation(DiDok endStation) {
        this.endStation = endStation;
        updateConfig();
    }

    private void initConfig() {
        config = Config.getConfig();
        startStation = config.getFrom();
        endStation = config.getTo();
        gui.setConfig(config);
    }

    private void updateConfig() {
        config.setFrom(startStation);
        config.setTo(endStation);
    }
}
