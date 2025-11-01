package by.shift.task2.io;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

import static by.shift.task2.constant.ApplicationConstant.CONSOLE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.writeString;
import static org.slf4j.LoggerFactory.getLogger;

@UtilityClass
public class DataWriter {

    private final String RESULT_IS_WRITTEN_TO_FILE = "Результат записан в файл: {}";

    private final String RESULT_IS_WRITTEN_TO_CONSOLE = "Результат записан в консоль";

    private final Logger logger = getLogger(DataWriter.class);

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
