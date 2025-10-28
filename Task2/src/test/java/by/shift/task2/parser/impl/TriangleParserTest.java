package by.shift.task2.parser.impl;

import by.shift.task2.model.Shape;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleParserTest {

    private final TriangleParser parser = new TriangleParser();

    @Test
    void parse_validTriangle_returnsShape() {
        Shape shape = parser.parse(List.of("ТРЕУГОЛЬНИК", "3 4 5"));
        assertEquals(3, ((by.shift.task2.model.impl.Triangle) shape).firstSide());
        assertEquals(4, ((by.shift.task2.model.impl.Triangle) shape).secondSide());
        assertEquals(5, ((by.shift.task2.model.impl.Triangle) shape).thirdSide());
    }

    @Test
    void parse_invalidSides_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(List.of("ТРЕУГОЛЬНИК", "1 2 10")));
    }

    @Test
    void parse_negativeSide_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(List.of("ТРЕУГОЛЬНИК", "-3 4 5")));
    }

    @Test
    void parse_notANumber_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(List.of("ТРЕУГОЛЬНИК", "a 4 5")));
    }
}
