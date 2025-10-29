package by.shift.task2.model;

import by.shift.task2.exeption.ApplicationException;
import org.junit.jupiter.api.Test;

import static by.shift.task2.constant.ApplicationConstant.*;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void constructor_validSides_shouldCreateRectangle() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        assertEquals(3.0, rectangle.getFirstSide());
        assertEquals(4.0, rectangle.getSecondSide());
    }

    @Test
    void constructor_negativeSide_shouldThrow() {
        assertThrows(ApplicationException.class, () -> new Rectangle(-1.0, 5.0));
    }

    @Test
    void getName_shouldReturnRectangle() {
        Rectangle rectangle = new Rectangle(2.0, 5.0);
        assertEquals(RECTANGLE, rectangle.getName());
    }

    @Test
    void calculateArea_shouldReturnCorrectValue() {
        Rectangle rectangle = new Rectangle(2.0, 6.0);
        assertEquals(12.0, rectangle.calculateArea());
    }

    @Test
    void calculatePerimeter_shouldReturnCorrectValue() {
        Rectangle rectangle = new Rectangle(2.0, 6.0);
        assertEquals(16.0, rectangle.calculatePerimeter());
    }

    @Test
    void calculateDiagonal_shouldReturnCorrectValue() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        assertEquals(5.0, rectangle.calculateDiagonal(), 1e-9);
    }

    @Test
    void getLength_shouldReturnBiggerSide() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        assertEquals(4.0, rectangle.getLength());
    }

    @Test
    void getWidth_shouldReturnSmallerSide() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        assertEquals(3.0, rectangle.getWidth());
    }
}