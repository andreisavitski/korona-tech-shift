package by.shift.task2.model.impl;

import by.shift.task2.model.Shape;

import static by.shift.task2.constant.ApplicationConstant.CIRCLE;
import static java.lang.Math.PI;

public record Circle(Double radius) implements Shape {

    @Override
    public String getName() {
        return CIRCLE;
    }

    @Override
    public Double calculateArea() {
        return PI * radius * radius;
    }

    @Override
    public Double calculatePerimeter() {
        return 2 * PI * radius;
    }

    public Double calculateDiameter() {
        return 2 * radius;
    }
}
