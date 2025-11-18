package by.shift.minesweeper.service.impl;

import by.shift.minesweeper.dto.GameResultDto;
import by.shift.minesweeper.mapper.GameResultMapper;
import by.shift.minesweeper.model.GameResult;
import by.shift.minesweeper.repository.GameResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameResultServiceImplTest {

    @Mock
    private GameResultRepository gameResultRepository;

    @Mock
    private GameResultMapper gameResultMapper;

    @InjectMocks
    private GameResultServiceImpl gameResultService;

    @Test
    void saveResultWhenCalledWithValidDtoShouldCallRepositorySave() {
        GameResultDto dto = new GameResultDto();
        GameResult gameResult = new GameResult();
        when(gameResultMapper.toGameResult(dto)).thenReturn(gameResult);

        gameResultService.saveResult(dto);

        verify(gameResultRepository).save(gameResult);
    }

    @Test
    void getResultsWhenCalledShouldReturnMappedDtoList() {
        GameResult gameResult = new GameResult();
        gameResult.setTimestamp(LocalDateTime.now());
        when(gameResultRepository.findAll()).thenReturn(List.of(gameResult));
        GameResultDto expected = new GameResultDto();
        when(gameResultMapper.toDtoList(anyList())).thenReturn(List.of(expected));

        List<GameResultDto> dtoList = gameResultService.getResults();

        assertThat(dtoList)
                .isNotNull()
                .hasSize(1)
                .containsExactly(expected);
    }
}