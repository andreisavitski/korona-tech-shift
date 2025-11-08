package by.shift.task2.parser;

import by.shift.task2.model.Shape;

import java.util.List;

public interface ShapeParserStrategy {

    Shape parse(List<String> lines);
}
