package by.shift.task2.io;

import by.shift.task2.validator.DataValidator;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.newBufferedReader;

@UtilityClass
public class DataReader {

    public List<String> readLines(final Path filePath) throws IOException {
        DataValidator.checkingForFileExistence(filePath);
        try (final BufferedReader reader = newBufferedReader(filePath, UTF_8)) {
            return reader.lines()
                    .filter(line -> !line.isBlank())
                    .toList();
        }
    }
}
