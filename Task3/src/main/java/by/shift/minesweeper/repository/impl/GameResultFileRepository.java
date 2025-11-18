package by.shift.minesweeper.repository.impl;

import by.shift.minesweeper.io.FileWorker;
import by.shift.minesweeper.model.GameResult;
import by.shift.minesweeper.repository.GameResultRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameResultFileRepository extends FileWorker implements GameResultRepository {

    @Value("${game-results.file.path}")
    private String path;

    @Override
    public void save(GameResult gameResult) {
        List<GameResult> games = findAll();
        games.add(gameResult);
        serializeObject(games, path);
    }

    @Override
    public List<GameResult> findAll() {
        Object o = deserializeObject(path);
        List<GameResult> gameResults = new ArrayList<>();
        if (o instanceof List<?>) {
            gameResults = (List<GameResult>) o;
        }
        return gameResults;
    }
}
