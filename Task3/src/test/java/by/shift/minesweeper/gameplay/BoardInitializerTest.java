package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardInitializerTest {

    private static final int ROWS = 10;

    private static final int COLS = 10;

    private BoardInitializer boardInitializer;

    private Game game;

    @BeforeEach
    void setUp() {
        boardInitializer = new BoardInitializer();
        game = new Game("test", ROWS, COLS, 10);
    }

    @Test
    void initializeBoardWhenCalledCreateBoardWithCorrectSize() {
        boardInitializer.initializeBoard(game);
        assertEquals(ROWS, game.getRows());
        assertEquals(COLS, game.getCols());
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                assertNotNull(game.getCell(row, col));
            }
        }
    }

    @Test
    void countMinesAroundCellCountCorrectly() {
        boardInitializer.initializeBoard(game);
        game.getCell(0, 1).setMine(true);
        game.getCell(1, 0).setMine(true);
        int count = boardInitializer.countMinesAroundCell(game, 0, 0);
        assertEquals(2, count);
    }

    @Test
    void isInsideBoardReturnsCorrectly() {
        assertTrue(boardInitializer.isInsideBoard(game, 0, 0));
        assertTrue(boardInitializer.isInsideBoard(game, ROWS - 1, COLS - 1));
        assertFalse(boardInitializer.isInsideBoard(game, -1, 0));
        assertFalse(boardInitializer.isInsideBoard(game, 0, COLS));
    }
}