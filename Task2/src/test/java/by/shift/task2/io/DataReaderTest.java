package by.shift.task2.io;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataReaderTest {

    @Test
    void readLines_validFile_shouldReturnLines() throws Exception {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, "CIRCLE\n5");
        DataReader reader = new DataReader();
        List<String> lines = reader.readLines(tempFile);
        assertEquals(2, lines.size());
        assertEquals("CIRCLE", lines.get(0));
        assertEquals("5", lines.get(1));
        Files.deleteIfExists(tempFile);
    }

    @Test
    void readLines_fileNotExist_shouldThrow() {
        Path fakePath = Path.of("nonexist.txt");
        DataReader reader = new DataReader();
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> reader.readLines(fakePath));
        assertTrue(e.getMessage().contains("Файл не найден"));
    }
}