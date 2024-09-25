package timer;

import guilib.*;
import io.github.humbleui.jwm.Event;
import io.github.humbleui.skija.Color4f;

import static guilib.Position.below;
import static guilib.Position.rightOf;
import static java.util.Objects.requireNonNullElse;

public class TimerApp extends Application {

    private static final int PADDING = 20;
    private static final Color4f ERROR_COLOR = new Color4f(0xFFC00000);
    private static final int WIDTH = 520;

    public static void main(String[] args) {
        new TimerApp().start("Timer", WIDTH, 250);
    }

    private Label timeLeftLabel;
    private TextField minsField;
    private TextField secsField;
    private Button start;
    private Button reset;
    private Checkbox alarmBox;

    private final Alarm alarm = new Alarm();
    private State state = State.STOPPED;
    private Long parsedTime;
    private long endTime;
    private long timeLeft;

    @Override
    protected Group createContent() {
        timeLeftLabel = new Label(PADDING, PADDING, WIDTH - 2 * PADDING, "");
        timeLeftLabel.setTextAlignment(TextAlignment.CENTER);
        timeLeftLabel.setFontSize(80);

        minsField = new TextField(below(timeLeftLabel).offset(0, 1.5f * PADDING), 50);
        minsField.setText("0");
        var minsLabel = new Label(rightOf(minsField).offset(5, TextField.PADDING.getTop()), "minutes");
        secsField = new TextField(rightOf(minsLabel).offset(20, -TextField.PADDING.getTop()), 50);
        secsField.setText("30");
        var secsLabel = new Label(rightOf(secsField).offset(5, TextField.PADDING.getTop()), "seconds");

        start = new Button(rightOf(secsLabel).offset(30, -TextField.PADDING.getTop()), 100, "Start");
        reset = new Button(rightOf(start).offset(10, 0), 100, "Reset");

        alarmBox = new Checkbox(below(minsField).offset(0, PADDING), "Alarm");
        alarmBox.setChecked(true);

        setupEventHandlers();

        return new Group(timeLeftLabel, minsField, minsLabel,
                secsField, secsLabel, start, reset, alarmBox);
    }

    private void setupEventHandlers() {
        Runnable timeParser = () -> {
            Integer mins = tryParseIntFrom(minsField);
            Integer secs = tryParseIntFrom(secsField);
            parsedTime = mins != null && secs != null
                    ? (60L * mins + secs) * 1000
                    : null;
            if (state == State.STOPPED) {
                updateTimeLeftLabel(requireNonNullElse(parsedTime, 0L));
            }
        };
        secsField.setOnTextChanged(timeParser);
        minsField.setOnTextChanged(timeParser);
        timeParser.run();

        start.setOnAction(() -> {
            switch (state) {
                case STOPPED -> tryStart();
                case RUNNING -> pause();
                case PAUSED -> resume();
                default -> throw new AssertionError();
            }
        });

        reset.setOnAction(() -> {
            if (parsedTime != null) {
                switch (state) {
                    case RUNNING ->
                            endTime = System.currentTimeMillis() + parsedTime;
                    case PAUSED -> {
                        state = State.STOPPED;
                        updateStartButton();
                        updateTimeLeftLabel(parsedTime);
                    }
                }
            }
        });
    }

    private static Integer tryParseIntFrom(TextField textField) {
        Integer parsed = null;
        try {
            parsed = Integer.parseInt(textField.getText());
            textField.setTextColor(TextField.DEFAULT_TEXT_COLOR);
            textField.setBorderColor(TextField.DEFAULT_BORDER_COLOR);
        } catch (NumberFormatException e) {
            textField.setTextColor(ERROR_COLOR);
            textField.setBorderColor(ERROR_COLOR);
        }
        return parsed;
    }

    private void tryStart() {
        if (parsedTime != null) {
            endTime = System.currentTimeMillis() + parsedTime;
            state = State.RUNNING;
            updateStartButton();
        }
    }

    private void pause() {
        state = State.PAUSED;
        updateStartButton();
    }

    private void resume() {
        endTime = System.currentTimeMillis() + timeLeft;
        state = State.RUNNING;
        updateStartButton();
    }

    private void updateStartButton() {
        start.setText(switch (state) {
            case STOPPED -> "Start";
            case RUNNING -> "Pause";
            case PAUSED -> "Resume";
        });
    }

    private void updateTimeLeftLabel(long time) {
        var mins = time / 1000 / 60;
        var secs = time / 1000 % 60;
        var millis = time % 1000;
        timeLeftLabel.setText(String.format("%d:%02d.%03d", mins, secs, millis));
    }

    /**
     * Custom event handling specific for this app: As long as the timer is
     * running, we need to update the time label and continuously redraw the
     * window ("request a new frame"). This is not possible with standard event
     * handling offered by the controls.
     */
    @Override
    protected void handleEvent(Event e) {
        if (state == State.RUNNING) {
            timeLeft = endTime - System.currentTimeMillis();
            if (timeLeft > 0) {
                updateTimeLeftLabel(timeLeft);
                // ensure events are created continuously
                getWindow().requestFrame();
            } else {
                state = State.STOPPED;
                updateStartButton();
                updateTimeLeftLabel(0);
                if (alarmBox.isChecked()) {
                    alarm.sound();
                }
            }
        }

        // need to call super method, which informs controls and
        // performs dirty checking
        super.handleEvent(e);
    }

    private enum State {
        STOPPED, RUNNING, PAUSED
    }
}
