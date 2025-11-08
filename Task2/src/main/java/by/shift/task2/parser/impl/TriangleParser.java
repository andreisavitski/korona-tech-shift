package by.shift.task2.parser.impl;

import by.shift.task2.model.Shape;
import by.shift.task2.model.Triangle;
import by.shift.task2.parser.ShapeParserStrategy;
import by.shift.task2.util.Separator;
import by.shift.task2.validator.ShapeValidator;

import java.util.List;

import static java.lang.Double.parseDouble;

public class TriangleParser implements ShapeParserStrategy {

    private static final Integer NUMBER_OF_TRIANGLE_PARAMETERS = 3;

    @Override
    public Shape parse(final List<String> lines) {
        final String[] parts = Separator.splitParameters(lines, NUMBER_OF_TRIANGLE_PARAMETERS);
        ShapeValidator.checkIfNumbers(parts);
        ShapeValidator.checkForPositiveNumbers(parts);
        ShapeValidator.checkTriangleSides(parts);
        return new Triangle(parseDouble(parts[0]), parseDouble(parts[1]), parseDouble(parts[2]));
    }
}
