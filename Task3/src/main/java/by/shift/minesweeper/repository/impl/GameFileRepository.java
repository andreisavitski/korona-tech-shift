package by.shift.minesweeper.repository.impl;

import by.shift.minesweeper.exception.ApplicationException;
import by.shift.minesweeper.io.FileWorker;
import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.repository.GameRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class GameFileRepository extends FileWorker implements GameRepository {

    @Value("${games.file.path}")
    private String path;

    private static final String GAME_NOT_FOUND = "Игра не найдена с id: ";

    @Override
    public void save(Game game) {
        List<Game> games = findAll();
        games.add(game);
        serializeObject(games, path);
    }

    @Override
    public Game findById(String id) {
        return findAll().stream()
                .filter(o -> Objects.equals(o.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ApplicationException(GAME_NOT_FOUND + id));
    }

    @Override
    public void update(Game game) {
        List<Game> games = findAll();
        for (int i = 0; i < games.size(); i++) {
            if (Objects.equals(games.get(i).getId(), game.getId())) {
                games.set(i, game);
                serializeObject(games, path);
                return;
            }
        }
        throw new ApplicationException(GAME_NOT_FOUND + game.getId());
    }

    private List<Game> findAll() {
        Object o = deserializeObject(path);
        List<Game> games = new ArrayList<>();
        if (o instanceof List<?>) {
            games = (List<Game>) o;
        }
        return games;
    }
}
