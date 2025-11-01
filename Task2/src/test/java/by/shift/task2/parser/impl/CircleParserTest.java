package by.shift.task2.parser.impl;

import by.shift.task2.exeption.ApplicationException;
import by.shift.task2.model.Circle;
import by.shift.task2.model.Shape;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleParserTest {

    private final CircleParser parser = new CircleParser();

    @Test
    void parse_validCircle_shouldReturnShape() {
        List<String> lines = List.of("КРУГ", "5");
        Circle expected = new Circle(5d);
        Shape actual = parser.parse(lines);
        assertEquals(expected, actual);
    }

    @Test
    void parse_negativeRadius_throwsException() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("КРУГ", "-1")));
    }

    @Test
    void parse_notNumber_throwsException() {
        assertThrows(ApplicationException.class, () -> parser.parse(List.of("КРУГ", "abc")));
    }
}
