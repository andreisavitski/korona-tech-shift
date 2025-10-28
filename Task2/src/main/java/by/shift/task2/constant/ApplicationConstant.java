package by.shift.task2.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstant {

    public static final String CIRCLE = "КРУГ";

    public static final String RECTANGLE = "ПРЯМОУГОЛЬНИК";

    public static final String TRIANGLE = "ТРЕУГОЛЬНИК";

    public static final String INPUT = "input";

    public static final String OUTPUT = "output";

    public static final String INPUT_FLAG = "--input";

    public static final String OUTPUT_FLAG = "--output";

    public static final String ASSIGNMENT = "=";

    public static final String EMPTY_LINE = "";

    public static final Integer NUMBER_OF_ARGUMENT_SPLITTINGS = 2;

    public static final Integer INDEX_SECOND_LINE = 1;

    public static final String CONSOLE = "console";

    public static final Integer NUMBER_OF_CIRCLE_PARAMETERS = 1;

    public static final Integer NUMBER_OF_RECTANGLE_PARAMETERS = 2;

    public static final Integer NUMBER_OF_TRIANGLE_PARAMETERS = 3;

    public static final String ANY_WHITESPACE_CHARACTER = "\\s+";

    public static final String RESULT_IS_WRITTEN_TO_FILE = "Результат записан в файл: {}";

    public static final String RESULT_IS_WRITTEN_TO_CONSOLE = "Результат записан в консоль";

    public static final String START_PROGRAM = "Запуск программы с аргументами: {}";

    public static final String FINISH_PROGRAM = "Завершение программы";

    public static final String INVALID_ARGUMENT_FORMAT = "Неверный формат аргумента: ";

    public static final String UNKNOWN_ARGUMENT = "Неизвестный аргумент: ";

    public static final String ARGUMENTS_REQUIRED = "Необходимо оба аргумента --input и --output";

    public static final String FILE_NOT_FOUND = "Файл не найден по пути: ";

    public static final String FILE_READ_SUCCESSFULLY = "Файл '{}' успешно прочитан. Количество строк: {}";

    public static final String TYPE_OF_SHAPE_IS_DETERMINED = "Определен тип фигуры: {}";

    public static final String SHAPE_SUCCESSFULLY_CREATED = "Фигура успешно создана: {}";

    public static final String INCORRECT_NUMBER_OF_PARAMETERS = "Неверное количество параметров для данной фигуры";

    public static final String UNSUPPORTED_SHAPE_TYPE = "Неподдерживаемый формат фигуры: {}";

    public static final String INCORRECT_NUMBER_FORMAT = "Неверный формат числа";

    public static final String VALUES_MUST_BE_POSITIVE = "Значения сторон должны быть положительными";

    public static final String TRIANGLE_CAN_NOT_EXIST = "Треугольник с такими параметрами не может существовать";

    public static final String FILE_IS_EMPTY = "Файл пустой";

    public static final String ERROR_EXECUTING_PROGRAM = "Ошибка при выполнении программы: {}";

    public static final String NO_ARGUMENTS = """
            Нет аргументов. Необходимо использовать --input=<file> и --output=<method>
            """;

    public static final String CIRCLE_FORMAT = """
            Тип фигуры: %s
            Площадь: %.2f кв. см
            Периметр: %.2f см
            Радиус: %.2f см
            Диаметр: %.2f см
            """;

    public static final String RECTANGLE_FORMAT = """
            Тип фигуры: %s
            Площадь: %.2f кв. см
            Периметр: %.2f см
            Длина: %.2f см
            Ширина: %.2f см
            Диагональ: %.2f см
            """;

    public static final String TRIANGLE_FORMAT = """
            Тип фигуры: %s
            Площадь: %.2f кв. см
            Периметр: %.2f см
            Первая сторона: %.2f см, противолежащий угол: %.2f°
            Вторая сторона: %.2f см, противолежащий угол: %.2f°
            Третья сторона: %.2f см, противолежащий угол: %.2f°
            """;
}
