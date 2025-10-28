package by.shift.task2.model.impl;

import by.shift.task2.model.Shape;

import static by.shift.task2.constant.ApplicationConstant.TRIANGLE;
import static java.lang.Math.acos;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;

public record Triangle(Double firstSide, Double secondSide, Double thirdSide) implements Shape {

    @Override
    public String getName() {
        return TRIANGLE;
    }

    @Override
    public Double calculateArea() {
        final Double semiperimeter = (firstSide + secondSide + thirdSide) / 2;
        return sqrt(
                semiperimeter * (semiperimeter - firstSide) * (semiperimeter - secondSide) * (semiperimeter - thirdSide)
        );
    }

    @Override
    public Double calculatePerimeter() {
        return firstSide + secondSide + thirdSide;
    }

    public Double getOppositeAngleFirstSide() {
        return calculateOppositeAngle(firstSide, secondSide, thirdSide);
    }

    public Double getOppositeAngleSecondSide() {
        return calculateOppositeAngle(secondSide, thirdSide, firstSide);
    }

    public Double getOppositeAngleThirdSide() {
        return calculateOppositeAngle(thirdSide, firstSide, secondSide);
    }

    private Double calculateOppositeAngle(final Double targetSide,
                                          final Double leftSide,
                                          final Double rightSide) {
        final double cosinus =
                (leftSide * leftSide + rightSide * rightSide - targetSide * targetSide) / (2 * leftSide * rightSide);
        final double radians = acos(cosinus);
        return toDegrees(radians);
    }
}
