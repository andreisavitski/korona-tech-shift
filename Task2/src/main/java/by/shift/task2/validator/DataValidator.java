package by.shift.task2.validator;

import by.shift.task2.exception.ApplicationException;
import lombok.experimental.UtilityClass;

import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.exists;

@UtilityClass
public class DataValidator {

    private static final String INSUFFICIENT_DATA = "Недостаточно данных";

    private static final String FILE_NOT_FOUND = "Файл не найден по пути: ";

    public void checkIsEmpty(final List<String> lines) {
        if (lines.isEmpty() || lines.size() < 2)
            throw new ApplicationException(INSUFFICIENT_DATA);
    }

    public void checkingForFileExistence(final Path filePath) {
        if (!exists(filePath)) {
            throw new ApplicationException(FILE_NOT_FOUND + filePath);
        }
    }
}
