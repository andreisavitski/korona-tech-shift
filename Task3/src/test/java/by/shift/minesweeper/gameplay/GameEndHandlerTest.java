package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEndHandlerTest {

    private static final int ROWS = 10;

    private static final int COLS = 10;

    private GameEndHandler gameEndHandler;

    private Game game;

    @BeforeEach
    void setUp() {
        gameEndHandler = new GameEndHandler();
        game = createDefaultGame();
    }

    @Test
    void handleMineCellWithMineGameOverAndCellRevealed() {
        Cell cell = new Cell();
        cell.setMine(true);
        game.setCell(0, 0, cell);
        gameEndHandler.handleMine(game, cell);
        assertTrue(game.isGameOver());
        assertTrue(cell.isRevealed());
    }

    @Test
    void isWinAllNonMineCellsRevealedReturnTrue() {
        Cell mineCell = new Cell();
        mineCell.setMine(true);
        game.setCell(0, 0, mineCell);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Cell cell = game.getCell(row, col);
                if (!cell.isMine()) {
                    cell.setRevealed(true);
                }
            }
        }
        assertTrue(gameEndHandler.isWin(game));
    }

    @Test
    void revealAllMinesContainsMinesOnlyMinesRevealed() {
        Cell cell = new Cell();
        cell.setMine(true);
        game.setCell(0, 0, cell);
        Cell emptyCell = new Cell();
        game.setCell(0, 1, emptyCell);
        gameEndHandler.revealAllMines(game);
        assertTrue(cell.isRevealed());
        assertFalse(emptyCell.isRevealed());
    }

    private Game createDefaultGame() {
        Game defaultGame = new Game("test", ROWS, COLS, 10);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                defaultGame.setCell(row, col, new Cell());
            }
        }
        return defaultGame;
    }
}