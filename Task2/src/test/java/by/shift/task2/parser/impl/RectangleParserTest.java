package by.shift.task2.parser.impl;

import by.shift.task2.exception.ApplicationException;
import by.shift.task2.model.Rectangle;
import by.shift.task2.model.Shape;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleParserTest {

    private final RectangleParser parser = new RectangleParser();

    @Test
    void parse_validRectangle_shouldReturnShape() {
        List<String> lines = List.of("ПРЯМОУГОЛЬНИК", "5 10");
        Rectangle expected = new Rectangle(5d, 10d);
        Shape actual = parser.parse(lines);
        assertEquals(expected, actual);

    }

    @Test
    void parse_negativeSide_shouldThrowApplicationException() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("ПРЯМОУГОЛЬНИК", "-5 10")));
    }

    @Test
    void parse_notNumber_shouldThrowApplicationException() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("RECTANGLE", "a 10")));
    }
}
