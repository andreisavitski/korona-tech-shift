package by.shift.minesweeper.service.impl;

import by.shift.minesweeper.dto.GameResultDto;
import by.shift.minesweeper.mapper.GameResultMapper;
import by.shift.minesweeper.model.GameResult;
import by.shift.minesweeper.repository.GameResultRepository;
import by.shift.minesweeper.service.GameResultService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GameResultServiceImpl implements GameResultService {

    private final GameResultRepository gameResultRepository;

    private final GameResultMapper gameResultMapper;

    public GameResultServiceImpl(GameResultRepository gameResultRepository, GameResultMapper gameResultMapper) {
        this.gameResultRepository = gameResultRepository;
        this.gameResultMapper = gameResultMapper;
    }

    @Override
    public void saveResult(final GameResultDto gameResultDto) {
        gameResultRepository.save(gameResultMapper.toGameResult(gameResultDto));
    }

    @Override
    public List<GameResultDto> getResults() {
        return gameResultMapper.toDtoList(gameResultRepository.findAll().stream()
                .sorted(Comparator.comparing(GameResult::getTimestamp).reversed())
                .toList());
    }
}
