package by.shift.minesweeper.repository.impl;

import by.shift.minesweeper.model.GameResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameResultFileRepositoryTest {

    @Spy
    private GameResultFileRepository fileRepository;

    private final GameResult gameResult = new GameResult();

    @Test
    void saveGivenGameResultShouldSerializeObject() {
        doReturn(new ArrayList<>()).when(fileRepository).findAll();
        doNothing().when(fileRepository).serializeObject(any(), any());

        fileRepository.save(gameResult);

        verify(fileRepository).serializeObject(any(), any());
    }

    @Test
    void findAllShouldReturnListOfGameResults() {
        doReturn(List.of(gameResult)).when(fileRepository).deserializeObject(any());

        List<GameResult> results = fileRepository.findAll();

        assertThat(results)
                .isNotNull()
                .hasSize(1)
                .containsExactly(gameResult);
    }
}