package by.shift.task2.util;

import by.shift.task2.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SeparatorTest {

    @Test
    void splitParameters_correctNumber_shouldReturnArray() {
        String[] result = Separator.splitParameters(List.of("КРУГ", "5"), 1);
        assertArrayEquals(new String[]{"5"}, result);
    }

    @Test
    void splitParameters_incorrectNumber_shouldThrowApplicationException() {
        assertThrows(ApplicationException.class,
                () -> Separator.splitParameters(List.of("КРУГ", "5 10"), 1));
    }
}