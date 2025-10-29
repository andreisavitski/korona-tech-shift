package by.shift.task2.model;

import by.shift.task2.exeption.ApplicationException;
import org.junit.jupiter.api.Test;

import static by.shift.task2.constant.ApplicationConstant.*;
import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void constructor_validRadius_shouldCreateCircle() {
        Circle circle = new Circle(5.0);
        assertEquals(5.0, circle.getRadius());
    }

    @Test
    void constructor_negativeRadius_shouldThrow() {
        assertThrows(ApplicationException.class, () -> new Circle(-3.0));
    }

    @Test
    void getName_shouldReturnCircle() {
        Circle circle = new Circle(4.0);
        assertEquals(CIRCLE, circle.getName());
    }

    @Test
    void calculateArea_shouldReturnCorrectValue() {
        Circle circle = new Circle(2.0);
        assertEquals(PI * 4, circle.calculateArea(), 1e-9);
    }

    @Test
    void calculatePerimeter_shouldReturnCorrectValue() {
        Circle circle = new Circle(3.0);
        assertEquals(2 * PI * 3, circle.calculatePerimeter(), 1e-9);
    }

    @Test
    void calculateDiameter_shouldReturnCorrectValue() {
        Circle circle = new Circle(7.5);
        assertEquals(15.0, circle.calculateDiameter());
    }
}