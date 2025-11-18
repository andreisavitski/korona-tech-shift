package by.shift.functioncalculator;

import by.shift.functioncalculator.io.DataReader;
import by.shift.functioncalculator.service.ComputationService;
import org.slf4j.Logger;

import java.math.BigDecimal;

import static org.slf4j.LoggerFactory.getLogger;

public class FunctionCalculatorRunner {

    private static final Logger logger = getLogger(FunctionCalculatorRunner.class);

    private static final double NANOSECONDS_IN_SECOND = 1_000_000_000.0;

    public static void main(String[] args) {
        logger.info("Приложение запущено");
        try {
            ComputationService computationService = new ComputationService();
            int n = DataReader.readNumber();

            long startTime = System.nanoTime();
            BigDecimal result = computationService.calculateSum(n);
            long endTime = System.nanoTime();

            BigDecimal seconds = BigDecimal.valueOf((endTime - startTime) / NANOSECONDS_IN_SECOND);

            logger.info("Вычисление завершено. Итоговая сумма: {}", result);
            logger.info("Время выполнения: {} секунд", seconds);
        } catch (Exception e) {
            logger.error("Ошибка во время выполнения приложения", e);
        } finally {
            logger.info("Приложение завершено");
        }
    }
}
