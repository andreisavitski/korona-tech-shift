package by.shift.functioncalculator.worker;

import org.slf4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.slf4j.LoggerFactory.getLogger;

public record Calculator(Task task) {

    private static final Logger logger = getLogger(Calculator.class);

    private static final int SIGNIFICANT_DIGITS = 20;

    private static final int DEGREE_OF_POWER = 2;

    public BigDecimal compute() {
        logger.debug("Начало вычислений для диапазона [{}..{}]", task.start(), task.end());
        BigDecimal sum = BigDecimal.ZERO;
        MathContext mathContext = new MathContext(SIGNIFICANT_DIGITS);
        for (long i = task.start(); i <= task.end(); i++) {
            BigDecimal term = BigDecimal.ONE.divide(BigDecimal.valueOf(i).pow(DEGREE_OF_POWER), mathContext);
            sum = sum.add(term);
        }
        logger.debug("Завершены вычисления для диапазона [{}..{}]. Частичная сумма: {}",
                task.start(), task.end(), sum);
        return sum;
    }
}