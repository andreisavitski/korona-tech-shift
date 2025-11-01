package by.shift.task2.model;

import by.shift.task2.validator.ShapeValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static by.shift.task2.constant.ApplicationConstant.CIRCLE;
import static java.lang.Math.PI;
import static java.lang.String.valueOf;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Circle extends Shape {

    private final Double radius;

    public Circle(Double radius) {
        this.radius = radius;
        ShapeValidator.checkForPositiveNumbers(new String[]{valueOf(radius)});
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
