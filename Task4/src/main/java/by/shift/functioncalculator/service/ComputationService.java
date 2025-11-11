package by.shift.functioncalculator.service;

import by.shift.functioncalculator.manager.ComputationManager;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.slf4j.LoggerFactory.getLogger;

public class ComputationService {

    private static final Logger logger = getLogger(ComputationService.class);

    private static final String THREAD_POOL_CREATED = "Создан пул потоков. Доступно процессоров: {}";

    private static final String ALL_TASKS_COMPLETED = "Все задачи завершены";

    private static final String THREAD_POOL_STOPPED = "Пул потоков остановлен";

    private final ExecutorService executorService;

    private final int processors;

    public ComputationService() {
        this.processors = Runtime.getRuntime().availableProcessors();
        this.executorService = Executors.newFixedThreadPool(processors);
        logger.info(THREAD_POOL_CREATED, processors);
    }

    public BigDecimal calculateSum(final int n) {
        try {
            final ComputationManager manager = new ComputationManager(executorService);
            final BigDecimal total = manager.computeParallel(n, processors);
            logger.info(ALL_TASKS_COMPLETED);
            return total;
        } finally {
            executorService.shutdown();
            logger.info(THREAD_POOL_STOPPED);
        }
    }
}