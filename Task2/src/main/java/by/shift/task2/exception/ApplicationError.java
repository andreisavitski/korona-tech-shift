package by.shift.task2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
@AllArgsConstructor
public enum ApplicationError implements AppError, Supplier<ApplicationException> {

    FILE_NOT_FOUND("Файл не найден по пути: "),

    INSUFFICIENT_DATA("Недостаточно данных"),

    INVALID_ARGUMENT_FORMAT("Неверный формат аргумента: "),

    UNKNOWN_ARGUMENT("Неизвестный аргумент: "),

    ARGUMENTS_REQUIRED("Необходимо оба аргумента --input и --output"),

    INCORRECT_NUMBER_OF_PARAMETERS("Неверное количество параметров для данной фигуры"),

    UNSUPPORTED_SHAPE_TYPE("Неподдерживаемый формат фигуры: {}"),

    INCORRECT_NUMBER_FORMAT("Неверный формат числа"),

    VALUES_MUST_BE_POSITIVE("Значения должны быть положительными"),

    TRIANGLE_CAN_NOT_EXIST("Треугольник с такими параметрами не может существовать"),

    ERROR_EXECUTING_PROGRAM("Ошибка при выполнении программы: {}"),

    NO_ARGUMENTS("Нет аргументов. Необходимо использовать --input=<file> и --output=<method>");

    private final String message;

    @Override
    public ApplicationException get() {
        return new ApplicationException(this);
    }
}
