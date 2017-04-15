package com.kslau.nexus.tictactoegame;

/**
 * Created by shen-mini-itx on 4/15/2017.
 */

public class Board {

    public static final int ROWS = 3;
    public static final int COLS = 3;

    public Cell[][] cells;
    public int currentRow;
    public int currentCol;

    public Board() {
        cells = new Cell[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void init() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cells[i][j].clear();
            }
        }
    }

    public void updatePosition(Cell.Content content) {
        cells[currentRow][currentCol].content = content;
    }

    public boolean isDraw() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (cells[i][j].content == Cell.Content.EMPTY)
                    return false;
            }
        }

        return true;
    }

    public boolean isWon(Cell.Content content) {
        return (cells[currentRow][0].content == content         // 3-in-the-row
                && cells[currentRow][1].content == content
                && cells[currentRow][2].content == content
                || cells[0][currentCol].content == content      // 3-in-the-column
                && cells[1][currentCol].content == content
                && cells[2][currentCol].content == content
                || currentRow == currentCol            // 3-in-the-diagonal
                && cells[0][0].content == content
                && cells[1][1].content == content
                && cells[2][2].content == content
                || currentRow + currentCol == 2    // 3-in-the-opposite-diagonal
                && cells[0][2].content == content
                && cells[1][1].content == content
                && cells[2][0].content == content);
    }
}
