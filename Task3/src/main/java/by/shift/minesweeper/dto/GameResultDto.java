package by.shift.minesweeper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public class GameResultDto {

    @NotBlank(message = "Требуется имя игрока")
    private String playerName;

    private boolean won;

    @PositiveOrZero(message = "Ходы должны быть нулевыми или положительными")
    private int moves;

    @PositiveOrZero(message = "Оставшиеся флаги должны быть нулевыми или положительными")
    private int remainingFlags;

    @NotNull(message = "Требуется время")
    @PastOrPresent(message = "Время не может быть в будущем")
    private LocalDateTime timestamp;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getRemainingFlags() {
        return remainingFlags;
    }

    public void setRemainingFlags(int remainingFlags) {
        this.remainingFlags = remainingFlags;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
