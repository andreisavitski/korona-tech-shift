package by.shift.util;

import java.util.Scanner;

import static by.shift.constant.ApplicationConstants.*;
import static java.lang.Integer.parseInt;

public class DataReader {

    private DataReader() {
        throw new UnsupportedOperationException(UTILITY_CLASS);
    }

    public static int readingData() {
        final Scanner scanner = new Scanner(System.in);
        int tableSize;
        while (true) {
            System.out.println("Введите целое число от " + TABLE_SIZE_MIN + " до " + TABLE_SIZE_MAX + ": ");
            final String input = scanner.nextLine();
            try {
                tableSize = parseInt(input.trim());
                if (tableSize >= TABLE_SIZE_MIN && tableSize <= TABLE_SIZE_MAX) {
                    break;
                } else {
                    System.out.println("Число вне допустимого диапазона, попробуйте еще раз!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод, попробуйте еще раз!!!");
            }
        }
        scanner.close();
        return tableSize;
    }
}
