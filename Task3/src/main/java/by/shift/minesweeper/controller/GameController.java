package by.shift.minesweeper.controller;

import by.shift.minesweeper.dto.GameDto;
import by.shift.minesweeper.service.GameService;
import by.shift.minesweeper.validator.RequestValidator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game")
    public GameDto startNewGame() {
        return gameService.startNewGame();
    }

    @PostMapping("/game/{id}/reveal")
    public GameDto revealCell(@PathVariable("id") String id, @RequestParam int row, @RequestParam int col) {
        RequestValidator.validateId(id);
        RequestValidator.validateRowAndCol(row, col);
        return gameService.revealCell(id, row, col);
    }

    @PostMapping("/game/{id}/toggle-flag")
    public GameDto toggleFlag(@PathVariable("id") String id, @RequestParam int row, @RequestParam int col) {
        RequestValidator.validateId(id);
        RequestValidator.validateRowAndCol(row, col);
        return gameService.toggleFlag(id, row, col);
    }
}
