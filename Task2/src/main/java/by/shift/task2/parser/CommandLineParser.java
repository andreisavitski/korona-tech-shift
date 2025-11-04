package by.shift.task2.parser;

import by.shift.task2.exception.ApplicationException;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

import static by.shift.task2.constant.ApplicationConstant.INPUT;
import static by.shift.task2.constant.ApplicationConstant.OUTPUT;
import static by.shift.task2.exception.ApplicationError.ARGUMENTS_REQUIRED;
import static by.shift.task2.exception.ApplicationError.INVALID_ARGUMENT_FORMAT;
import static by.shift.task2.exception.ApplicationError.NO_ARGUMENTS;
import static by.shift.task2.exception.ApplicationError.UNKNOWN_ARGUMENT;
import static java.util.Locale.ROOT;

@UtilityClass
public class CommandLineParser {

    private final String INPUT_FLAG = "--input";

    private final String OUTPUT_FLAG = "--output";

    private final String ASSIGNMENT = "=";

    private final Integer NUMBER_OF_ARGUMENT_SPLITTINGS = 2;


    public Map<String, String> parseArgs(final String[] args) {
        if (args == null || args.length == 0) {
            throw new ApplicationException(NO_ARGUMENTS);
        }
        final Map<String, String> argsMap = new HashMap<>();
        for (final String arg : args) {
            final String[] parts = arg.split(ASSIGNMENT, NUMBER_OF_ARGUMENT_SPLITTINGS);
            if (parts.length != NUMBER_OF_ARGUMENT_SPLITTINGS || parts[1].isBlank()) {
                throw new ApplicationException(INVALID_ARGUMENT_FORMAT, arg);
            }
            final String key = parts[0].trim().toLowerCase(ROOT);
            final String value = parts[1].trim();
            switch (key) {
                case INPUT_FLAG -> argsMap.put(INPUT, value);
                case OUTPUT_FLAG -> argsMap.put(OUTPUT, value);
                default -> throw new ApplicationException(UNKNOWN_ARGUMENT, arg);
            }
        }
        if (!argsMap.containsKey(INPUT) || !argsMap.containsKey(OUTPUT)) {
            throw new ApplicationException(ARGUMENTS_REQUIRED);
        }
        return argsMap;
    }
}
