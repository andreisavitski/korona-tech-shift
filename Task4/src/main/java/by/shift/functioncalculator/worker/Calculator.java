package by.shift.functioncalculator.worker;

import org.slf4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.slf4j.LoggerFactory.getLogger;

public record Calculator(Task task) {

    private static final Logger logger = getLogger(Calculator.class);

    private static final String START_CALCULATIONS = "Начало вычислений для диапазона [{}..{}]";

    private static final String CALCULATIONS_COMPLETED =
            "Завершены вычисления для диапазона [{}..{}]. Частичная сумма: {}";

    private static final int SIGNIFICANT_DIGITS = 20;

    private static final int DEGREE_OF_POWER = 2;

    public BigDecimal compute() {
        logger.debug(START_CALCULATIONS, task.start(), task.end());
        BigDecimal sum = BigDecimal.ZERO;
        final MathContext mathContext = new MathContext(SIGNIFICANT_DIGITS);
        for (long i = task.start(); i <= task.end(); i++) {
            final BigDecimal term = BigDecimal.ONE.divide(BigDecimal.valueOf(i).pow(DEGREE_OF_POWER), mathContext);
            sum = sum.add(term);
        }
        logger.debug(CALCULATIONS_COMPLETED, task.start(), task.end(), sum);
        return sum;
    }
}