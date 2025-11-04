package by.shift.task2.util;

import by.shift.task2.exception.ApplicationException;
import lombok.experimental.UtilityClass;

import java.util.List;

import static by.shift.task2.exception.ApplicationError.INCORRECT_NUMBER_OF_PARAMETERS;

@UtilityClass
public class Separator {

    private final Integer INDEX_SECOND_LINE = 1;

    private final String ANY_WHITESPACE_CHARACTER = "\\s+";

    public String[] splitParameters(final List<String> lines, final Integer numberOfParameters) {
        final String parameters = lines.get(INDEX_SECOND_LINE).trim();
        final String[] parts = parameters.trim().split(ANY_WHITESPACE_CHARACTER);
        if (parts.length != numberOfParameters) {
            throw new ApplicationException(INCORRECT_NUMBER_OF_PARAMETERS);
        }
        return parts;
    }
}
