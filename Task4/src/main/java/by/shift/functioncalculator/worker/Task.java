package by.shift.functioncalculator.worker;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public record Task(int start, int end) {

    private static final Logger logger = getLogger(Task.class);

    private static final String TASK_CREATED = "Создан Task [{}..{}]";

    public Task {
        logger.debug(TASK_CREATED, start, end);
    }
}