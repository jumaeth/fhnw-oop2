package oop2.project.models;

import java.io.IOException;

import oop2.project.logic.FileHandler;

public class Config {

    private static final String CONFIG_FILE = "src/oop2/project/config/config.txt";
    private static final String DIVIDER = ":";
    private static final String SEPARATION = "#";

    private DiDok from;
    private DiDok to;

    public Config(DiDok from, DiDok to) {
        this.from = from;
        this.to = to;
    }

    public void setFrom(DiDok from) {
        this.from = from;
        save();
    }

    public void setTo(DiDok to) {
        this.to = to;
        save();
    }

    public DiDok getFrom() {
        return from;
    }

    public DiDok getTo() {
        return to;
    }

    private void save() {
        if (from == null || to == null) {
            return;
        }
        try {
            FileHandler.saveFile(CONFIG_FILE, from.getDesignationOfficial() + DIVIDER + from.getNumber() + SEPARATION
                    + to.getDesignationOfficial() + DIVIDER + to.getNumber());
        } catch (IOException e) {
            System.err.println("Error creating config file: " + e.getMessage());
        }
    }

    public static Config getConfig() {
        try {
            String res = FileHandler.loadFile(CONFIG_FILE);
            String[] parts = res.split(SEPARATION);
            String[] left = parts[0].split(DIVIDER);
            String[] right = parts[1].split(DIVIDER);
            return new Config(new DiDok(left[0], Integer.parseInt(left[1])),
                    new DiDok(right[0], Integer.parseInt(right[1])));
        } catch (Exception e) {
            return new Config(null, null);
        }
    }
}
