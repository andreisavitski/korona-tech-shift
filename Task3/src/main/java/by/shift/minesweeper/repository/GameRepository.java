package by.shift.minesweeper.repository;

import by.shift.minesweeper.model.Game;

public interface GameRepository {

    void save(Game game);

    Game findById(String id);

    void update(Game game);
}
