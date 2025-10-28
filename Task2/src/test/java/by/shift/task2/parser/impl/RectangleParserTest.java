package by.shift.task2.parser.impl;

import by.shift.task2.model.Shape;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleParserTest {

    private final RectangleParser parser = new RectangleParser();

    @Test
    void parse_validRectangle_returnsShape() {
        Shape shape = parser.parse(List.of("ПРЯМОУГОЛЬНИК", "5 10"));
        assertEquals(5, ((by.shift.task2.model.impl.Rectangle) shape).getWidth());
        assertEquals(10, ((by.shift.task2.model.impl.Rectangle) shape).getLength());
    }

    @Test
    void parse_negativeSide_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(List.of("ПРЯМОУГОЛЬНИК", "-5 10")));
    }

    @Test
    void parse_notANumber_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(List.of("RECTANGLE", "a 10")));
    }
}
