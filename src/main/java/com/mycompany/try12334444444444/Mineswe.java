/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.try12334444444444;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author YairF
 */
public class Mineswe {

    private int mineCount;
    private int rowCount;
    private int columnCount;
    private int[][] Board;
    private int[][] UpdatedBoard;
    private int tempR;
    private int tempC;

    public Mineswe() {
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int[][] getBoard() {
        return Board;
    }

    public void setBoard(int[][] Board) {
        this.Board = Board;
    }

    public int[][] getUpdatedBoard() {
        return UpdatedBoard;
    }

    public void setUpdatedBoard(int[][] UpdatedBoard) {
        this.UpdatedBoard = UpdatedBoard;
    }

    public int getTempR() {
        return tempR;
    }

    public void setTempR(int tempR) {
        this.tempR = tempR;
    }

    public int getTempC() {
        return tempC;
    }

    public void setTempC(int tempC) {
        this.tempC = tempC;
    }

    /**
     * If one of these cases happens a message will pop up to the user. (1) If
     * there are more bombs than defined he will pop up a message "Too many
     * bombs". (2) If the array size is less than 2x2, then a message will pop
     * up. (3) If the user enters bombs less than zero or zero bombs, he will
     * enter the user an arrival message.
     *
     * @param row - Array lines.
     * @param column - Array columns.
     * @param bombs - The amount of bombs.
     */
    public void Executions(int row, int column, int bombs) {
        if (bombs > (row * column)) {
            throw new IllegalArgumentException("Too many bombs");
        }
        if (row < 2 || column < 2) {
            throw new IllegalArgumentException("Small area");
        }
        if (bombs <= 0) {
            throw new IllegalArgumentException("No bombs");
        }

    }

    /**
     * A function that receives an array size and quantity of bombs, and
     * randomly places the bombs on the array.
     *
     * @param i - Array lines.
     * @param j - Array columns.
     * @param booms - The amount of bombs.
     */
    public void board(int i, int j, int booms) {       //random
        this.rowCount = i;
        this.columnCount = j;
        this.mineCount = booms;
        Random rand = new Random();
        int r;
        int c;
        int boom;
        r = i;
        c = j;
        boom = booms;
        Board = new int[r][c];
        UpdatedBoard = new int[r][c];
        while (boom > 0) {
            r = rand.nextInt(i);
            c = rand.nextInt(j);
            if (Board[r][c] == 10) {
                continue;
            }
            Board[r][c] = 10;
            boom--;

        }

    }

    /**
     * A function that checks the amount of bombs around the received position.
     *
     * @param i - Row position.
     * @param j - Column position.
     * @return - Returns the amount of bombs around the location.
     */
    public int boomAround(int i, int j) {
        int sum;
        int r;
        int c;
        sum = 0;
        r = i;
        c = j;
        if (Board[r][c] == 10) {        //i'm on a bomb?
            sum = -1;
            return sum;
        }
        for (r = i - 1; r <= i + 1; r++) {
            for (c = j - 1; c <= j + 1; c++) {
                if (r < 0 || r >= rowCount || c < 0 || c >= columnCount) {  //The boundary of the array
                    continue;
                }
                if (i == r && j == c) {     //If I'm on my position, skip it.
                    continue;
                }
                if (boom(Board[r][c]) == true) {    //If there's a bomb, sum it up for me.
                    sum++;
                }

            }
        }
        return sum;
    }

    /**
     * If the amount obtained is -1, a bomb went up, and the game was over.
     *
     * @param sum - Amount of bombs.
     * @return - If this is "true", then you have lost.
     */
    public boolean GameOver(int sum) {
        if (sum == -1) {
            return true;
        }
        return false;
    }

    public boolean GameOverWinn() {
        int sumBoom;
        sumBoom = 0;
        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                if (UpdatedBoard[i][j] == 0) {
                    sumBoom++;
                }
            }
        }
        if (sumBoom == mineCount) {
            return true;
        }
        return false;
    }

    /**
     * Will return "true" only if in the field.
     *
     * @param r - A line where you are.
     * @param c - A column you are in.
     * @return
     */
    public boolean InTheField(int r, int c) {

        if ((r >= 0 && r <= rowCount && c >= 0 && c <= columnCount)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     *
     * @param index - Contents of the current location.
     * @return
     */
    public boolean boom(int index) {     //boom
        if (index == 10) {
            return true;
        }
        return false;
    }

    public void OpenAnEmptyLocation(int r, int c) {
        //int tempRow;
        //int tempColumn;
        int sum;
        int i;
        int j;

        if (Board[r][c] != 0) {
            return;
        } else {
            sum = boomAround(r, c);
            UpdatedBoard[r][c] = sum;
            Board[r][c] = sum;

            if (sum == 0) {

                UpdatedBoard[r][c] = 9;
                Board[r][c] = 9;

                for (i = r - 1; i <= r + 1; i++) {
                    for (j = c - 1; j <= c + 1; j++) {
                        if (i < 0 || i >= rowCount || j < 0 || j >= columnCount) {  //The boundary of the array
                            continue;
                        }
                        if (i == r && j == c) {     //If I'm on my position, skip it.
                            continue;
                        }
                        OpenAnEmptyLocation(i, j);
                    }
                }

            }
            return;

        }
    }

    /**
     * Print an array of each step.
     */
    public void printArray() {
        for (int i = 0; i < this.getRowCount(); i++) {
            System.out.print("(" + i + ")  ");
            for (int j = 0; j < this.getColumnCount(); j++) {
                System.out.print(UpdatedBoard[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("---------------------");
    }

    /**
     * If disqualified, prints an up-to-date array including bombs.
     */
    public void printArrayForGameOver() {

        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                System.out.print(Board[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("---------------------");

    }

    /**
     * A function that unifies all functions and executes them.
     */
    public void begin() {
        Mineswe ma;
        ma = new Mineswe();
        Scanner sc;
        sc = new Scanner(System.in);
        int row;
        int column;
        int sum;
        boolean gameOver;
        boolean getBoolOfGame;
        gameOver = false;
        while (gameOver == false) {
            row = sc.nextInt();
            column = sc.nextInt();
            //Executions(row, column, mineCount);
            if (Board[row][column] != 0 && Board[row][column] != 10) {
                System.out.println("Already open, please enter another location");
                continue;
            }
            sum = boomAround(row, column);
            getBoolOfGame = GameOver(sum);
            if (getBoolOfGame == true) {
                System.out.println("! Game over !\n  you lose !!!");
                printArrayForGameOver();
                gameOver = true;
            }

            if (gameOver == false) {
                if (sum == 0) {
                    tempR = row;
                    tempC = column;
                    //sum = 9;
                    OpenAnEmptyLocation(row, column);
                    printArray();
                    if (GameOverWinn() == true) {
                        System.out.println("!! You Winn !!");
                        gameOver = true;
                    }
                    continue;
                }
                UpdatedBoard[row][column] = sum;
                Board[row][column] = sum;
                printArray();
                if (GameOverWinn() == true) {
                    System.out.println("!! You Winn !!");
                    gameOver = true;
                }

            }
        }

    }
}
