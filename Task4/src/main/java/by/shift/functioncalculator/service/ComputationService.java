package by.shift.functioncalculator.service;

import by.shift.functioncalculator.manager.ComputationManager;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.slf4j.LoggerFactory.getLogger;

public class ComputationService {

    private static final Logger logger = getLogger(ComputationService.class);

    private final ExecutorService executorService;

    private final int processors;

    public ComputationService() {
        this.processors = Runtime.getRuntime().availableProcessors();
        this.executorService = Executors.newFixedThreadPool(processors);
        logger.info("Создан пул потоков. Доступно процессоров: {}", processors);
    }

    public BigDecimal calculateSum(int n) {
        try {
            ComputationManager manager = new ComputationManager(executorService);
            BigDecimal total = manager.computeParallel(n, processors);
            logger.info("Все задачи завершены");
            return total;
        } finally {
            executorService.shutdown();
            logger.info("Пул потоков остановлен");
        }
    }
}