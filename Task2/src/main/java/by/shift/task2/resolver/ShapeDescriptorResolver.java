package by.shift.task2.resolver;

import by.shift.task2.descriptor.ShapeDescriptorStrategy;
import by.shift.task2.descriptor.impl.CircleDescriptor;
import by.shift.task2.descriptor.impl.RectangleDescriptor;
import by.shift.task2.descriptor.impl.TriangleDescriptor;
import by.shift.task2.enums.ShapeType;

import java.util.Map;

import static by.shift.task2.enums.ShapeType.CIRCLE;
import static by.shift.task2.enums.ShapeType.RECTANGLE;
import static by.shift.task2.enums.ShapeType.TRIANGLE;

public class ShapeDescriptorResolver {

    private final Map<ShapeType, ShapeDescriptorStrategy> shapeDescriptorStrategyMap = Map.of(
            CIRCLE, new CircleDescriptor(),
            RECTANGLE, new RectangleDescriptor(),
            TRIANGLE, new TriangleDescriptor()
    );

    public ShapeDescriptorStrategy getShapeDescriptorStrategy(final ShapeType shapeType) {
        return shapeDescriptorStrategyMap.get(shapeType);
    }
}
