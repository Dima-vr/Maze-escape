package Maze;

import java.util.Random;
import java.util.Scanner;

public class MazeEscape {
    private final String[][] maze;
    private final int n;

    public MazeEscape(int n) {
        this.n = n;
        this.maze = new String[n][n];
        input_maze();
        //fill_maze();
    }

    void print_maze() {
        for (String[] row : maze) {
            for (String col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void input_maze() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the elements of the matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = String.valueOf(in.next().charAt(0));
            }
        }
    }

    public void make_escape(int row, int col, String[][] maze, int n) {
        Random random = new Random();
        int[] direction_row = {-1, 0, 1, 0};
        int[] direction_col = {0, 1, 0, -1};
        maze[row][col] = "□";
        if ((row == 0 || col == 0) || (row == n - 1 || col == n - 1)) {
            return;
        }
        int x = random.nextInt(4);
        int next_row = row + direction_row[x];
        int next_col = col + direction_col[x];
        make_escape(next_row, next_col, maze, n);
    }

    private void fill_maze() {
        Random random = new Random();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                maze[row][col] = "■";
            }
        }
        make_escape(n / 2, n / 2, maze, n);

//        System.out.println("Created exit:");
//        print_maze();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (!((row == 0 || col == 0) || (row == n - 1 || col == n - 1)) && !maze[row][col].equals("□")) {

                    maze[row][col] = random.nextInt(2) == 1 ? "■" : "□";
                }
            }
        }
        //Start point
        maze[n / 2][n / 2] = "◊";


        //Final maze to use
        System.out.println("Maze:");
        print_maze();
    }


    public void backtrack(int row, int col, String[][] maze, int[] direction_row, int[] direction_col, int[] count_moves, int num) {
        maze[row][col] = "◊";
        if ((row == 0 || col == 0) || (row == n - 1 || col == n - 1)) {
            count_moves[num] = count_of_moves(maze);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int next_row = row + direction_row[i];
            int next_col = col + direction_col[i];
            if (maze[next_row][next_col].equals("□")) {
                backtrack(next_row, next_col, maze, direction_row, direction_col, count_moves, num);
            }
        }
        maze[row][col] = "□";
    }

    public void print_best_escape(int row, int col, String[][] maze, int[] direction_row, int[] direction_col) {
        maze[row][col] = "◊";
        if ((row == 0 || col == 0) || (row == maze.length - 1 || col == maze[row].length - 1)) {
            print_maze();
            return;
        }
        for (int i = 0; i < 4; i++) {
            int next_row = row + direction_row[i];
            int next_col = col + direction_col[i];
            if (maze[next_row][next_col].equals("□")) {
                print_best_escape(next_row, next_col, maze, direction_row, direction_col);
            }
        }
        maze[row][col] = "□";
    }

    public String[][] copied_maze(String[][] maze) {
        String[][] tmp_maze = new String[maze.length][];
        for (int i = 0; i < maze.length; i++)
            tmp_maze[i] = maze[i].clone();
        return tmp_maze;
    }

    public int count_of_moves(String[][] maze) {
        int count = 0;
        for (String[] row : maze) {
            for (String col : row) {
                if (col.equals("◊")) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    public void best_maze_escape() {
        String[][] tmp_maze;
        int[] count_moves = new int[8];
        int[][] direction_row = new int[8][4];
        int[][] direction_col = new int[8][4];

        direction_row[0] = new int[]{-1, 0, 1, 0};
        direction_col[0] = new int[]{0, 1, 0, -1};

        direction_row[1] = new int[]{-1, 0, 1, 0};
        direction_col[1] = new int[]{0, -1, 0, 1};

        direction_row[2] = new int[]{1, 0, -1, 0};
        direction_col[2] = new int[]{0, 1, 0, -1};

        direction_row[3] = new int[]{1, 0, -1, 0};
        direction_col[3] = new int[]{0, -1, 0, 1};

        direction_row[4] = new int[]{0, 1, 0, -1};
        direction_col[4] = new int[]{1, 0, -1, 0};

        direction_row[5] = new int[]{0, 1, 0, -1};
        direction_col[5] = new int[]{1, 0, -1, 0};

        direction_row[6] = new int[]{0, -1, 0, 1};
        direction_col[6] = new int[]{1, 0, -1, 0};

        direction_row[7] = new int[]{0, -1, 0, 1};
        direction_col[7] = new int[]{-1, 0, 1, 0};

        for (int i = 0; i < 8; i++) {
            tmp_maze = copied_maze(maze);
            backtrack(n / 2, n / 2, tmp_maze, direction_row[i], direction_col[i], count_moves, i);
        }
        int min_moves = count_moves[0];
        int max_moves = count_moves[0];
        int best = 0;
        for (int i = 0; i < 8; i++) {
            if (min_moves > count_moves[i]) {
                min_moves = count_moves[i];
                best = i;
            }
            if (max_moves < count_moves[i]) {
                max_moves = count_moves[i];
            }
        }
        if (max_moves == 0) {
            System.out.println("There is no way out");
            System.exit(1);
        }
        System.out.println("'Best' escape:");
        print_best_escape(n / 2, n / 2, maze, direction_row[best], direction_col[best]);
    }
}
