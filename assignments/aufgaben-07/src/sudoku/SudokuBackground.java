package sudoku;

import guilib.Control;
import guilib.Node;
import io.github.humbleui.jwm.Event;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.Rect;

import static sudoku.SudokuApp.*;

public class SudokuBackground extends Node {

    private boolean finished = false;
    private boolean correct = false;

    @Override
    protected void paint(Canvas canvas) {
        var min = PADDING;
        var oneThird = PADDING + 3 * CELL_SIZE + 2 * SMALL_SPACE + LARGE_SPACE / 2;
        var twoThirds = TOTAL_SIZE - oneThird;
        var max = TOTAL_SIZE - PADDING;
        float[] coords = {
                min, oneThird, max, oneThird,
                min, twoThirds, max, twoThirds,
                oneThird, min, oneThird, max,
                twoThirds, min, twoThirds, max};
        canvas.drawLines(coords, new Paint()
                .setColor4f(Control.DEFAULT_TEXT_COLOR)
                .setStrokeWidth(3));

        if (finished) {
            var rect = Rect.makeWH(TOTAL_SIZE, TOTAL_SIZE);
            canvas.drawRect(rect, new Paint()
                    .setColor4f(correct ? CORRECT_COLOR : ERROR_COLOR)
                    .setStroke(true)
                    .setStrokeWidth(15));
        }
    }

    @Override
    protected void handleEvent(Event e) {
        // nothing to do
    }

    void setFinished(boolean finished) {
        setDirty();
        this.finished = finished;
    }

    void setCorrect(boolean correct) {
        setDirty();
        this.correct = correct;
    }
}
