package by.shift.minesweeper.dto;

import by.shift.minesweeper.model.Cell;

public class GameDto {

    private String id;

    private int rows;

    private int cols;

    private int minesCount;

    private int flagCount;

    private int flaggedMines;

    private Cell[][] board;

    private boolean gameOver;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public void setMinesCount(int minesCount) {
        this.minesCount = minesCount;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public void setFlagCount(int flagCount) {
        this.flagCount = flagCount;
    }

    public int getFlaggedMines() {
        return flaggedMines;
    }

    public void setFlaggedMines(int flaggedMines) {
        this.flaggedMines = flaggedMines;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
