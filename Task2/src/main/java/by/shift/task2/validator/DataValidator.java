package by.shift.task2.validator;

import by.shift.task2.exception.ApplicationException;
import lombok.experimental.UtilityClass;

import java.nio.file.Path;
import java.util.List;

import static by.shift.task2.exception.ApplicationError.FILE_NOT_FOUND;
import static by.shift.task2.exception.ApplicationError.INSUFFICIENT_DATA;
import static java.nio.file.Files.exists;

@UtilityClass
public class DataValidator {

    public void checkIsEmpty(final List<String> lines) {
        if (lines.isEmpty() || lines.size() < 2)
            throw new ApplicationException(INSUFFICIENT_DATA);
    }

    public void checkingForFileExistence(final Path filePath) {
        if (!exists(filePath)) {
            throw new ApplicationException(FILE_NOT_FOUND, filePath.toString());
        }
    }
}
