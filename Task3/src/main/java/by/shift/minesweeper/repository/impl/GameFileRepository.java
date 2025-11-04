package by.shift.minesweeper.repository.impl;

import by.shift.minesweeper.exception.ApplicationException;
import by.shift.minesweeper.io.FileWorker;
import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.repository.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class GameFileRepository extends FileWorker implements GameRepository {

    private static final String PATH = "Task3/src/main/resources/dbfile/games.txt";

    private static final String GAME_NOT_FOUND = "Игра не найдена с id: ";

    @Override
    public void save(final Game game) {
        final List<Game> games = findAll();
        games.add(game);
        serializeObject(games, PATH);
    }

    @Override
    public Game findById(final String id) {
        return findAll().stream()
                .filter(o -> Objects.equals(o.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ApplicationException(GAME_NOT_FOUND + id));
    }

    @Override
    public void update(final Game game) {
        final List<Game> games = findAll();
        for (int i = 0; i < games.size(); i++) {
            if (Objects.equals(games.get(i).getId(), game.getId())) {
                games.set(i, game);
                serializeObject(games, PATH);
                return;
            }
        }
        throw new ApplicationException(GAME_NOT_FOUND + game.getId());
    }

    private List<Game> findAll() {
        final Object o = deserializeObject(PATH);
        List<Game> games = new ArrayList<>();
        if (o instanceof List<?>) {
            games = (List<Game>) o;
        }
        return games;
    }
}
