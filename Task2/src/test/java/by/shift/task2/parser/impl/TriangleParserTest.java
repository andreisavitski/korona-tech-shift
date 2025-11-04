package by.shift.task2.parser.impl;

import by.shift.task2.exception.ApplicationException;
import by.shift.task2.model.Shape;
import by.shift.task2.model.Triangle;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleParserTest {

    private final TriangleParser parser = new TriangleParser();

    @Test
    void parse_validTriangle_shouldReturnsShape() {
        List<String> lines = List.of("ТРЕУГОЛЬНИК", "3 4 5");
        Triangle expected = new Triangle(3d, 4d, 5d);
        Shape actual = parser.parse(lines);
        assertEquals(expected, actual);
    }

    @Test
    void parse_invalidSides_shouldThrow() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("ТРЕУГОЛЬНИК", "1 2 10")));
    }

    @Test
    void parse_negativeSide_shouldThrow() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("ТРЕУГОЛЬНИК", "-3 4 5")));
    }

    @Test
    void parse_notNumber_shouldThrow() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("ТРЕУГОЛЬНИК", "a 4 5")));
    }
}
