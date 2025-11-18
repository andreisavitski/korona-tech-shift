package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CellRevealerTest {

    private static final int ROWS = 10;

    private static final int COLS = 10;

    private final CellRevealer cellRevealer = new CellRevealer();

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("test", ROWS, COLS, 10);
        initBoardCells(game);
    }

    @Test
    void revealAreaCellWhenNoMinesRevealAllCells() {
        cellRevealer.revealAreaCell(game, 0, 0);

        Cell[][] board = game.getBoard();
        for (Cell[] row : board) {
            assertThat(row).allMatch(Cell::isRevealed);
        }
    }

    private void initBoardCells(Game game) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                game.setCell(row, col, new Cell());
            }
        }
    }
}