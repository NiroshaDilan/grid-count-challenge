package com.codechallenge;

import java.util.Arrays;

/**
 * @Project grid-count-challenge
 * @Author DILAN on 2/27/2022
 */
public class Main {

    private static final int ROW = 5;
    private static final int COLUMN = 5;
    private static final int NUM_OF_COLORS = 3;

    private static int count;

    private static final int[][] visited = new int[ROW][COLUMN];
    private static int[][] resultGrid = new int[ROW][COLUMN];

    public static void main(String[] args) {
        int[][] colorGrid = createGame();
        getLargestColorBlock(colorGrid);
    }

    private static int[][] createGame() {
        int[][] colorGrid = new int[ROW][COLUMN];
        for (var i = 0; i < ROW; i++) {
            for (var j = 0; j < COLUMN; j++) {
                colorGrid[i][j] = getColorCode();
            }
        }

        for (int[] a : colorGrid) {
            System.out.println(Arrays.toString(a));
        }
        return colorGrid;
    }

    private static int getColorCode() {
        return (int) (1 + Math.random() * NUM_OF_COLORS);
    }

    private static void getLargestColorBlock(int[][] colorGrid) {
        int largestBlock = 0;

            for (var i = 0; i < ROW; i++) {
            for (var j = 0; j < COLUMN; j++) {

                resetVisited();
                count = 0;

                if (j + 1 < COLUMN) {
                    breadthFirstSearch(colorGrid[i][j], colorGrid[i][j + 1], i, j, colorGrid);
                }

                if (count >= largestBlock) {
                    largestBlock = count;
                    resetResult(colorGrid[i][j], colorGrid);
                }

                resetVisited();
                count = 0;

                if (i + 1 < ROW)
                    breadthFirstSearch(colorGrid[i][j], colorGrid[i + 1][j], i, j, colorGrid);

                if (count >= largestBlock) {
                    largestBlock = count;
                    resetResult(colorGrid[i][j], colorGrid);
                }
            }
        }
        printResult(largestBlock);
    }

    private static void resetResult(int key, int[][] colorGrid) {
        for (var i = 0; i < ROW; i++) {
            for (var j = 0; j < COLUMN; j++) {
                if (visited[i][j] == 1 && colorGrid[i][j] == key) {
                    resultGrid[i][j] = visited[i][j];
                } else {
                    resultGrid[i][j] = 0;
                }
            }
        }
    }

    private static void printResult(int result) {
        for (var i = 0; i < ROW; i++) {
            for (var j = 0; j < COLUMN; j++) {
                if (resultGrid[i][j] != 0) {
                    System.out.print(resultGrid[i][j] + " ");
                } else {
                    System.out.print(". ");
                }
                System.out.println();
            }
        }
    }

    private static void breadthFirstSearch(int x, int y, int i, int j, int[][] grid) {
        if (x != y) return;

        visited[i][j] = 1;
        count++;

        int[] xMove = {0, 0, 1, -1};
        int[] yMove = {1, -1, 0, 0};

        for (var k = 0; k < 4; k++) {
            if ((isValid(i + yMove[k], j + xMove[k], x, grid)))
                breadthFirstSearch(x, y, i + yMove[k], j + xMove[k], grid);
        }
    }

    private static boolean isValid(int x, int y, int key, int[][] grid) {
        if (x < ROW && y < COLUMN && x >= 0 && y >= 0) {
            return visited[x][y] == 0 && grid[x][y] == key;
        } else {
            return false;
        }
    }

    private static void resetVisited() {
        for (var i = 0; i < ROW; i++) {
            for (var j = 0; j < COLUMN; j++) {
                visited[i][j] = 0;
            }
        }
    }
}
