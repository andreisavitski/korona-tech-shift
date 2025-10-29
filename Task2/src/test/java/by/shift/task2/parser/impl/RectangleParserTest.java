package by.shift.task2.parser.impl;

import by.shift.task2.exeption.ApplicationException;
import by.shift.task2.model.Rectangle;
import by.shift.task2.model.Shape;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleParserTest {

    private final RectangleParser parser = new RectangleParser();

    @Test
    void parse_validRectangle_returnsShape() {
        Shape shape = parser.parse(List.of("ПРЯМОУГОЛЬНИК", "5 10"));
        assertEquals(5, ((Rectangle) shape).getWidth());
        assertEquals(10, ((Rectangle) shape).getLength());
    }

    @Test
    void parse_negativeSide_throwsException() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("ПРЯМОУГОЛЬНИК", "-5 10")));
    }

    @Test
    void parse_notANumber_throwsException() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("RECTANGLE", "a 10")));
    }
}
