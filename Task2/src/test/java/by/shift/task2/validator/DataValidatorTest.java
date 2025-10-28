package by.shift.task2.validator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataValidatorTest {

    @Test
    void checkIsEmpty_emptyList_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> DataValidator.checkIsEmpty(List.of()));
    }

    @Test
    void checkIsEmpty_nonEmptyList_noException() {
        assertDoesNotThrow(() -> DataValidator.checkIsEmpty(List.of("line1")));
    }
}