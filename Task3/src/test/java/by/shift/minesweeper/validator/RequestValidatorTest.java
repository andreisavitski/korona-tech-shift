package by.shift.minesweeper.validator;

import by.shift.minesweeper.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RequestValidatorTest {

    private static final String INVALID_ID = "Неверный id: ";

    private static final String INVALID_PARAMETERS = "Неверные параметры row и col";

    @Test
    void validateIdWhenNullShouldThrowApplicationException() {
        assertThatThrownBy(() -> RequestValidator.validateId(null))
                .isInstanceOf(ApplicationException.class)
                .hasMessageContaining(INVALID_ID);
    }

    @Test
    void validateIdWhenBlankShouldThrowApplicationException() {
        assertThatThrownBy(() -> RequestValidator.validateId(" "))
                .isInstanceOf(ApplicationException.class)
                .hasMessageContaining(INVALID_ID);
    }

    @Test
    void validateRowAndColWhenNegativeShouldThrowApplicationException() {
        assertThatThrownBy(() -> RequestValidator.validateRowAndCol(-1, 0))
                .isInstanceOf(ApplicationException.class)
                .hasMessageContaining(INVALID_PARAMETERS);

        assertThatThrownBy(() -> RequestValidator.validateRowAndCol(0, -1))
                .isInstanceOf(ApplicationException.class)
                .hasMessageContaining(INVALID_PARAMETERS);
    }

    @Test
    void validateRowAndColWhenPositiveShouldNotThrow() {
        int[][] validPairs = {{0, 0}, {5, 5}, {10, 10}};
        for (int[] pair : validPairs) {
            assertThatCode(() -> RequestValidator.validateRowAndCol(pair[0], pair[1]))
                    .doesNotThrowAnyException();
        }
    }
}