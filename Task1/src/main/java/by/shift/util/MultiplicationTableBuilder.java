package by.shift.util;

import static by.shift.constant.ApplicationConstants.*;
import static by.shift.util.DataReader.readingData;
import static by.shift.util.RowSeparator.buildRowSeparator;

public class MultiplicationTableBuilder {

    private MultiplicationTableBuilder() {
        throw new UnsupportedOperationException(UTILITY_CLASS);
    }

    public static void buildMultiplicationTableInConsole() {
        int tableSize = readingData();
        int firstColumnWidth = String.valueOf(tableSize).length();
        int cellWidth = String.valueOf(tableSize * tableSize).length();
        final String rowSeparator = buildRowSeparator(tableSize, cellWidth, firstColumnWidth);
        System.out.printf("%" + (firstColumnWidth + EXTRA_PADDING) + "s", "");
        for (int i = TABLE_SIZE_MIN; i <= tableSize; i++) {
            System.out.printf("|%" + cellWidth + "d", i);
        }
        System.out.println("\n" + rowSeparator);
        for (int i = TABLE_SIZE_MIN; i <= tableSize; i++) {
            System.out.printf("%" + firstColumnWidth + "d ", i);
            for (int j = TABLE_SIZE_MIN; j <= tableSize; j++) {
                int result = i * j;
                System.out.printf("|%" + cellWidth + "d", result);
            }
            System.out.println("\n" + rowSeparator);
        }
    }
}
