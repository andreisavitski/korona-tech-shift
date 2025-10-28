package by.shift.task2.descriptor.impl;

import by.shift.task2.descriptor.ShapeDescriptorStrategy;
import by.shift.task2.model.Shape;
import by.shift.task2.model.impl.Circle;

import static by.shift.task2.constant.ApplicationConstant.CIRCLE_FORMAT;
import static java.lang.String.format;

public class CircleDescriptor implements ShapeDescriptorStrategy {

    @Override
    public String describe(final Shape shape) {
        final Circle circle = (Circle) shape;
        return format(CIRCLE_FORMAT,
                circle.getName(),
                circle.calculateArea(),
                circle.calculatePerimeter(),
                circle.radius(),
                circle.calculateDiameter()
        );
    }
}
