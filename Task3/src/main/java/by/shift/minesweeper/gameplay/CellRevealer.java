package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Queue;

@Component
public class CellRevealer {

    private final BoardInitializer boardInitializer;

    public CellRevealer(BoardInitializer boardInitializer) {
        this.boardInitializer = boardInitializer;
    }

    public void revealAreaCell(final Game game,
                               final int row,
                               final int col) {
        final Queue<int[]> positionsQueue = new ArrayDeque<>();
        positionsQueue.add(new int[]{row, col});
        while (!positionsQueue.isEmpty()) {
            final int[] position = positionsQueue.poll();
            revealCellAndQueueNeighbours(game, position[0], position[1], positionsQueue);
        }
    }

    private void revealCellAndQueueNeighbours(final Game game,
                                              final int row,
                                              final int col,
                                              final Queue<int[]> positionsQueue) {
        final Cell cell = game.getCell(row, col);
        if (cell.isRevealed() || cell.isMine()) return;
        cell.setRevealed(true);
        cell.setAdjacentMines(boardInitializer.countMinesAroundCell(game, row, col));
        if (cell.getAdjacentMines() == 0) {
            checkNeighbours(game, row, col, positionsQueue);
        }
    }

    private void checkNeighbours(final Game game,
                                 final int row,
                                 final int col,
                                 final Queue<int[]> positionsQueue) {
        for (int[] offset : Neighbour.getNeighbours()) {
            final int neighbourRow = row + offset[0];
            final int neighbourCol = col + offset[1];
            if (!boardInitializer.isInsideBoard(game, neighbourRow, neighbourCol)) continue;
            final Cell neighbour = game.getCell(neighbourRow, neighbourCol);
            if (!neighbour.isRevealed() && !neighbour.isMine()) {
                positionsQueue.add(new int[]{neighbourRow, neighbourCol});
            }
        }
    }
}
