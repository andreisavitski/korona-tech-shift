package by.shift.task2.validator;

import by.shift.task2.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShapeValidatorTest {

    @Test
    void checkIfNumbers_validNumbers_shouldNotThrowApplicationException() {
        assertDoesNotThrow(() -> ShapeValidator.checkIfNumbers(new String[]{"1", "2.5", "3"}));
    }

    @Test
    void checkIfNumbers_invalidNumber_shouldThrowApplicationException() {
        assertThrows(ApplicationException.class,
                () -> ShapeValidator.checkIfNumbers(new String[]{"1", "a", "3"}));
    }

    @Test
    void checkForPositiveNumbers_allPositive_shouldNotThrowApplicationException() {
        assertDoesNotThrow(() -> ShapeValidator.checkForPositiveNumbers(new String[]{"1", "2", "3"}));
    }

    @Test
    void checkForPositiveNumbers_containsZeroOrNegative_shouldThrowApplicationException() {
        assertThrows(ApplicationException.class,
                () -> ShapeValidator.checkForPositiveNumbers(new String[]{"1", "-2", "3"}));
        assertThrows(ApplicationException.class,
                () -> ShapeValidator.checkForPositiveNumbers(new String[]{"0", "2", "3"}));
    }

    @Test
    void checkTriangleSides_validTriangle_shouldNotThrowApplicationException() {
        assertDoesNotThrow(() -> ShapeValidator.checkTriangleSides(new String[]{"3", "4", "5"}));
    }

    @Test
    void checkTriangleSides_invalidTriangle_shouldThrowApplicationException() {
        assertThrows(ApplicationException.class,
                () -> ShapeValidator.checkTriangleSides(new String[]{"1", "2", "10"}));
    }

    @Test
    void checkShapeType_validTypes_shouldNotThrowApplicationException() {
        assertDoesNotThrow(() -> ShapeValidator.checkShapeType(List.of("CIRCLE")));
        assertDoesNotThrow(() -> ShapeValidator.checkShapeType(List.of("RECTANGLE")));
        assertDoesNotThrow(() -> ShapeValidator.checkShapeType(List.of("TRIANGLE")));
    }

    @Test
    void checkShapeType_invalidType_shouldThrowApplicationException() {
        assertThrows(ApplicationException.class,
                () -> ShapeValidator.checkShapeType(List.of("ШИФТ")));
    }
}
