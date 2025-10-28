package by.shift.task2.util;

import lombok.experimental.UtilityClass;

import java.util.List;

import static by.shift.task2.constant.ApplicationConstant.ANY_WHITESPACE_CHARACTER;
import static by.shift.task2.constant.ApplicationConstant.EMPTY_LINE;
import static by.shift.task2.constant.ApplicationConstant.INCORRECT_NUMBER_OF_PARAMETERS;
import static by.shift.task2.constant.ApplicationConstant.INDEX_SECOND_LINE;

@UtilityClass
public class Separator {

    public static String[] splitParameters(final List<String> lines, final Integer numberOfParameters) {
        final String parameters = lines.size() > INDEX_SECOND_LINE ? lines.get(INDEX_SECOND_LINE).trim() : EMPTY_LINE;
        final String[] parts = parameters.trim().split(ANY_WHITESPACE_CHARACTER);
        if (parts.length != numberOfParameters) {
            throw new IllegalArgumentException(INCORRECT_NUMBER_OF_PARAMETERS);
        }
        return parts;
    }
}
