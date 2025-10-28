package by.shift.task2.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static by.shift.task2.constant.ApplicationConstant.FILE_NOT_FOUND;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.exists;
import static java.nio.file.Files.newBufferedReader;

public class DataReader {

    public List<String> readLines(final Path filePath) throws IOException {
        if (!exists(filePath)) {
            throw new IllegalArgumentException(FILE_NOT_FOUND + filePath);
        }
        try (final BufferedReader reader = newBufferedReader(filePath, UTF_8)) {
            return reader.lines()
                    .filter(line -> !line.isBlank())
                    .toList();
        }
    }
}
