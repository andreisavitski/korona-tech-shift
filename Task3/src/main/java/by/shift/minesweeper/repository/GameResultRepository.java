package by.shift.minesweeper.repository;

import by.shift.minesweeper.model.GameResult;

import java.util.List;

public interface GameResultRepository {

    void save(GameResult gameResult);

    List<GameResult> findAll();
}
