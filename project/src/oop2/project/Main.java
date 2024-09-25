package oop2.project;

import oop2.project.gui.Gui;
import oop2.project.guilib.*;
import oop2.project.logic.Logic;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Stream;


public class Main extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    private final Gui gui = new Gui();
    private final Logic logic = new Logic();

    public static void main(String[] args) {
        new Main().start("Abfahrtszeiten", WIDTH, HEIGHT);
        Runtime.getRuntime().addShutdownHook(new Thread(EXECUTOR::shutdown));

    }

    @Override
    protected Group createContent() {
        Group group = gui.initGui(logic, EXECUTOR);
        logic.initLogic(gui);
        return group;
    }
}
