package by.shift.task2.parser.impl;

import by.shift.task2.model.Rectangle;
import by.shift.task2.model.Shape;
import by.shift.task2.parser.ShapeParserStrategy;
import by.shift.task2.util.Separator;
import by.shift.task2.validator.ShapeValidator;

import java.util.List;

import static java.lang.Double.parseDouble;

public class RectangleParser implements ShapeParserStrategy {

    private static final Integer NUMBER_OF_RECTANGLE_PARAMETERS = 2;

    @Override
    public Shape parse(final List<String> lines) {
        final String[] parts = Separator.splitParameters(lines, NUMBER_OF_RECTANGLE_PARAMETERS);
        ShapeValidator.checkIfNumbers(parts);
        ShapeValidator.checkForPositiveNumbers(parts);
        return new Rectangle(parseDouble(parts[0]), parseDouble(parts[1]));
    }
}
