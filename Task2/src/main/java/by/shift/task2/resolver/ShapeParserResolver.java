package by.shift.task2.resolver;

import by.shift.task2.enums.ShapeType;
import by.shift.task2.parser.ShapeParserStrategy;
import by.shift.task2.parser.impl.CircleParser;
import by.shift.task2.parser.impl.RectangleParser;
import by.shift.task2.parser.impl.TriangleParser;

import java.util.Map;

import static by.shift.task2.enums.ShapeType.CIRCLE;
import static by.shift.task2.enums.ShapeType.RECTANGLE;
import static by.shift.task2.enums.ShapeType.TRIANGLE;

public class ShapeParserResolver {

    private final Map<ShapeType, ShapeParserStrategy> shapeParsers = Map.of(
            CIRCLE, new CircleParser(),
            RECTANGLE, new RectangleParser(),
            TRIANGLE, new TriangleParser()
    );

    public ShapeParserStrategy getShapeParser(final ShapeType shapeType) {
        return shapeParsers.get(shapeType);
    }
}
