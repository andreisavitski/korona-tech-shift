package by.shift.task2.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final String errorMessage;

    public ApplicationException(AppError appError) {
        this(appError, appError.getMessage());
    }

    public ApplicationException(AppError appError, String additionalInformation) {
        super(additionalInformation != null ? additionalInformation : appError.getMessage());
        this.errorMessage = appError.getMessage();
    }
}
