package by.shift.task2.descriptor.impl;

import by.shift.task2.descriptor.ShapeDescriptorStrategy;
import by.shift.task2.model.Circle;

import static java.lang.String.format;

public class CircleDescriptor implements ShapeDescriptorStrategy<Circle> {

    private static final String CIRCLE_FORMAT = """
            Тип фигуры: %s
            Площадь: %.2f кв. см
            Периметр: %.2f см
            Радиус: %.2f см
            Диаметр: %.2f см
            """;

    @Override
    public String describe(final Circle circle) {
        return format(CIRCLE_FORMAT,
                circle.getName(),
                circle.calculateArea(),
                circle.calculatePerimeter(),
                circle.getRadius(),
                circle.calculateDiameter()
        );
    }
}
