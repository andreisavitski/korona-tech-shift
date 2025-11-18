package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameEndHandlerTest {

    private static final int ROWS = 10;

    private static final int COLS = 10;

    private final GameEndHandler gameEndHandler = new GameEndHandler();

    private Game game;

    @BeforeEach
    void setUp() {
        game = createDefaultGame();
    }

    @Test
    void handleMineCellWithMineGameOverAndCellRevealed() {
        Cell mineCell = new Cell();
        mineCell.setMine(true);
        game.setCell(0, 0, mineCell);

        gameEndHandler.handleMine(game, mineCell);

        assertThat(game.isGameOver()).isTrue();
        assertThat(mineCell.isRevealed()).isTrue();
    }

    @Test
    void isWinAllNonMineCellsRevealedReturnsTrue() {
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

        assertThat(gameEndHandler.isWin(game)).isTrue();
        assertThat(mineCell.isRevealed()).isFalse();
    }

    @Test
    void revealAllMinesWhenCalledRevealsOnlyMines() {
        Cell mineCell = new Cell();
        mineCell.setMine(true);
        game.setCell(0, 0, mineCell);

        Cell emptyCell = new Cell();
        game.setCell(0, 1, emptyCell);

        gameEndHandler.revealAllMines(game);

        assertThat(mineCell.isRevealed()).isTrue();
        assertThat(emptyCell.isRevealed()).isFalse();
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