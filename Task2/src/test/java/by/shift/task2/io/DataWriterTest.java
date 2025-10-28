package by.shift.task2.io;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static by.shift.task2.constant.ApplicationConstant.CONSOLE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataWriterTest {

    @Test
    void write_console_shouldPrint() {
        DataWriter writer = new DataWriter();
        assertDoesNotThrow(() -> writer.write("test output", CONSOLE));
    }

    @Test
    void write_file_shouldWriteToFile() throws Exception {
        Path tempFile = Files.createTempFile("output", ".txt");
        DataWriter writer = new DataWriter();
        writer.write("test file content", tempFile.toString());
        String content = Files.readString(tempFile);
        assertEquals("test file content", content);
        Files.deleteIfExists(tempFile);
    }
}