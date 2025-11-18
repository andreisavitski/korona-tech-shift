package by.shift.minesweeper.service;

import by.shift.minesweeper.dto.GameResultDto;

import java.util.List;

public interface GameResultService {

    List<GameResultDto> getResults();

    void saveResult(GameResultDto gameResultDto);
}
