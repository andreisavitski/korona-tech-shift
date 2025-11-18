package by.shift.functioncalculator.io;

import org.slf4j.Logger;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static org.slf4j.LoggerFactory.getLogger;

public final class DataReader {

    private static final Logger logger = getLogger(DataReader.class);

    private static final int MIN_NUMBER = 1;

    private static final int MAX_NUMBER = 1_000_000_000;

    private DataReader() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static int readNumber() {
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            logger.info("Введите целое число от {} до {}: ", MIN_NUMBER, MAX_NUMBER);
            String input = scanner.nextLine();
            try {
                number = parseInt(input.trim());
                if (number >= MIN_NUMBER && number <= MAX_NUMBER) {
                    logger.info("Пользователь ввел значение N = {}", number);
                    return number;
                }
                logger.info("Число вне допустимого диапазона, попробуйте еще раз");
            } catch (NumberFormatException e) {
                logger.warn("Ошибка парсинга числа: {}", input);
            }
        }
    }
}
