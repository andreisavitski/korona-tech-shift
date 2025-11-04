package by.shift.minesweeper.service;

import by.shift.minesweeper.dto.GameDto;

public interface GameService {

    GameDto startNewGame();

    GameDto revealCell(String id, int row, int col);

    GameDto toggleFlag(String id, int row, int col);
}
