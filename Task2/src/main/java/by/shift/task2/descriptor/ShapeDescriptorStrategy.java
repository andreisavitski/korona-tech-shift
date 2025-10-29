package by.shift.task2.descriptor;

import by.shift.task2.model.Shape;

public interface ShapeDescriptorStrategy<T extends Shape> {

    String describe(T shape);
}
