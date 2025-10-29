package by.shift.task2.io;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static by.shift.task2.constant.ApplicationConstant.*;
import static org.junit.jupiter.api.Assertions.*;

class DataWriterTest {

    @Test
    void write_console_shouldPrint() {
        DataWriter writer = new DataWriter();
        assertDoesNotThrow(() -> writer.write("output", CONSOLE));
    }

    @Test
    void write_file_shouldWriteToFile() throws Exception {
        Path resultFile = Path.of("result.txt");
        Files.deleteIfExists(resultFile);
        new DataWriter().write("test", "file");
        assertEquals("test", Files.readString(resultFile));
        Files.deleteIfExists(resultFile);
    }
}