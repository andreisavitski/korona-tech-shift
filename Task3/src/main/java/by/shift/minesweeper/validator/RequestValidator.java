package by.shift.minesweeper.validator;

import by.shift.minesweeper.exception.ApplicationException;

public class RequestValidator {

    private static final String INVALID_ID = "Неверный id: ";

    private static final String INVALID_PARAMETERS = "Неверные параметры row и col";

    private RequestValidator() {
    }

    public static void validateId(final String id) {
        if (id == null || id.isBlank()) {
            throw new ApplicationException(INVALID_ID + id);
        }
    }

    public static void validateRowAndCol(final int row, final int col) {
        if (row < 0 || col < 0) {
            throw new ApplicationException(INVALID_PARAMETERS);
        }
    }
}
