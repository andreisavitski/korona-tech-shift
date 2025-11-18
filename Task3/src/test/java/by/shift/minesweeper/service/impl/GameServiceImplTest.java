package by.shift.minesweeper.service.impl;

import by.shift.minesweeper.dto.GameDto;
import by.shift.minesweeper.gameplay.BoardInitializer;
import by.shift.minesweeper.gameplay.GameEndHandler;
import by.shift.minesweeper.mapper.GameMapper;
import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    private static final int ROWS = 10;

    private static final int COLS = 10;

    private static final int MINES = 10;

    private static final String GAME_ID = "test";

    @Mock
    private GameRepository gameRepository;

    @Mock
    private BoardInitializer boardInitializer;

    @Mock
    private GameEndHandler gameEndHandler;

    @Mock
    private GameMapper gameMapper;

    @InjectMocks
    private GameServiceImpl gameService;

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(GAME_ID, ROWS, COLS, MINES);
    }

    @Test
    void startNewGameWhenCalledShouldInitializeAndSaveGame() {
        Game newGame = new Game(randomUUID().toString(), ROWS, COLS, MINES);
        when(boardInitializer.initializeBoard(any())).thenReturn(newGame);
        GameDto expected = new GameDto();
        when(gameMapper.toGameDto(newGame)).thenReturn(expected);

        GameDto result = gameService.startNewGame();

        assertThat(result).isEqualTo(expected);
        verify(gameRepository).save(newGame);
    }

    @Test
    void revealCellWhenCellIsMineShouldHandleMineAndUpdateGame() {
        Cell mineCell = createCell(true, false, false);
        game.setCell(0, 0, mineCell);
        when(gameRepository.findById(GAME_ID)).thenReturn(game);
        GameDto expected = new GameDto();
        when(gameMapper.toGameDto(game)).thenReturn(expected);

        GameDto result = gameService.revealCell(GAME_ID, 0, 0);

        verify(gameEndHandler).handleMine(game, mineCell);
        verify(gameRepository).update(game);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void revealCellWhenCellAlreadyRevealedOrFlaggedShouldReturnGameWithoutUpdate() {
        Cell cell = createCell(false, true, false);
        game.setCell(0, 0, cell);
        when(gameRepository.findById(GAME_ID)).thenReturn(game);
        GameDto expected = new GameDto();
        when(gameMapper.toGameDto(game)).thenReturn(expected);

        GameDto result = gameService.revealCell(GAME_ID, 0, 0);

        verify(gameRepository, never()).update(any());
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void toggleFlagWhenCellNotFlaggedShouldFlagCellAndUpdateGame() {
        Cell cell = createCell(false, false, false);
        game.setCell(0, 0, cell);
        game.setFlagCount(MINES);
        when(gameRepository.findById(GAME_ID)).thenReturn(game);
        GameDto expected = new GameDto();
        when(gameMapper.toGameDto(game)).thenReturn(expected);

        GameDto result = gameService.toggleFlag(GAME_ID, 0, 0);

        assertThat(cell.isFlagged()).isTrue();
        assertThat(game.getFlagCount()).isEqualTo(MINES - 1);
        assertThat(game.getFlaggedMines()).isEqualTo(1);
        verify(gameRepository).update(game);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void toggleFlagWhenCellAlreadyFlaggedShouldUnflagCellAndUpdateGame() {
        Cell cell = createCell(false, false, true);
        game.setCell(0, 0, cell);
        game.setFlagCount(MINES - 1);
        game.setFlaggedMines(1);
        when(gameRepository.findById(GAME_ID)).thenReturn(game);
        GameDto expected = new GameDto();
        when(gameMapper.toGameDto(game)).thenReturn(expected);

        GameDto result = gameService.toggleFlag(GAME_ID, 0, 0);

        assertThat(cell.isFlagged()).isFalse();
        assertThat(game.getFlagCount()).isEqualTo(MINES);
        assertThat(game.getFlaggedMines()).isEqualTo(0);
        verify(gameRepository).update(game);
        assertThat(result).isEqualTo(expected);
    }

    private Cell createCell(boolean mine, boolean revealed, boolean flagged) {
        Cell cell = new Cell();
        cell.setMine(mine);
        cell.setRevealed(revealed);
        cell.setFlagged(flagged);
        return cell;
    }
}