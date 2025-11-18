package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardUtilTest {

    private static final int ROWS = 3;

    private static final int COLS = 3;

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("test", ROWS, COLS, 0);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                game.setCell(row, col, new Cell());
            }
        }
    }

    @Test
    void countMinesAroundCellShouldReturnCorrectCount() {
        game.getCell(0, 0).setMine(true);
        game.getCell(0, 1).setMine(true);
        game.getCell(1, 0).setMine(true);

        int count = BoardUtil.countMinesAroundCell(game, 1, 1);
        assertThat(count).isEqualTo(3);

        int countCorner = BoardUtil.countMinesAroundCell(game, 0, 0);
        assertThat(countCorner).isEqualTo(2);
    }

    @Test
    void isInsideBoardShouldReturnTrueForValidCoordinates() {
        assertThat(BoardUtil.isInsideBoard(game, 0, 0)).isTrue();
        assertThat(BoardUtil.isInsideBoard(game, ROWS - 1, COLS - 1)).isTrue();
    }

    @Test
    void isInsideBoardShouldReturnFalseForInvalidCoordinates() {
        assertThat(BoardUtil.isInsideBoard(game, -1, 0)).isFalse();
        assertThat(BoardUtil.isInsideBoard(game, 0, -1)).isFalse();
        assertThat(BoardUtil.isInsideBoard(game, ROWS, 0)).isFalse();
        assertThat(BoardUtil.isInsideBoard(game, 0, COLS)).isFalse();
    }
}