package by.shift.task2.validator;

import by.shift.task2.exeption.ApplicationException;
import lombok.experimental.UtilityClass;

import java.nio.file.Path;
import java.util.List;

import static by.shift.task2.exeption.ApplicationError.*;
import static java.nio.file.Files.*;

@UtilityClass
public class DataValidator {

    public void checkIsEmpty(final List<String> lines) {
        if (lines.isEmpty()) throw new ApplicationException(FILE_IS_EMPTY);
    }

    public void checkingForFileExistence(final Path filePath) {
        if (!exists(filePath)) {
            throw new ApplicationException(FILE_NOT_FOUND, filePath.toString());
        }
    }
}
