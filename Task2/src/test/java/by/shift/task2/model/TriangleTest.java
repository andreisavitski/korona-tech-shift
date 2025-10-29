package by.shift.task2.model;

import by.shift.task2.exeption.ApplicationException;
import org.junit.jupiter.api.Test;

import static by.shift.task2.constant.ApplicationConstant.*;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void constructor_validSides_shouldCreateTriangle() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals(3.0, triangle.getFirstSide());
        assertEquals(4.0, triangle.getSecondSide());
        assertEquals(5.0, triangle.getThirdSide());
    }

    @Test
    void constructor_negativeSide_shouldThrow() {
        assertThrows(ApplicationException.class, () -> new Triangle(-3.0, 4.0, 5.0));
    }

    @Test
    void constructor_invalidTriangle_shouldThrow() {
        assertThrows(ApplicationException.class, () -> new Triangle(1.0, 2.0, 5.0));
    }

    @Test
    void getName_shouldReturnTriangle() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals(TRIANGLE, triangle.getName());
    }

    @Test
    void calculateArea_shouldReturnCorrectValue() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals(6.0, triangle.calculateArea(), 1e-9);
    }

    @Test
    void calculatePerimeter_shouldReturnCorrectValue() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals(12.0, triangle.calculatePerimeter());
    }

    @Test
    void getOppositeAngleFirstSide_shouldBeAccurate() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals(36.869897, triangle.getOppositeAngleFirstSide(), 1e-6);
    }

    @Test
    void getOppositeAngleSecondSide_shouldBeAccurate() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals(53.130102, triangle.getOppositeAngleSecondSide(), 1e-6);
    }

    @Test
    void getOppositeAngleThirdSide_shouldBeAccurate() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        assertEquals(90.0, triangle.getOppositeAngleThirdSide(), 1e-6);
    }
}
