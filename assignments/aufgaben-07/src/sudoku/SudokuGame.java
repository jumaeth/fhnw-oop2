package sudoku;

public class SudokuGame {

    public static final int[][] SOLUTION = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}};

    private static final int E = 0; // empty cell

    private final int[][] cells = {
            {5, 3, E, E, 7, E, E, E, E},
            {6, E, E, 1, 9, 5, E, E, E},
            {E, 9, 8, E, E, E, E, 6, E},
            {8, E, E, E, 6, E, E, E, 3},
            {4, E, E, 8, E, 3, E, E, 1},
            {7, E, E, E, 2, E, E, E, 6},
            {E, 6, E, E, E, E, 2, 8, E},
            {E, E, E, 4, 1, 9, E, E, 5},
            {E, E, E, E, 8, E, E, 7, 9}};

    public int getCell(int row, int col) {
        return cells[row][col];
    }

    public boolean isCellEmpty(int row, int col) {
        return cells[row][col] == E;
    }

    public void setCell(int row, int col, int number) {
        if (number < 1 || number > 9) {
            throw new IllegalArgumentException();
        }
        cells[row][col] = number;
    }

    public void clearCell(int row, int col) {
        cells[row][col] = E;
    }

    public boolean isFinished() {
        for (int row = 0; row < 9; row++) {
            for (int n : cells[row]) {
                if (n == E) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCorrect() {
        boolean correct = rowsCorrect() && colsCorrect();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                correct &= squareCorrect(i, j);
            }
        }
        return correct;
    }

    private boolean rowsCorrect() {
        for (int row = 0; row < 9; row++) {
            var seen = new boolean[9];
            for (int n : cells[row]) {
                if (n == E || seen[n - 1]) {
                    return false;
                }
                seen[n - 1] = true;
            }
        }
        return true;
    }

    private boolean colsCorrect() {
        for (int col = 0; col < 9; col++) {
            var seen = new boolean[9];
            for (int row = 0; row < 9; row++) {
                int n = cells[row][col];
                if (n == E || seen[n - 1]) {
                    return false;
                }
                seen[n - 1] = true;
            }
        }
        return true;
    }

    private boolean squareCorrect(int i, int j) {
        var seen = new boolean[9];
        for (int row = i * 3; row < (i + 1) * 3; row++) {
            for (int col = j * 3; col < (j + 1) * 3; col++) {
                int n = cells[row][col];
                if (n == E || seen[n - 1]) {
                    return false;
                }
                seen[n - 1] = true;
            }
        }
        return true;
    }
}