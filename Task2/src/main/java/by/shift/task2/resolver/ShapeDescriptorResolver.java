package by.shift.task2.resolver;

import by.shift.task2.descriptor.ShapeDescriptorStrategy;
import by.shift.task2.descriptor.impl.CircleDescriptor;
import by.shift.task2.descriptor.impl.RectangleDescriptor;
import by.shift.task2.descriptor.impl.TriangleDescriptor;
import by.shift.task2.enums.ShapeType;
import by.shift.task2.model.Shape;

import java.util.Map;

import static by.shift.task2.enums.ShapeType.*;

public class ShapeDescriptorResolver {

    private static final String UNCHECKED = "unchecked";

    private final Map<ShapeType, ShapeDescriptorStrategy<? extends Shape>> shapeDescriptorStrategyMap = Map.of(
            CIRCLE, new CircleDescriptor(),
            RECTANGLE, new RectangleDescriptor(),
            TRIANGLE, new TriangleDescriptor()
    );

    @SuppressWarnings(UNCHECKED)
    public <T extends Shape> ShapeDescriptorStrategy<T> getShapeDescriptorStrategy(final ShapeType shapeType) {
        return (ShapeDescriptorStrategy<T>) shapeDescriptorStrategyMap.get(shapeType);
    }
}
