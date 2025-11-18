package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Queue;

@Component
public class CellRevealer {

    public void revealAreaCell(Game game, int row, int col) {
        Queue<int[]> positionsQueue = new ArrayDeque<>();
        positionsQueue.add(new int[]{row, col});
        while (!positionsQueue.isEmpty()) {
            int[] position = positionsQueue.poll();
            revealCellAndQueueNeighbours(game, position[0], position[1], positionsQueue);
        }
    }

    private void revealCellAndQueueNeighbours(Game game, int row, int col, Queue<int[]> positionsQueue) {
        Cell cell = game.getCell(row, col);
        if (cell.isRevealed() || cell.isMine()) {
            return;
        }
        cell.setRevealed(true);
        cell.setAdjacentMines(BoardUtil.countMinesAroundCell(game, row, col));
        if (cell.getAdjacentMines() == 0) {
            checkNeighbours(game, row, col, positionsQueue);
        }
    }

    private void checkNeighbours(Game game, int row, int col, Queue<int[]> positionsQueue) {
        for (int[] offset : Neighbour.getNeighbours()) {
            int neighbourRow = row + offset[0];
            int neighbourCol = col + offset[1];
            if (!BoardUtil.isInsideBoard(game, neighbourRow, neighbourCol)) {
                continue;
            }
            Cell neighbour = game.getCell(neighbourRow, neighbourCol);
            if (!neighbour.isRevealed() && !neighbour.isMine()) {
                positionsQueue.add(new int[]{neighbourRow, neighbourCol});
            }
        }
    }
}
