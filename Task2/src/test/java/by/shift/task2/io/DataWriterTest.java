package by.shift.task2.io;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static by.shift.task2.constant.ApplicationConstant.CONSOLE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataWriterTest {

    @Test
    void write_consoleOutputProvided_shouldNotThrowApplicationException() {
        assertDoesNotThrow(() -> DataWriter.write("output", CONSOLE));
    }

    @Test
    void write_fileTextProvided_shouldWriteContentToFile() throws Exception {
        Path resultFile = Path.of("result.txt");
        Files.deleteIfExists(resultFile);
        DataWriter.write("test", "file");
        assertEquals("test", Files.readString(resultFile));
        Files.deleteIfExists(resultFile);
    }
}