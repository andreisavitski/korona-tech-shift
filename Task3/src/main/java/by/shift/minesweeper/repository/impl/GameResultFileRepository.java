package by.shift.minesweeper.repository.impl;

import by.shift.minesweeper.io.FileWorker;
import by.shift.minesweeper.model.GameResult;
import by.shift.minesweeper.repository.GameResultRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameResultFileRepository extends FileWorker implements GameResultRepository {

    private static final String PATH = "Task3/src/main/resources/dbfile/game-results.txt";

    @Override
    public void save(final GameResult gameResult) {
        final List<GameResult> games = findAll();
        games.add(gameResult);
        serializeObject(games, PATH);
    }

    @Override
    public List<GameResult> findAll() {
        final Object o = deserializeObject(PATH);
        List<GameResult> gameResults = new ArrayList<>();
        if (o instanceof List<?>) {
            gameResults = (List<GameResult>) o;
        }
        return gameResults;
    }
}
