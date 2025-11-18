package by.shift.minesweeper.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validGameResultShouldPassValidation() {
        GameResultDto dto = createValidDto();

        Set<ConstraintViolation<GameResultDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void playerNameBlankShouldFailValidation() {
        GameResultDto dto = createValidDto();
        dto.setPlayerName("");

        Set<ConstraintViolation<GameResultDto>> violations = validator.validate(dto);
        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("Требуется имя игрока");
    }

    @Test
    void movesNegativeShouldFailValidation() {
        GameResultDto dto = createValidDto();
        dto.setMoves(-1);

        Set<ConstraintViolation<GameResultDto>> violations = validator.validate(dto);
        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("Ходы должны быть нулевыми или положительными");
    }

    @Test
    void remainingFlagsNegativeShouldFailValidation() {
        GameResultDto dto = createValidDto();
        dto.setRemainingFlags(-1);

        Set<ConstraintViolation<GameResultDto>> violations = validator.validate(dto);
        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("Оставшиеся флаги должны быть нулевыми или положительными");
    }

    @Test
    void timestampNullShouldFailValidation() {
        GameResultDto dto = createValidDto();
        dto.setTimestamp(null);

        Set<ConstraintViolation<GameResultDto>> violations = validator.validate(dto);
        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("Требуется время");
    }

    @Test
    void timestampInFutureShouldFailValidation() {
        GameResultDto dto = createValidDto();
        dto.setTimestamp(LocalDateTime.now().plusDays(1));

        Set<ConstraintViolation<GameResultDto>> violations = validator.validate(dto);
        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("Время не может быть в будущем");
    }

    private GameResultDto createValidDto() {
        GameResultDto dto = new GameResultDto();
        dto.setPlayerName("Player");
        dto.setWon(true);
        dto.setMoves(5);
        dto.setRemainingFlags(3);
        dto.setTimestamp(LocalDateTime.now());
        return dto;
    }
}