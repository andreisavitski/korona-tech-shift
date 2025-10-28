package by.shift.task2.validator;

import lombok.experimental.UtilityClass;

import java.util.List;

import static by.shift.task2.constant.ApplicationConstant.FILE_IS_EMPTY;

@UtilityClass
public class DataValidator {

    public static void checkIsEmpty(final List<String> lines) {
        if (lines.isEmpty()) throw new IllegalArgumentException(FILE_IS_EMPTY);
    }
}
