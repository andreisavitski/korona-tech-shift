package by.shift.task2.model;

import by.shift.task2.exeption.ApplicationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static by.shift.task2.constant.ApplicationConstant.CIRCLE;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleTest {

    @ParameterizedTest
    @CsvSource({
            "7788.0",
            "1.0"
    })
    void circleParameterizedTests(Double radius) {
        Circle circle = new Circle(radius);
        assertEquals(radius, circle.getRadius());
        assertEquals(CIRCLE, circle.getName());
        assertEquals(PI * radius * radius, circle.calculateArea(), 1e-9);
        assertEquals(2 * PI * radius, circle.calculatePerimeter(), 1e-9);
        assertEquals(2 * radius, circle.calculateDiameter());
    }

    @ParameterizedTest
    @CsvSource({
            "-9379992.0",
            "-0.0"
    })
    void constructor_negativeRadius_shouldThrow(Double radius) {
        assertThrows(ApplicationException.class, () -> new Circle(radius));
    }
}