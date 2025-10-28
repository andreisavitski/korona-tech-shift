package by.shift.task2.parser;

import java.util.HashMap;
import java.util.Map;

import static by.shift.task2.constant.ApplicationConstant.ARGUMENTS_REQUIRED;
import static by.shift.task2.constant.ApplicationConstant.ASSIGNMENT;
import static by.shift.task2.constant.ApplicationConstant.INPUT;
import static by.shift.task2.constant.ApplicationConstant.INPUT_FLAG;
import static by.shift.task2.constant.ApplicationConstant.INVALID_ARGUMENT_FORMAT;
import static by.shift.task2.constant.ApplicationConstant.NO_ARGUMENTS;
import static by.shift.task2.constant.ApplicationConstant.NUMBER_OF_ARGUMENT_SPLITTINGS;
import static by.shift.task2.constant.ApplicationConstant.OUTPUT;
import static by.shift.task2.constant.ApplicationConstant.OUTPUT_FLAG;
import static by.shift.task2.constant.ApplicationConstant.UNKNOWN_ARGUMENT;
import static java.util.Locale.ROOT;

public class CommandLineParser {

    public Map<String, String> parseArgs(final String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException(NO_ARGUMENTS);
        }
        final Map<String, String> argsMap = new HashMap<>();
        for (final String arg : args) {
            final String[] parts = arg.split(ASSIGNMENT, NUMBER_OF_ARGUMENT_SPLITTINGS);
            if (parts.length != NUMBER_OF_ARGUMENT_SPLITTINGS || parts[1].isBlank()) {
                throw new IllegalArgumentException(INVALID_ARGUMENT_FORMAT + arg);
            }
            final String key = parts[0].trim().toLowerCase(ROOT);
            final String value = parts[1].trim();
            switch (key) {
                case INPUT_FLAG -> argsMap.put(INPUT, value);
                case OUTPUT_FLAG -> argsMap.put(OUTPUT, value);
                default -> throw new IllegalArgumentException(UNKNOWN_ARGUMENT + arg);
            }
        }
        if (!argsMap.containsKey(INPUT) || !argsMap.containsKey(OUTPUT)) {
            throw new IllegalArgumentException(ARGUMENTS_REQUIRED);
        }
        return argsMap;
    }
}
