package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardInitializerTest {

    private static final int ROWS = 10;

    private static final int COLS = 10;

    private final BoardInitializer boardInitializer = new BoardInitializer();

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("test", ROWS, COLS, 10);
    }

    @Test
    void initializeBoardCreatesBoardWithCorrectSize() {
        boardInitializer.initializeBoard(game);

        assertThat(game.getRows()).isEqualTo(ROWS);
        assertThat(game.getCols()).isEqualTo(COLS);

        Cell[][] board = game.getBoard();
        assertThat(board).isNotNull().hasDimensions(ROWS, COLS);

        for (Cell[] row : board) {
            assertThat(row).doesNotContainNull();
        }
    }

    @Test
    void countMinesAroundCellCountCorrectly() {
        boardInitializer.initializeBoard(game);
        game.getCell(0, 1).setMine(true);
        game.getCell(1, 0).setMine(true);

        int count = BoardUtil.countMinesAroundCell(game, 0, 0);
        assertThat(count).isEqualTo(2);
    }

    @Test
    void isInsideBoardReturnsCorrectly() {
        assertThat(BoardUtil.isInsideBoard(game, 0, 0)).isTrue();
        assertThat(BoardUtil.isInsideBoard(game, ROWS - 1, COLS - 1)).isTrue();
        assertThat(BoardUtil.isInsideBoard(game, -1, 0)).isFalse();
        assertThat(BoardUtil.isInsideBoard(game, 0, COLS)).isFalse();
    }
}