package by.shift.task2.io;

import by.shift.task2.exeption.ApplicationException;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderTest {

    private final DataReader reader = new DataReader();

    @Test
    void readLines_validFile_shouldReturnLines() throws Exception {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, "CIRCLE\n5");
        List<String> lines = reader.readLines(tempFile);
        assertEquals(2, lines.size());
        assertEquals("CIRCLE", lines.get(0));
        assertEquals("5", lines.get(1));
        Files.deleteIfExists(tempFile);
    }

    @Test
    void readLines_fileNotExist_shouldThrow() {
        Path fakePath = Path.of("nonexist.txt");
        ApplicationException e = assertThrows(
                ApplicationException.class,
                () -> reader.readLines(fakePath)
        );
        assertTrue(e.getMessage().contains("Файл не найден"));
    }
}
