package by.shift.minesweeper.validator;

import by.shift.minesweeper.exception.ApplicationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestValidatorTest {

    private static final String INVALID_ID = "Неверный id: ";

    private static final String INVALID_PARAMETERS = "Неверные параметры row и col";

    @Test
    void validateIdWhenNullShouldThrowApplicationException() {
        assertThrowsWithMessage(() -> RequestValidator.validateId(null), INVALID_ID);
    }

    @Test
    void validateIdWhenBlankShouldThrowApplicationException() {
        assertThrowsWithMessage(() -> RequestValidator.validateId(" "), INVALID_ID);
    }

    @Test
    void validateRowAndColWhenNegativeShouldThrowApplicationException() {
        assertThrowsWithMessage(() -> RequestValidator.validateRowAndCol(-1, 0), INVALID_PARAMETERS);
        assertThrowsWithMessage(() -> RequestValidator.validateRowAndCol(0, -1), INVALID_PARAMETERS);
    }

    @Test
    void validateRowAndColWhenPositiveShouldNotThrow() {
        int[][] validPairs = {{0, 0}, {5, 5}, {10, 10}};
        for (int[] pair : validPairs) {
            assertDoesNotThrow(() -> RequestValidator.validateRowAndCol(pair[0], pair[1]));
        }
    }

    private void assertThrowsWithMessage(Executable executable, String message) {
        ApplicationException exception = assertThrows(ApplicationException.class, executable);
        assertTrue(exception.getMessage().contains(message));
    }
}