package by.shift.task2.descriptor.impl;

import by.shift.task2.descriptor.ShapeDescriptorStrategy;
import by.shift.task2.model.Rectangle;

import static java.lang.String.format;

public class RectangleDescriptor implements ShapeDescriptorStrategy<Rectangle> {

    private static final String RECTANGLE_FORMAT = """
            Тип фигуры: %s
            Площадь: %.2f кв. см
            Периметр: %.2f см
            Длина: %.2f см
            Ширина: %.2f см
            Диагональ: %.2f см
            """;

    @Override
    public String describe(final Rectangle rectangle) {
        return format(RECTANGLE_FORMAT,
                rectangle.getName(),
                rectangle.calculateArea(),
                rectangle.calculatePerimeter(),
                rectangle.getLength(),
                rectangle.getWidth(),
                rectangle.calculateDiagonal());
    }
}
