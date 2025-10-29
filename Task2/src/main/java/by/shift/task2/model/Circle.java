package by.shift.task2.model;

import lombok.Getter;

import static by.shift.task2.constant.ApplicationConstant.*;
import static by.shift.task2.validator.ShapeValidator.*;
import static java.lang.Math.*;
import static java.lang.String.*;

@Getter
public class Circle extends Shape {

    private final Double radius;

    public Circle(Double radius) {
        this.radius = radius;
        checkForPositiveNumbers(new String[]{valueOf(radius)});
    }

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
