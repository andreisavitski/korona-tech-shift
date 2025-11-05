package by.shift.task2.model;

import by.shift.task2.exception.ApplicationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static by.shift.task2.constant.ApplicationConstant.TRIANGLE;
import static java.lang.Math.acos;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleTest {

    @ParameterizedTest
    @CsvSource({
            "3.0,4.0,5.0",
            "999.0,999.0,999.0"
    })
    void triangleParameterizedTests(Double firstSide, Double secondSide, Double thirdSide) {
        Triangle triangle = new Triangle(firstSide, secondSide, thirdSide);

        assertEquals(firstSide, triangle.getFirstSide());
        assertEquals(secondSide, triangle.getSecondSide());
        assertEquals(thirdSide, triangle.getThirdSide());

        assertEquals(TRIANGLE, triangle.getName());

        assertEquals(firstSide + secondSide + thirdSide, triangle.calculatePerimeter());

        Double semiperimeter = (firstSide + secondSide + thirdSide) / 2;
        double expectedArea = sqrt(semiperimeter * (semiperimeter - firstSide) *
                (semiperimeter - secondSide) * (semiperimeter - thirdSide));
        assertEquals(expectedArea, triangle.calculateArea(), 1e-9);

        double oppositeAngleFirstSide = toDegrees(acos(
                        (secondSide * secondSide + thirdSide * thirdSide - firstSide * firstSide) /
                                (2 * secondSide * thirdSide)
                )
        );
        double oppositeAngleSecondSide = toDegrees(acos(
                        (firstSide * firstSide + thirdSide * thirdSide - secondSide * secondSide) /
                                (2 * firstSide * thirdSide)
                )
        );
        double oppositeAngleThirdSide = 180.0 - oppositeAngleFirstSide - oppositeAngleSecondSide;

        assertEquals(oppositeAngleFirstSide, triangle.getOppositeAngleFirstSide(), 1e-6);
        assertEquals(oppositeAngleSecondSide, triangle.getOppositeAngleSecondSide(), 1e-6);
        assertEquals(oppositeAngleThirdSide, triangle.getOppositeAngleThirdSide(), 1e-6);
    }

    @ParameterizedTest
    @CsvSource({
            "-3.0,4.0,5.0",
            "3310.0,-4.0,5.0"
    })
    void constructor_negativeSide_shouldThrowApplicationException(Double firstSide, Double secondSide, Double thirdSide) {
        assertThrows(ApplicationException.class, () -> new Triangle(firstSide, secondSide, thirdSide));
    }

    @ParameterizedTest
    @CsvSource({
            "1.0,2.0,5.0",
            "2.0,3.0,6.0"
    })
    void constructor_invalidTriangle_shouldThrowApplicationException(Double firstSide, Double secondSide, Double thirdSide) {
        assertThrows(ApplicationException.class, () -> new Triangle(firstSide, secondSide, thirdSide));
    }
}
