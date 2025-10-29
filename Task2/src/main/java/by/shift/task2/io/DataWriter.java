package by.shift.task2.io;

import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

import static by.shift.task2.constant.ApplicationConstant.*;
import static java.nio.charset.StandardCharsets.*;
import static java.nio.file.Files.*;
import static org.slf4j.LoggerFactory.*;

public class DataWriter {

    private static final String RESULT_IS_WRITTEN_TO_FILE = "Результат записан в файл: {}";

    private static final String RESULT_IS_WRITTEN_TO_CONSOLE = "Результат записан в консоль";

    private static final Logger logger = getLogger(DataWriter.class);

    public void write(final String output, final String outputMethod) throws IOException {
        if (CONSOLE.equalsIgnoreCase(outputMethod)) {
            System.out.println(output);
            logger.info(RESULT_IS_WRITTEN_TO_CONSOLE);
        } else {
            final Path outputPath = Path.of("result.txt");
            writeString(outputPath, output, UTF_8);
            logger.info(RESULT_IS_WRITTEN_TO_FILE, outputPath.toAbsolutePath());
        }
    }
}
