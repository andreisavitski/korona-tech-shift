package by.shift.task2.model.impl;

import by.shift.task2.model.Shape;

import static by.shift.task2.constant.ApplicationConstant.RECTANGLE;
import static java.lang.Math.hypot;

public record Rectangle(Double firstSide, Double secondSide) implements Shape {

    @Override
    public String getName() {
        return RECTANGLE;
    }

    @Override
    public Double calculateArea() {
        return firstSide * secondSide;
    }

    @Override
    public Double calculatePerimeter() {
        return 2 * (firstSide + secondSide);
    }

    public Double calculateDiagonal() {
        return hypot(firstSide, secondSide);
    }

    public Double getLength() {
        if (firstSide > secondSide) return firstSide;
        else return secondSide;
    }

    public Double getWidth() {
        if (firstSide > secondSide) return secondSide;
        else return firstSide;
    }
}
