package com.kslau.nexus.tictactoegame;

/**
 * Created by shen-mini-itx on 4/14/2017.
 */

public class Cell {

    public enum Content {
        X, O, EMPTY
    }

    private int row;
    private int col;
    public Content content;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        clear();
    }

    public void clear() {
        content = Content.EMPTY;
    }
}
