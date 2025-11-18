package by.shift.minesweeper.service.impl;

import by.shift.minesweeper.dto.GameDto;
import by.shift.minesweeper.gameplay.BoardInitializer;
import by.shift.minesweeper.gameplay.CellRevealer;
import by.shift.minesweeper.gameplay.GameEndHandler;
import by.shift.minesweeper.mapper.GameMapper;
import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.repository.GameRepository;
import by.shift.minesweeper.service.GameService;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
public class GameServiceImpl implements GameService {

    private static final int NUMBER_OF_ROWS = 10;

    private static final int NUMBER_OF_COLUMNS = 10;

    private static final int NUMBER_OF_MINES = 10;

    private final GameRepository gameRepository;

    private final GameMapper gameMapper;

    private final BoardInitializer boardInitializer;

    private final GameEndHandler gameEndHandler;

    private final CellRevealer cellRevealer;

    public GameServiceImpl(GameRepository gameRepository,
                           GameMapper gameMapper,
                           BoardInitializer boardInitializer,
                           GameEndHandler gameEndHandler,
                           CellRevealer cellRevealer) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.boardInitializer = boardInitializer;
        this.gameEndHandler = gameEndHandler;
        this.cellRevealer = cellRevealer;
    }

    @Override
    public GameDto startNewGame() {
        Game game = boardInitializer.initializeBoard(
                new Game(randomUUID().toString(), NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, NUMBER_OF_MINES)
        );
        gameRepository.save(game);
        return gameMapper.toGameDto(game);
    }

    @Override
    public GameDto revealCell(String id, int row, int col) {
        Game game = gameRepository.findById(id);
        if (game.isGameOver()) {
            return gameMapper.toGameDto(game);
        }

        Cell cell = game.getCell(row, col);
        if (cell.isFlagged() || cell.isRevealed()) {
            return gameMapper.toGameDto(game);
        }
        if (cell.isMine()) {
            gameEndHandler.handleMine(game, cell);
        } else {
            cellRevealer.revealAreaCell(game, row, col);
            if (gameEndHandler.isWin(game)) {
                game.setFlaggedMines(game.getMinesCount());
                gameEndHandler.revealAllMines(game);
            }
        }

        gameRepository.update(game);
        return gameMapper.toGameDto(game);
    }

    @Override
    public GameDto toggleFlag(String id, int row, int col) {
        Game game = gameRepository.findById(id);
        Cell cell = game.getCell(row, col);

        boolean currentlyFlagged = cell.isFlagged();
        boolean canPlaceFlag = !currentlyFlagged && game.getFlagCount() > 0;

        int adjustment = canPlaceFlag ? 1 : 0;
        int newFlagCount = currentlyFlagged
                ? game.getFlagCount() + 1
                : game.getFlagCount() - adjustment;

        int newFlaggedMines = currentlyFlagged
                ? game.getFlaggedMines() - 1
                : game.getFlaggedMines() + adjustment;

        boolean newFlagged = !currentlyFlagged && canPlaceFlag;

        cell.setFlagged(newFlagged);
        game.setFlagCount(newFlagCount);
        game.setFlaggedMines(newFlaggedMines);

        gameRepository.update(game);
        return gameMapper.toGameDto(game);
    }
}
