package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Game;

final class BoardUtil {

    private BoardUtil() {
    }

    public static int countMinesAroundCell(Game game, int row, int col) {
        int mineCount = 0;
        for (int[] offset : Neighbour.getNeighbours()) {
            int neighbourRow = row + offset[0];
            int neighbourCol = col + offset[1];
            if (isInsideBoard(game, neighbourRow, neighbourCol) && game.getCell(neighbourRow, neighbourCol).isMine()) {
                mineCount++;
            }
        }
        return mineCount;
    }

    public static boolean isInsideBoard(Game game, int row, int col) {
        return row >= 0 && row < game.getRows() && col >= 0 && col < game.getCols();
    }
}
