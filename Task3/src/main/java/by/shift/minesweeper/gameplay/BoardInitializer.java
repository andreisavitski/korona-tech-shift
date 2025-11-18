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

    public Game initializeBoard(Game game) {
        createEmptyCells(game);
        setNumberMinesRandomly(game);
        calculateAdjacentMines(game);
        return game;
    }

    private void createEmptyCells(Game game) {
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                game.setCell(row, col, new Cell());
            }
        }
    }

    private void setNumberMinesRandomly(Game game) {
        List<int[]> positions = generateAllPositions(game);
        Collections.shuffle(positions, new Random());
        positions.stream()
                .limit(game.getMinesCount())
                .forEach(position -> game.getCell(position[0], position[1]).setMine(true));
    }

    private List<int[]> generateAllPositions(Game game) {
        List<int[]> positions = new ArrayList<>(game.getRows() * game.getCols());
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                positions.add(new int[]{row, col});
            }
        }
        return positions;
    }

    private void calculateAdjacentMines(Game game) {
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                Cell cell = game.getCell(row, col);
                if (cell.isMine()) {
                    cell.setAdjacentMines(MINE_MARKER);
                    continue;
                }
                int adjacentMineCount = BoardUtil.countMinesAroundCell(game, row, col);
                cell.setAdjacentMines(adjacentMineCount);
            }
        }
    }
}
