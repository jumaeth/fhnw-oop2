public class Grid {
    public static void main(String[] args) {
        int[][] grid = {
                {4, 1, 0, 4, 5, 7},
                {8, 1, 2, 9, 3, 3},
                {0, 7, 5, 4, 8, 1}};
        print(grid);
    }

    private static void print(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
