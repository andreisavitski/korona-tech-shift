package by.shift.minesweeper.repository.impl;

import by.shift.minesweeper.model.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameResultFileRepositoryTest {

    @Spy
    private GameResultFileRepository fileRepository;

    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        gameResult = new GameResult();
    }

    @Test
    void saveGivenGameResultShouldSerializeObject() {
        mockSerializeDoNothing();
        fileRepository.save(gameResult);
        verify(fileRepository).serializeObject(anyList(), anyString());
    }

    @Test
    void findAllShouldReturnListOfGameResults() {
        mockDeserializeToReturnTestResult();
        List<GameResult> results = fileRepository.findAll();
        assertEquals(1, results.size());
        assertEquals(gameResult, results.get(0));
    }

    private void mockSerializeDoNothing() {
        doNothing().when(fileRepository).serializeObject(anyList(), anyString());
    }

    private void mockDeserializeToReturnTestResult() {
        doReturn(List.of(gameResult)).when(fileRepository).deserializeObject(anyString());
    }
}