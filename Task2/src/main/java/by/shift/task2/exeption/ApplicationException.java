package by.shift.task2.exeption;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final String message;

    public ApplicationException(AppError appError) {
        this(appError, appError.getMessage());
    }

    public ApplicationException(AppError appError, String message) {
        super(message != null ? message : appError.getMessage());
        this.message = appError.getMessage();
    }
}
