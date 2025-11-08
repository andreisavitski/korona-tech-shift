package by.shift.task2.io;

import by.shift.task2.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataReaderTest {

    private static final String ERROR_READING = "Ошибка чтения из файла :";

    private static final String FILE_NOT_FOUND = "Файл не найден по пути: ";

    @Test
    void readLines_validFile_shouldReturnLines() throws Exception {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, "CIRCLE\n5");
        List<String> lines = DataReader.readLines(tempFile);
        assertEquals(2, lines.size());
        assertEquals("CIRCLE", lines.get(0));
        assertEquals("5", lines.get(1));
        Files.deleteIfExists(tempFile);
    }

    @Test
    void readLines_fileNotExist_shouldThrowApplicationException() {
        Path path = Path.of("test.txt");
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> DataReader.readLines(path)
        );
        assertTrue(exception.getMessage().contains(FILE_NOT_FOUND));
        assertTrue(exception.getMessage().contains("test.txt"));
    }

    @Test
    void readLines_ioException_shouldThrowApplicationException() throws Exception {
        Path dir = Files.createTempDirectory("test");
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> DataReader.readLines(dir)
        );
        assertTrue(exception.getMessage().contains(ERROR_READING));
        assertTrue(exception.getMessage().contains(dir.toString()));
        Files.deleteIfExists(dir);
    }
}