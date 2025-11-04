package by.shift.task2.model;

import by.shift.task2.exception.ApplicationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static by.shift.task2.constant.ApplicationConstant.RECTANGLE;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleTest {

    @ParameterizedTest
    @CsvSource({
            "1.0,1.0",
            "999.0,999.0"
    })
    void rectangleParameterizedTests(Double firstSide, Double secondSide) {
        Rectangle rectangle = new Rectangle(firstSide, secondSide);

        assertEquals(Math.min(firstSide, secondSide), rectangle.getWidth());
        assertEquals(Math.max(firstSide, secondSide), rectangle.getLength());
        assertEquals(firstSide, rectangle.getFirstSide());
        assertEquals(secondSide, rectangle.getSecondSide());

        assertEquals(RECTANGLE, rectangle.getName());

        assertEquals(firstSide * secondSide, rectangle.calculateArea());

        assertEquals(2 * (firstSide + secondSide), rectangle.calculatePerimeter());

        assertEquals(sqrt(firstSide * firstSide + secondSide * secondSide), rectangle.calculateDiagonal(), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
            "-1.0,999.0",
            "1.0,-999.0"
    })
    void constructor_negativeSide_shouldThrow(Double firstSide, Double secondSide) {
        assertThrows(ApplicationException.class, () -> new Rectangle(firstSide, secondSide));
    }
}