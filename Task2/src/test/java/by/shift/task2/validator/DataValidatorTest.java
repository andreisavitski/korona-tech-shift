package by.shift.task2.validator;

import by.shift.task2.exeption.ApplicationException;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static by.shift.task2.exeption.ApplicationError.INSUFFICIENT_DATA;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataValidatorTest {

    @Test
    void checkIsEmpty_emptyList_shouldThrow() {
        List<String> lines = List.of();
        ApplicationException exception = assertThrows(ApplicationException.class,
                () -> DataValidator.checkIsEmpty(lines));
        assertEquals(INSUFFICIENT_DATA.getMessage(), exception.getMessage());
    }

    @Test
    void checkIsEmpty_singleElementList_shouldThrow() {
        List<String> lines = List.of("CIRCLE");
        ApplicationException exception = assertThrows(ApplicationException.class,
                () -> DataValidator.checkIsEmpty(lines));
        assertEquals(INSUFFICIENT_DATA.getMessage(), exception.getMessage());
    }

    @Test
    void checkingForFileExistence_existingFile_shouldNotThrow() throws Exception {
        Path tempFile = Files.createTempFile("exist", ".txt");
        assertDoesNotThrow(() -> DataValidator.checkingForFileExistence(tempFile));
        Files.deleteIfExists(tempFile);
    }

    @Test
    void checkingForFileExistence_nonExistingFile_shouldThrow() {
        Path fakePath = Path.of("notfound.txt");
        ApplicationException e = assertThrows(ApplicationException.class,
                () -> DataValidator.checkingForFileExistence(fakePath));
        assertTrue(e.getMessage().contains("Файл не найден"));
    }
}
