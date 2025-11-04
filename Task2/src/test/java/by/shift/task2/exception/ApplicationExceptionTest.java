package by.shift.task2.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationExceptionTest {

    private final ApplicationError applicationError = ApplicationError.FILE_NOT_FOUND;

    @Test
    void constructor_AppErrorWithoutAdditionalInformation_shouldSetErrorMessage() {
        ApplicationException ex = new ApplicationException(applicationError);
        assertEquals(applicationError.getMessage(), ex.getErrorMessage());
        assertEquals(applicationError.getMessage(), ex.getMessage());
    }

    @Test
    void constructor_AppErrorWithAdditionalInformation_shouldSetSuperMessageOnly() {
        String additionalInformation = "Дополнительная информация";
        ApplicationException ex = new ApplicationException(applicationError, additionalInformation);
        assertEquals(applicationError.getMessage(), ex.getErrorMessage());
        assertEquals(additionalInformation, ex.getMessage());
    }
}
