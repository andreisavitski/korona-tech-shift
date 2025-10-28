package by.shift.task2.parser;

import by.shift.task2.constant.ApplicationConstant;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandLineParserTest {

    private final CommandLineParser parser = new CommandLineParser();

    @Test
    void parseArgs_validArgs_shouldReturnMap() {
        String[] args = {"--input=file.txt", "--output=console"};
        Map<String, String> result = parser.parseArgs(args);

        assertEquals("file.txt", result.get(ApplicationConstant.INPUT));
        assertEquals("console", result.get(ApplicationConstant.OUTPUT));
    }

    @Test
    void parseArgs_noArgs_shouldThrow() {
        String[] args = {};
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> parser.parseArgs(args));
        assertTrue(ex.getMessage().contains("Нет аргументов"));
    }

    @Test
    void parseArgs_invalidFormat_shouldThrow() {
        String[] args = {"--inputfile.txt"};
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> parser.parseArgs(args));
        assertTrue(ex.getMessage().contains("Неверный формат аргумента"));
    }

    @Test
    void parseArgs_unknownArgument_shouldThrow() {
        String[] args = {"--unknown=value", "--input=file.txt", "--output=console"};
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> parser.parseArgs(args));
        assertTrue(ex.getMessage().contains("Неизвестный аргумент"));
    }

    @Test
    void parseArgs_missingInput_shouldThrow() {
        String[] args = {"--output=console"};
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> parser.parseArgs(args));
        assertTrue(ex.getMessage().contains("Необходимо оба аргумента"));
    }

    @Test
    void parseArgs_missingOutput_shouldThrow() {
        String[] args = {"--input=file.txt"};
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> parser.parseArgs(args));
        assertTrue(ex.getMessage().contains("Необходимо оба аргумента"));
    }
}
