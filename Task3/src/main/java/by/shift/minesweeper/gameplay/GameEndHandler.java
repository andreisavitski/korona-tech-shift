package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameEndHandler {

    public void handleMine(Game game, Cell cell) {
        cell.setRevealed(true);
        game.setGameOver(true);
        revealAllMines(game);
    }

    public boolean isWin(Game game) {
        for (Cell cell : getAllCells(game)) {
            if (!cell.isMine() && !cell.isRevealed()) {
                return false;
            }
        }
        return true;
    }

    public void revealAllMines(Game game) {
        for (Cell cell : getAllCells(game)) {
            if (cell.isMine()) {
                cell.setRevealed(true);
            }
        }
    }

    private Iterable<Cell> getAllCells(Game game) {
        List<Cell> cells = new ArrayList<>();
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                cells.add(game.getCell(row, col));
            }
        }
        return cells;
    }
}
