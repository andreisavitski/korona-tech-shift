package by.shift.minesweeper.gameplay;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CellRevealerTest {

    private static final int ROWS = 10;

    private static final int COLS = 10;

    @Mock
    private BoardInitializer boardInitializer;

    @InjectMocks
    private CellRevealer cellRevealer;

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("test", ROWS, COLS, 10);
        initBoardCells(game);
    }

    @Test
    void revealAreaCellWhenNoMinesRevealAllCells() {
        when(boardInitializer.countMinesAroundCell(any(Game.class), anyInt(), anyInt())).thenReturn(0);
        when(boardInitializer.isInsideBoard(any(Game.class), anyInt(), anyInt())).thenAnswer(invocation -> {
            int row = invocation.getArgument(1);
            int col = invocation.getArgument(2);
            return row >= 0 && row < ROWS && col >= 0 && col < COLS;
        });
        cellRevealer.revealAreaCell(game, 0, 0);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                assertTrue(game.getCell(row, col).isRevealed());
            }
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