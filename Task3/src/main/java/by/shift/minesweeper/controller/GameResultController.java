package by.shift.minesweeper.controller;

import by.shift.minesweeper.dto.GameResultDto;
import by.shift.minesweeper.service.GameResultService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class GameResultController {

    private final GameResultService gameResultService;

    public GameResultController(GameResultService gameResultService) {
        this.gameResultService = gameResultService;
    }

    @GetMapping
    public List<GameResultDto> getResults() {
        return gameResultService.getResults();
    }

    @PostMapping
    public void saveResult(@RequestBody @Valid GameResultDto result) {
        gameResultService.saveResult(result);
    }
}
