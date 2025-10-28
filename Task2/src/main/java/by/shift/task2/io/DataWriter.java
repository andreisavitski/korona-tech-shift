package by.shift.task2.io;

import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

import static by.shift.task2.constant.ApplicationConstant.CONSOLE;
import static by.shift.task2.constant.ApplicationConstant.RESULT_IS_WRITTEN_TO_CONSOLE;
import static by.shift.task2.constant.ApplicationConstant.RESULT_IS_WRITTEN_TO_FILE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.writeString;
import static org.slf4j.LoggerFactory.getLogger;

public class DataWriter {

    private static final Logger logger = getLogger(DataWriter.class);

    public void write(final String output, final String outputMethod) throws IOException {
        if (CONSOLE.equalsIgnoreCase(outputMethod)) {
            System.out.println(output);
            logger.info(RESULT_IS_WRITTEN_TO_CONSOLE);
        } else {
            final Path outputPath = Path.of(outputMethod);
            writeString(outputPath, output, UTF_8);
            logger.info(RESULT_IS_WRITTEN_TO_FILE, outputPath.toAbsolutePath());
        }
    }
}
