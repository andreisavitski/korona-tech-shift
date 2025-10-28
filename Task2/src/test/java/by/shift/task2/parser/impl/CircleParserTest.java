package by.shift.task2.parser.impl;

import by.shift.task2.model.Shape;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleParserTest {

    private final CircleParser parser = new CircleParser();

    @Test
    void parse_validCircle_returnsShape() {
        Shape shape = parser.parse(List.of("КРУГ", "5"));
        assertEquals(5, ((by.shift.task2.model.impl.Circle) shape).radius());
    }

    @Test
    void parse_negativeRadius_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(List.of("КРУГ", "-1")));
    }

    @Test
    void parse_notANumber_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(List.of("КРУГ", "abc")));
    }
}
