package by.shift.task2.descriptor.impl;

import by.shift.task2.descriptor.ShapeDescriptorStrategy;
import by.shift.task2.model.Shape;
import by.shift.task2.model.impl.Rectangle;

import static by.shift.task2.constant.ApplicationConstant.RECTANGLE_FORMAT;
import static java.lang.String.format;

public class RectangleDescriptor implements ShapeDescriptorStrategy {

    @Override
    public String describe(final Shape shape) {
        final Rectangle rectangle = (Rectangle) shape;
        return format(RECTANGLE_FORMAT,
                rectangle.getName(),
                rectangle.calculateArea(),
                rectangle.calculatePerimeter(),
                rectangle.getLength(),
                rectangle.getWidth(),
                rectangle.calculateDiagonal());
    }
}
