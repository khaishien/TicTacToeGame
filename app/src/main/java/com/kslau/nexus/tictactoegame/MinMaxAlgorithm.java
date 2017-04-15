package com.kslau.nexus.tictactoegame;

import android.util.Log;

import static com.kslau.nexus.tictactoegame.Board.COLS;
import static com.kslau.nexus.tictactoegame.Board.ROWS;

/**
 * Created by shen-mini-itx on 4/14/2017.
 */

public class MinMaxAlgorithm {

    private static final String TAG = "MinMaxAlgorithm";

    public enum MinMaxState {
        MIN, //look for lowest
        MAX //look for highest
    }

    //formula minmax = e(n) = m(n) - o(n)
    //m(n) as own winning liue
    //o(n) as opponent winning line

    //rules
    //opponent
    //if 2 player in a line + 100
    //if 1  player in a line +10
    //if none player in a line +1

    //ai
    //if 3 ai in a line +100
    //if 2 ai in a line +10
    //if 1 ai in a line +1


    private Cell.Content ai = Cell.Content.O;
    private Cell.Content player = Cell.Content.X;
    private Cell[][] cells;

    private Cell[][] defaultCells;

    private int theMaxLines = -999;
    private int theMaxLinesRow = -1;
    private int theMaxLinesCol = -1;
    private int theLastEmptyRow = -1;
    private int theLastEmptyCol = -1;


    public MinMaxAlgorithm(Cell[][] cells) {
        this.cells = cells;
    }

    public int[] run() {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (cells[i][j].content == Cell.Content.EMPTY) {
                    theLastEmptyRow = i;
                    theLastEmptyCol = j;
                    getMaxPossibleLines(i, j);
                }
            }
        }

        if (theMaxLinesRow == -1 && theMaxLinesCol == -1) {
            theMaxLinesRow = theLastEmptyRow;
            theMaxLinesCol = theLastEmptyCol;
        }
        int[] result = {theMaxLines, theMaxLinesRow, theMaxLinesCol};

        return result;

    }

    public void getMaxPossibleLines(int row, int col) {

        int myLines = 0;
        //check 8 lines
        myLines += getMyPossibleLine(0, 0, 0, 1, 0, 2); //row 0
        myLines += getMyPossibleLine(1, 0, 1, 1, 1, 2); //row 1
        myLines += getMyPossibleLine(2, 0, 2, 1, 2, 2); //row 2

        myLines += getMyPossibleLine(0, 0, 1, 0, 2, 0); //col 0
        myLines += getMyPossibleLine(0, 1, 1, 1, 2, 1); //col 1
        myLines += getMyPossibleLine(0, 2, 1, 2, 2, 2); //col 2

        myLines += getMyPossibleLine(0, 0, 1, 1, 2, 2); //cross
        myLines += getMyPossibleLine(0, 2, 1, 1, 2, 0); //reverse cross

        int oppLines = 0;
        oppLines += getOppPossibleLine(0, 0, 0, 1, 0, 2, row, col); //row 0
        oppLines += getOppPossibleLine(1, 0, 1, 1, 1, 2, row, col); //row 1
        oppLines += getOppPossibleLine(2, 0, 2, 1, 2, 2, row, col); //row 2

        oppLines += getOppPossibleLine(0, 0, 1, 0, 2, 0, row, col); //col 0
        oppLines += getOppPossibleLine(0, 1, 1, 1, 2, 1, row, col); //col 1
        oppLines += getOppPossibleLine(0, 2, 1, 2, 2, 2, row, col); //col 2

        oppLines += getOppPossibleLine(0, 0, 1, 1, 2, 2, row, col); //cross
        oppLines += getOppPossibleLine(0, 2, 1, 1, 2, 0, row, col); //reverse cross

        Log.d(TAG, "myLines: " + myLines);
        Log.d(TAG, "oppLines: " + oppLines);
        Log.d(TAG, "theRow: " + row + ", theCol: " + col);

        int theCase = myLines - oppLines;
        Log.d(TAG, "theCase: " + theCase);

        if (theCase > theMaxLines) {
            theMaxLines = theCase;
            Log.d(TAG, "theMaxLines: " + theMaxLines);
            theMaxLinesRow = row;
            theMaxLinesCol = col;
        }

    }

    private int getOppPossibleLine(int row1, int col1, int row2, int col2, int row3, int col3, int theRow, int theCol) {

        defaultCells = cloneCells(cells);
        defaultCells[theRow][theCol].content = ai;


        if ((defaultCells[row1][col1].content == player) &&
                (defaultCells[row2][col2].content == player) &&
                (defaultCells[row3][col3].content == Cell.Content.EMPTY)) {
            return 100;
        } else if ((defaultCells[row1][col1].content == player) &&
                (defaultCells[row2][col2].content == Cell.Content.EMPTY) &&
                (defaultCells[row3][col3].content == player)) {
            return 100;
        } else if ((defaultCells[row1][col1].content == Cell.Content.EMPTY) &&
                (defaultCells[row2][col2].content == player) &&
                (defaultCells[row3][col3].content == player)) {
            return 100;
        }

        if ((defaultCells[row1][col1].content == player || defaultCells[row1][col1].content == Cell.Content.EMPTY) &&
                (defaultCells[row2][col2].content == player || defaultCells[row2][col2].content == Cell.Content.EMPTY) &&
                (defaultCells[row3][col3].content == player || defaultCells[row3][col3].content == Cell.Content.EMPTY)) {

            if (defaultCells[row1][col1].content == Cell.Content.EMPTY &&
                    defaultCells[row2][col2].content == Cell.Content.EMPTY &&
                    defaultCells[row3][col3].content == Cell.Content.EMPTY) {
                return 1;
            } else {
                return 10;
            }
        }

        if (defaultCells[row1][col1].content == Cell.Content.EMPTY &&
                defaultCells[row2][col2].content == Cell.Content.EMPTY &&
                defaultCells[row3][col3].content == Cell.Content.EMPTY) {
            return 1;
        }

        return 0;
    }

    private int getMyPossibleLine(int row1, int col1, int row2, int col2, int row3, int col3) {


        if ((cells[row1][col1].content == ai) &&
                (cells[row2][col2].content == ai) &&
                (cells[row3][col3].content == ai)) {
            return 100;
        }

        if ((cells[row1][col1].content == ai || cells[row1][col1].content == Cell.Content.EMPTY) &&
                (cells[row2][col2].content == ai || cells[row2][col2].content == Cell.Content.EMPTY) &&
                (cells[row3][col3].content == ai || cells[row3][col3].content == Cell.Content.EMPTY)) {

            if (cells[row1][col1].content == Cell.Content.EMPTY &&
                    cells[row2][col2].content == Cell.Content.EMPTY &&
                    cells[row3][col3].content == Cell.Content.EMPTY) {
                return 1;
            } else {
                return 10;

            }
        }

        if (cells[row1][col1].content == Cell.Content.EMPTY &&
                cells[row2][col2].content == Cell.Content.EMPTY &&
                cells[row3][col3].content == Cell.Content.EMPTY) {
            return 1;
        }

        return 0;

    }

    private Cell[][] cloneCells(Cell[][] cells) {

        Cell[][] newCells = new Cell[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                newCells[i][j] = new Cell(i, j);
                newCells[i][j].content = cells[i][j].content;
            }
        }
        return newCells;
    }

}
