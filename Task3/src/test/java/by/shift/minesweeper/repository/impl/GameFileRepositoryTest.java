package by.shift.minesweeper.repository.impl;

import by.shift.minesweeper.exception.ApplicationException;
import by.shift.minesweeper.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameFileRepositoryTest {

    private static final String GAME_ID = "test";

    @Spy
    private GameFileRepository fileRepository;

    private List<Game> games;

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(GAME_ID, 10, 10, 10);
        games = new ArrayList<>();
        games.add(game);
    }

    @Test
    void saveGivenValidGameShouldSerializeObject() {
        mockDeserializeToReturnGames();
        mockSerializeDoNothing();
        fileRepository.save(game);
        verify(fileRepository, times(1)).serializeObject(any(), anyString());
    }

    @Test
    void findByIdGivenExistingGameShouldReturnGame() {
        mockDeserializeToReturnGames();
        Game result = fileRepository.findById(GAME_ID);
        assertNotNull(result);
        assertEquals(GAME_ID, result.getId());
    }

    @Test
    void findByIdGivenNonExistingGameShouldThrowApplicationException() {
        mockDeserializeToReturnGames();
        assertThrows(ApplicationException.class, () -> fileRepository.findById("unknown"));
    }

    @Test
    void updateGivenExistingGameShouldSerializeUpdateList() {
        mockDeserializeToReturnGames();
        mockSerializeDoNothing();
        fileRepository.update(game);
        verify(fileRepository, times(1)).serializeObject(any(), anyString());
    }

    @Test
    void updateGivenNonExistingGameShouldThrowException() {
        doReturn(new ArrayList<>()).when(fileRepository).deserializeObject(anyString());
        assertThrows(ApplicationException.class, () -> fileRepository.update(game));
    }

    private void mockDeserializeToReturnGames() {
        doReturn(games).when(fileRepository).deserializeObject(anyString());
    }

    private void mockSerializeDoNothing() {
        doNothing().when(fileRepository).serializeObject(any(), anyString());
    }
}