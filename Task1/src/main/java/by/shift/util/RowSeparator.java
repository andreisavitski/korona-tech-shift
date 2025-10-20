package by.shift.util;

import static by.shift.constant.ApplicationConstants.*;

public class RowSeparator {

    private RowSeparator() {
        throw new UnsupportedOperationException(UTILITY_CLASS);
    }

    public static String buildRowSeparator(int tableSize, int cellWidth, int firstColumnWidth) {
        final StringBuilder line = new StringBuilder((cellWidth + EXTRA_PADDING) * (tableSize + EXTRA_PADDING));
        line.append(DASH.repeat(firstColumnWidth + EXTRA_PADDING));
        for (int i = TABLE_SIZE_MIN; i <= tableSize; i++) {
            line.append(PLUS).append(DASH.repeat(cellWidth));
        }
        return line.toString();
    }
}
