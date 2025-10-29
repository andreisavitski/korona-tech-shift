package by.shift.task2.validator;

import by.shift.task2.exeption.ApplicationException;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataValidatorTest {

    @Test
    void checkIsEmpty_emptyList_throwsException() {
        assertThrows(ApplicationException.class,
                () -> DataValidator.checkIsEmpty(List.of()));
    }

    @Test
    void checkIsEmpty_nonEmptyList_noException() {
        assertDoesNotThrow(() -> DataValidator.checkIsEmpty(List.of("line1")));
    }

    @Test
    void checkingForFileExistence_existingFile_noException() throws Exception {
        Path tempFile = Files.createTempFile("exist", ".txt");
        assertDoesNotThrow(() -> DataValidator.checkingForFileExistence(tempFile));
        Files.deleteIfExists(tempFile);
    }

    @Test
    void checkingForFileExistence_nonExistingFile_throwsException() {
        Path fakePath = Path.of("notfound.txt");
        ApplicationException e = assertThrows(ApplicationException.class,
                () -> DataValidator.checkingForFileExistence(fakePath));
        assertTrue(e.getMessage().contains("Файл не найден"));
    }
}
