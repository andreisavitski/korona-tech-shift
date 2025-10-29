package by.shift.task2.validator;

import by.shift.task2.enums.ShapeType;
import by.shift.task2.exeption.ApplicationException;
import lombok.experimental.UtilityClass;

import java.util.List;

import static by.shift.task2.enums.ShapeType.valueOf;
import static by.shift.task2.exeption.ApplicationError.*;
import static java.lang.Double.*;
import static java.util.Locale.*;

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
