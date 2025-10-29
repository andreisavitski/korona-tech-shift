package by.shift.task2.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static by.shift.task2.validator.DataValidator.*;
import static java.nio.charset.StandardCharsets.*;
import static java.nio.file.Files.*;

public class DataReader {

    public List<String> readLines(final Path filePath) throws IOException {
        checkingForFileExistence(filePath);
        try (final BufferedReader reader = newBufferedReader(filePath, UTF_8)) {
            return reader.lines()
                    .filter(line -> !line.isBlank())
                    .toList();
        }
    }
}
