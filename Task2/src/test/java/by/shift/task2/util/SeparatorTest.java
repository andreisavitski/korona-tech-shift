package by.shift.task2.util;

import by.shift.task2.exeption.ApplicationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeparatorTest {

    @Test
    void splitParameters_correctNumber_returnsArray() {
        String[] result = Separator.splitParameters(List.of("КРУГ", "5"), 1);
        assertArrayEquals(new String[]{"5"}, result);
    }

    @Test
    void splitParameters_incorrectNumber_throwsException() {
        assertThrows(ApplicationException.class,
                () -> Separator.splitParameters(List.of("КРУГ", "5 10"), 1));
    }
}