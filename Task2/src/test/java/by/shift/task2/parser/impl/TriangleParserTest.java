package by.shift.task2.parser.impl;

import by.shift.task2.exeption.ApplicationException;
import by.shift.task2.model.Shape;
import by.shift.task2.model.Triangle;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleParserTest {

    private final TriangleParser parser = new TriangleParser();

    @Test
    void parse_validTriangle_returnsShape() {
        Shape shape = parser.parse(List.of("ТРЕУГОЛЬНИК", "3 4 5"));
        assertEquals(3, ((Triangle) shape).getFirstSide());
        assertEquals(4, ((Triangle) shape).getSecondSide());
        assertEquals(5, ((Triangle) shape).getThirdSide());
    }

    @Test
    void parse_invalidSides_throwsException() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("ТРЕУГОЛЬНИК", "1 2 10")));
    }

    @Test
    void parse_negativeSide_throwsException() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("ТРЕУГОЛЬНИК", "-3 4 5")));
    }

    @Test
    void parse_notANumber_throwsException() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("ТРЕУГОЛЬНИК", "a 4 5")));
    }
}
