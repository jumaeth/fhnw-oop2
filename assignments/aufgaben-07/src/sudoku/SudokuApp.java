package sudoku;

import guilib.*;
import io.github.humbleui.jwm.Event;
import io.github.humbleui.jwm.EventKey;
import io.github.humbleui.jwm.Key;
import io.github.humbleui.skija.Color4f;

public class SudokuApp extends Application {

    static final int FONT_SIZE = 24;
    static final int PADDING = 30;
    static final int CELL_SIZE = 42;
    static final int SMALL_SPACE = 5;
    static final int LARGE_SPACE = 20;
    static final int TOTAL_SIZE = 9 * CELL_SIZE + 6 * SMALL_SPACE
            + 2 * LARGE_SPACE + 2 * PADDING;

    static final Color4f CORRECT_COLOR = new Color4f(0xFF38BE75);
    static final Color4f ERROR_COLOR = new Color4f(0xFFC00000);

    public static void main(String[] args) {
        new SudokuApp().start("Sudoku", TOTAL_SIZE, TOTAL_SIZE);
    }

    private final SudokuGame game = new SudokuGame();

    private final TextField[][] fields = new TextField[9][9];
    private final SudokuBackground background = new SudokuBackground();

    @Override
    protected Group createContent() {
        var content = new Group(background);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                var x = PADDING + col * (CELL_SIZE + SMALL_SPACE)
                        + (col / 3) * (LARGE_SPACE - SMALL_SPACE);
                var y = PADDING + row * (CELL_SIZE + SMALL_SPACE)
                        + (row / 3) * (LARGE_SPACE - SMALL_SPACE);
                if (game.isCellEmpty(row, col)) {
                    var field = new TextField(x, y, CELL_SIZE);
                    field.setFontSize(FONT_SIZE);
                    var theRow = row;
                    var theCol = col;
                    field.setOnTextChanged(() -> updateField(theRow, theCol, field));
                    fields[row][col] = field;
                    content.addChild(field);
                } else {
                    var label = new Label(x, y + TextField.PADDING.getTop(),
                            CELL_SIZE, "" + game.getCell(row, col));
                    label.setFontSize(FONT_SIZE);
                    label.setTextAlignment(TextAlignment.CENTER);
                    content.addChild(label);
                }
            }
        }
        return content;
    }

    private void updateField(int row, int col, TextField field) {
        try {
            if (field.getText().isEmpty()) {
                game.clearCell(row, col);
            } else {
                var number = Integer.parseInt(field.getText());
                game.setCell(row, col, number);
            }
            field.setTextColor(Control.DEFAULT_TEXT_COLOR);
            field.setBorderColor(TextField.DEFAULT_BORDER_COLOR);
        } catch (IllegalArgumentException e) { // due to parseInt() or setCell()
            game.clearCell(row, col);
            field.setTextColor(ERROR_COLOR);
            field.setBorderColor(ERROR_COLOR);
        }
        checkFinishedCorrect();
    }

    private void checkFinishedCorrect() {
        var finished = game.isFinished();
        background.setFinished(finished);
        if (finished) {
            background.setCorrect(game.isCorrect());
        }
    }

    @Override
    protected void handleEvent(Event e) {
        // custom event handling
        if (e instanceof EventKey keyEvent
                && keyEvent.isPressed() && keyEvent.getKey() == Key.INSERT) {
            insertSolution();
        }

        // need to call super method, which informs controls and
        // performs dirty checking
        super.handleEvent(e);
    }

    private void insertSolution() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (fields[row][col] != null) {
                    fields[row][col].setText("" + SudokuGame.SOLUTION[row][col]);
                }
            }
        }
    }
}
