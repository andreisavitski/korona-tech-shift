package by.shift.functioncalculator.io;

import org.slf4j.Logger;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static org.slf4j.LoggerFactory.getLogger;

public class DataReader {

    private static final Logger logger = getLogger(DataReader.class);

    private static final String UTILITY_CLASS = "Utility class";

    private static final String ENTER_AN_INTEGER = "Введите целое число от %d до %d: ";

    private static final String NUMBER_OUT_OF_RANGE = "Число вне допустимого диапазона, попробуйте еще раз";

    private static final String INVALID_FORMAT_NUMBER = "Число недопустимого формата, попробуйте еще раз";

    private static final String CLIENT_ENTERED_VALUE = "Пользователь ввел значение N = {}";

    private static final String ERROR_PARSING_NUMBER = "Ошибка парсинга числа: {}";

    private static final int MIN_NUMBER = 1;

    private static final int MAX_NUMBER = 1_000_000_000;

    private DataReader() {
        throw new UnsupportedOperationException(UTILITY_CLASS);
    }

    public static int readNumber() {
        final Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            System.out.printf(ENTER_AN_INTEGER, MIN_NUMBER, MAX_NUMBER);
            final String input = scanner.nextLine();
            try {
                number = parseInt(input.trim());
                if (number >= MIN_NUMBER && number <= MAX_NUMBER) {
                    logger.info(CLIENT_ENTERED_VALUE, number);
                    return number;
                }
                System.out.println(NUMBER_OUT_OF_RANGE);
            } catch (NumberFormatException e) {
                logger.warn(ERROR_PARSING_NUMBER, input);
                System.out.println(INVALID_FORMAT_NUMBER);
            }
        }
    }
}
