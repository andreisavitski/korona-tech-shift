package by.shift.task2.validator;

import by.shift.task2.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataValidatorTest {

    private static final String INSUFFICIENT_DATA = "Недостаточно данных";

    private static final String FILE_NOT_FOUND = "Файл не найден по пути: ";

    @Test
    void checkIsEmpty_emptyList_shouldThrowApplicationException() {
        List<String> lines = List.of();
        ApplicationException ex = assertThrows(ApplicationException.class,
                () -> DataValidator.checkIsEmpty(lines));
        assertEquals(INSUFFICIENT_DATA, ex.getMessage());
    }

    @Test
    void checkIsEmpty_singleElementList_shouldThrowApplicationException() {
        List<String> lines = List.of("CIRCLE");
        ApplicationException ex = assertThrows(ApplicationException.class,
                () -> DataValidator.checkIsEmpty(lines));
        assertEquals(INSUFFICIENT_DATA, ex.getMessage());
    }

    @Test
    void checkingForFileExistence_existingFile_shouldNotThrowApplicationException() throws Exception {
        Path tempFile = Files.createTempFile("exist", ".txt");
        assertDoesNotThrow(() -> DataValidator.checkingForFileExistence(tempFile));
        Files.deleteIfExists(tempFile);
    }

    @Test
    void checkingForFileExistence_nonExistingFile_shouldThrowApplicationException() {
        Path path = Path.of("notfound.txt");
        ApplicationException ex = assertThrows(ApplicationException.class,
                () -> DataValidator.checkingForFileExistence(path));
        assertTrue(ex.getMessage().contains(FILE_NOT_FOUND));
    }
}