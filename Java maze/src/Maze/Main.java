package Maze;

import java.util.Scanner;

//import Maze.MazeEscape;
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("n: ");
        int n = scan.nextInt();
        MazeEscape Dima = new MazeEscape(n);
        Dima.best_maze_escape();
    }
}

//■ ■ ■ □ ■ ■ ■ ■ ■
//■ □ □ □ □ □ □ □ ■
//■ □ □ □ □ □ □ □ ■
//■ □ □ □ □ □ □ □ ■
//■ □ □ □ ◊ □ □ □ ■
//■ □ □ □ □ □ □ □ ■
//■ □ □ □ □ □ □ □ ■
//■ □ □ □ □ □ □ □ ■
//■ ■ ■ ■ ■ ■ ■ ■ ■

//■ ■ ■ □ ■ ■ ■
//■ □ □ □ □ □ ■
//■ □ □ □ □ □ ■
//■ □ □ ◊ □ □ ■
//■ □ □ □ □ □ ■
//■ □ □ □ □ □ ■
//■ ■ ■ ■ ■ ■ ■

//■ ■ □ ■ ■ ■ ■ ■ ■
//■ □ □ □ □ □ □ ■ ■
//■ □ □ □ □ □ □ □ ■
//■ □ □ □ □ □ □ □ ■
//□ □ □ □ ◊ □ □ □ □
//■ □ □ □ □ □ □ □ ■
//■ □ □ □ □ □ □ □ ■
//■ ■ □ □ □ □ □ ■ ■
//■ ■ ■ ■ ■ ■ ■ ■ ■