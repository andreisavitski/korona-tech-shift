package by.shift.task2.validator;

import by.shift.task2.enums.ShapeType;
import lombok.experimental.UtilityClass;

import java.util.List;

import static by.shift.task2.constant.ApplicationConstant.INCORRECT_NUMBER_FORMAT;
import static by.shift.task2.constant.ApplicationConstant.TRIANGLE_CAN_NOT_EXIST;
import static by.shift.task2.constant.ApplicationConstant.UNSUPPORTED_SHAPE_TYPE;
import static by.shift.task2.constant.ApplicationConstant.VALUES_MUST_BE_POSITIVE;
import static by.shift.task2.enums.ShapeType.valueOf;
import static java.lang.Double.parseDouble;
import static java.util.Locale.ROOT;

@UtilityClass
public class ShapeValidator {

    public static void checkIfNumbers(final String[] parts) {
        for (final String part : parts) {
            try {
                parseDouble(part);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INCORRECT_NUMBER_FORMAT);
            }
        }
    }

    public static void checkForPositiveNumbers(final String[] parts) {
        for (final String part : parts) {
            if (parseDouble(part) <= 0) {
                throw new IllegalArgumentException(VALUES_MUST_BE_POSITIVE);
            }
        }
    }

    public static void checkTriangleSides(final String[] parts) {
        final Double firstSide = parseDouble(parts[0]);
        final Double secondSide = parseDouble(parts[1]);
        final Double thirdSide = parseDouble(parts[2]);
        if (firstSide + secondSide <= thirdSide
                || firstSide + thirdSide <= secondSide
                || secondSide + thirdSide <= firstSide) {
            throw new IllegalArgumentException(TRIANGLE_CAN_NOT_EXIST);
        }
    }

    public static ShapeType checkShapeType(final List<String> lines) {
        final String typeRaw = lines.get(0).trim().toUpperCase(ROOT);
        final ShapeType shapeType;
        try {
            shapeType = valueOf(typeRaw);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(UNSUPPORTED_SHAPE_TYPE + typeRaw);
        }
        return shapeType;
    }
}
