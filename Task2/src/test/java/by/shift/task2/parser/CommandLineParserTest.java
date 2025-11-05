package by.shift.task2.parser;

import by.shift.task2.constant.ApplicationConstant;
import by.shift.task2.exception.ApplicationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandLineParserTest {

    private static final String NO_ARGUMENTS =
            "Нет аргументов. Необходимо использовать --input=<file> и --output=<method>";

    private static final String INVALID_ARGUMENT_FORMAT = "Неверный формат аргумента: ";

    private static final String UNKNOWN_ARGUMENT = "Неизвестный аргумент: ";

    private static final String ARGUMENTS_REQUIRED = "Необходимо оба аргумента --input и --output";

    @Test
    void parseArgs_validArgs_shouldReturnMap() {
        String[] args = {"--input=file.txt", "--output=console"};
        Map<String, String> result = CommandLineParser.parseArgs(args);
        assertEquals("file.txt", result.get(ApplicationConstant.INPUT));
        assertEquals("console", result.get(ApplicationConstant.OUTPUT));
    }

    @Test
    void parseArgs_noArgs_shouldThrowApplicationException() {
        String[] args = {};
        ApplicationException ex = assertThrows(ApplicationException.class,
                () -> CommandLineParser.parseArgs(args));
        assertTrue(ex.getMessage().contains(NO_ARGUMENTS));
    }

    @ParameterizedTest
    @ValueSource(strings = {"--input=file.txt", "--output=console"})
    void parseArgs_missingArgument_shouldThrowApplicationException(String arg) {
        String[] args = {arg};
        ApplicationException ex = assertThrows(ApplicationException.class,
                () -> CommandLineParser.parseArgs(args));
        assertTrue(ex.getMessage().contains(ARGUMENTS_REQUIRED));
    }

    @Test
    void parseArgs_invalidFormat_shouldThrowApplicationException() {
        String[] args = {"--inputfile.txt"};
        ApplicationException ex = assertThrows(ApplicationException.class,
                () -> CommandLineParser.parseArgs(args));
        assertTrue(ex.getMessage().contains(INVALID_ARGUMENT_FORMAT));
    }

    @Test
    void parseArgs_unknownArgument_shouldThrowApplicationException() {
        String[] args = {"--unknown=value", "--input=file.txt", "--output=console"};
        ApplicationException ex = assertThrows(ApplicationException.class,
                () -> CommandLineParser.parseArgs(args));
        assertTrue(ex.getMessage().contains(UNKNOWN_ARGUMENT));
    }
}