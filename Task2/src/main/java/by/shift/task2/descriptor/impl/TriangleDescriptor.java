package by.shift.task2.descriptor.impl;

import by.shift.task2.descriptor.ShapeDescriptorStrategy;
import by.shift.task2.model.Shape;
import by.shift.task2.model.impl.Triangle;

import static by.shift.task2.constant.ApplicationConstant.TRIANGLE_FORMAT;
import static java.lang.String.format;

public class TriangleDescriptor implements ShapeDescriptorStrategy {

    @Override
    public String describe(final Shape shape) {
        final Triangle triangle = (Triangle) shape;
        return format(TRIANGLE_FORMAT,
                triangle.getName(),
                triangle.calculateArea(),
                triangle.calculatePerimeter(),
                triangle.firstSide(),
                triangle.getOppositeAngleFirstSide(),
                triangle.secondSide(),
                triangle.getOppositeAngleSecondSide(),
                triangle.thirdSide(),
                triangle.getOppositeAngleThirdSide()
        );
    }
}
