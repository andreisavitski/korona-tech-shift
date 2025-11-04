package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class BoardInitializer {

    private static final int MINE_MARKER = -1;

    public Game initializeBoard(final Game game) {
        createEmptyCells(game);
        setNumberMinesRandomly(game);
        calculateAdjacentMines(game);
        return game;
    }

    public int countMinesAroundCell(final Game game,
                                    final int row,
                                    final int col) {
        int mineCount = 0;
        for (int[] offset : Neighbour.getNeighbours()) {
            final int neighbourRow = row + offset[0];
            final int neighbourCol = col + offset[1];
            if (isInsideBoard(game, neighbourRow, neighbourCol) && game.getCell(neighbourRow, neighbourCol).isMine()) {
                mineCount++;
            }
        }
        return mineCount;
    }

    public boolean isInsideBoard(final Game game,
                                 final int row,
                                 final int col) {
        return row >= 0 && row < game.getRows() && col >= 0 && col < game.getCols();
    }

    private void createEmptyCells(final Game game) {
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                game.setCell(row, col, new Cell());
            }
        }
    }

    private void setNumberMinesRandomly(final Game game) {
        final List<int[]> positions = generateAllPositions(game);
        Collections.shuffle(positions, new Random());
        positions.stream()
                .limit(game.getMinesCount())
                .forEach(position -> game.getCell(position[0], position[1]).setMine(true));
    }

    private List<int[]> generateAllPositions(final Game game) {
        final List<int[]> positions = new ArrayList<>(game.getRows() * game.getCols());
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                positions.add(new int[]{row, col});
            }
        }
        return positions;
    }

    private void calculateAdjacentMines(final Game game) {
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                final Cell cell = game.getCell(row, col);
                if (cell.isMine()) {
                    cell.setAdjacentMines(MINE_MARKER);
                    continue;
                }
                final int adjacentMineCount = countMinesAroundCell(game, row, col);
                cell.setAdjacentMines(adjacentMineCount);
            }
        }
    }
}
