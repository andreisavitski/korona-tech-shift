package by.shift.task2.validator;

import by.shift.task2.enums.ShapeType;
import by.shift.task2.exception.ApplicationException;
import lombok.experimental.UtilityClass;

import java.util.List;

import static by.shift.task2.enums.ShapeType.valueOf;
import static by.shift.task2.exception.ApplicationError.INCORRECT_NUMBER_FORMAT;
import static by.shift.task2.exception.ApplicationError.TRIANGLE_CAN_NOT_EXIST;
import static by.shift.task2.exception.ApplicationError.UNSUPPORTED_SHAPE_TYPE;
import static by.shift.task2.exception.ApplicationError.VALUES_MUST_BE_POSITIVE;
import static java.lang.Double.parseDouble;
import static java.util.Locale.ROOT;

@UtilityClass
public class ShapeValidator {

    public void checkIfNumbers(final String[] parts) {
        for (final String part : parts) {
            try {
                parseDouble(part);
            } catch (NumberFormatException e) {
                throw new ApplicationException(INCORRECT_NUMBER_FORMAT);
            }
        }
    }

    public void checkForPositiveNumbers(final String[] parts) {
        for (final String part : parts) {
            if (parseDouble(part) <= 0) {
                throw new ApplicationException(VALUES_MUST_BE_POSITIVE);
            }
        }
    }

    public void checkTriangleSides(final String[] parts) {
        final Double firstSide = parseDouble(parts[0]);
        final Double secondSide = parseDouble(parts[1]);
        final Double thirdSide = parseDouble(parts[2]);
        if (firstSide + secondSide <= thirdSide
                || firstSide + thirdSide <= secondSide
                || secondSide + thirdSide <= firstSide) {
            throw new ApplicationException(TRIANGLE_CAN_NOT_EXIST);
        }
    }

    public ShapeType checkShapeType(final List<String> lines) {
        final String typeRaw = lines.get(0).trim().toUpperCase(ROOT);
        final ShapeType shapeType;
        try {
            shapeType = valueOf(typeRaw);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(UNSUPPORTED_SHAPE_TYPE, typeRaw);
        }
        return shapeType;
    }
}
