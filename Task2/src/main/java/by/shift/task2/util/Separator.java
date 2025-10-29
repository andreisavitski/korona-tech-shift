package by.shift.task2.util;

import by.shift.task2.exeption.ApplicationException;
import lombok.experimental.UtilityClass;

import java.util.List;

import static by.shift.task2.exeption.ApplicationError.*;

@UtilityClass
public class Separator {

    private static final String EMPTY_LINE = "";

    private static final Integer INDEX_SECOND_LINE = 1;

    private static final String ANY_WHITESPACE_CHARACTER = "\\s+";

    public String[] splitParameters(final List<String> lines, final Integer numberOfParameters) {
        final String parameters = lines.size() > INDEX_SECOND_LINE ? lines.get(INDEX_SECOND_LINE).trim() : EMPTY_LINE;
        final String[] parts = parameters.trim().split(ANY_WHITESPACE_CHARACTER);
        if (parts.length != numberOfParameters) {
            throw new ApplicationException(INCORRECT_NUMBER_OF_PARAMETERS);
        }
        return parts;
    }
}
