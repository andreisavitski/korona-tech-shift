package by.shift.task2;

import by.shift.task2.descriptor.ShapeDescriptorStrategy;
import by.shift.task2.enums.ShapeType;
import by.shift.task2.io.DataReader;
import by.shift.task2.io.DataWriter;
import by.shift.task2.model.Shape;
import by.shift.task2.parser.CommandLineParser;
import by.shift.task2.parser.ShapeParserStrategy;
import by.shift.task2.resolver.ShapeDescriptorResolver;
import by.shift.task2.resolver.ShapeParserResolver;
import by.shift.task2.validator.DataValidator;
import by.shift.task2.validator.ShapeValidator;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static by.shift.task2.constant.ApplicationConstant.INPUT;
import static by.shift.task2.constant.ApplicationConstant.OUTPUT;
import static org.slf4j.LoggerFactory.getLogger;

public class ShapeApplication {

    private static final String START_PROGRAM = "Запуск программы с аргументами: {}";

    private static final String FINISH_PROGRAM = "Завершение программы";

    private static final String FILE_READ_SUCCESSFULLY = "Файл '{}' успешно прочитан. Количество строк: {}";

    private static final String TYPE_OF_SHAPE_IS_DETERMINED = "Определен тип фигуры: {}";

    private static final String SHAPE_SUCCESSFULLY_CREATED = "Фигура успешно создана: {}";

    private static final Logger logger = getLogger(ShapeApplication.class);

    public static void main(String[] args) {
        logger.info(START_PROGRAM, (Object) args);
        try {
            final Map<String, String> commandLineArguments = CommandLineParser.parseArgs(args);

            final String inputFile = commandLineArguments.get(INPUT);
            final String outputMethod = commandLineArguments.get(OUTPUT);

            final List<String> lines = DataReader.readLines(Path.of(inputFile));
            DataValidator.checkIsEmpty(lines);
            logger.info(FILE_READ_SUCCESSFULLY, inputFile, lines.size());

            final ShapeType shapeType = ShapeValidator.checkShapeType(lines);
            logger.info(TYPE_OF_SHAPE_IS_DETERMINED, shapeType);

            final ShapeParserStrategy shapeParserStrategy = ShapeParserResolver.getShapeParser(shapeType);
            final Shape shape = shapeParserStrategy.parse(lines);
            logger.info(SHAPE_SUCCESSFULLY_CREATED, shape.getName());

            final ShapeDescriptorStrategy<Shape> shapeDescriptorStrategy =
                    ShapeDescriptorResolver.getShapeDescriptorStrategy(shapeType);
            final String description = shapeDescriptorStrategy.describe(shape);

            DataWriter.write(description, outputMethod);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info(FINISH_PROGRAM);
    }
}
