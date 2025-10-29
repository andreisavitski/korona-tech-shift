package by.shift.task2.model;

import lombok.Getter;

import static by.shift.task2.constant.ApplicationConstant.*;
import static by.shift.task2.validator.ShapeValidator.*;
import static java.lang.Math.*;
import static java.lang.String.*;

@Getter
public class Rectangle extends Shape {

    private final Double firstSide;

    private final Double secondSide;

    public Rectangle(Double firstSide, Double secondSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        checkForPositiveNumbers(new String[]{valueOf(firstSide), valueOf(secondSide)});
    }

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
