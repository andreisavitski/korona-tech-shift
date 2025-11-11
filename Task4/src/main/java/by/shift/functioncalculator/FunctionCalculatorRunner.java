package by.shift.functioncalculator;

import by.shift.functioncalculator.io.DataReader;
import by.shift.functioncalculator.service.ComputationService;
import org.slf4j.Logger;

import java.math.BigDecimal;

import static org.slf4j.LoggerFactory.getLogger;

public class FunctionCalculatorRunner {

    private static final Logger logger = getLogger(FunctionCalculatorRunner.class);

    private static final String APPLICATION_START = "Приложение запущено";

    private static final String CALCULATION_COMPLETED = "Вычисление завершено. Итоговая сумма: {}";

    private static final String LEAD_TIME = "Время выполнения: {} секунд";

    private static final String ERROR_IN_RUNTIME = "Ошибка во время выполнения приложения";

    private static final String APPLICATION_COMPLETED = "Приложение завершено";

    private static final double NANOSECONDS_IN_SECOND = 1_000_000_000.0;

    public static void main(String[] args) {
        logger.info(APPLICATION_START);
        try {
            final ComputationService computationService = new ComputationService();
            final int n = DataReader.readNumber();

            final long startTime = System.nanoTime();
            final BigDecimal result = computationService.calculateSum(n);
            final long endTime = System.nanoTime();

            final BigDecimal seconds = BigDecimal.valueOf((endTime - startTime) / NANOSECONDS_IN_SECOND);

            logger.info(CALCULATION_COMPLETED, result);
            logger.info(LEAD_TIME, seconds);
        } catch (Exception e) {
            logger.error(ERROR_IN_RUNTIME, e);
        } finally {
            logger.info(APPLICATION_COMPLETED);
        }
    }
}
