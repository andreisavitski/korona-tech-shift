package by.shift.task2.descriptor.impl;

import by.shift.task2.descriptor.ShapeDescriptorStrategy;
import by.shift.task2.model.Triangle;

import static java.lang.String.format;

public class TriangleDescriptor implements ShapeDescriptorStrategy<Triangle> {

    private static final String TRIANGLE_FORMAT = """
            Тип фигуры: %s
            Площадь: %.2f кв. см
            Периметр: %.2f см
            Первая сторона: %.2f см, противолежащий угол: %.2f°
            Вторая сторона: %.2f см, противолежащий угол: %.2f°
            Третья сторона: %.2f см, противолежащий угол: %.2f°
            """;

    @Override
    public String describe(final Triangle triangle) {
        return format(TRIANGLE_FORMAT,
                triangle.getName(),
                triangle.calculateArea(),
                triangle.calculatePerimeter(),
                triangle.getFirstSide(),
                triangle.getOppositeAngleFirstSide(),
                triangle.getSecondSide(),
                triangle.getOppositeAngleSecondSide(),
                triangle.getThirdSide(),
                triangle.getOppositeAngleThirdSide()
        );
    }
}
