package by.shift.functioncalculator.worker;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public record Task(int start, int end) {

    private static final Logger logger = getLogger(Task.class);

    public Task {
        logger.debug("Создан Task [{}..{}]", start, end);
    }
}