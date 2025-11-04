package by.shift.minesweeper.mapper;

import by.shift.minesweeper.dto.GameResultDto;
import by.shift.minesweeper.model.GameResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GameResultMapper {

    @Mapping(source = "playerName", target = "playerName")
    @Mapping(source = "won", target = "won")
    @Mapping(source = "moves", target = "moves")
    @Mapping(source = "remainingFlags", target = "remainingFlags")
    @Mapping(source = "timestamp", target = "timestamp")
    GameResult toGameResult(GameResultDto dto);

    @Mapping(source = "playerName", target = "playerName")
    @Mapping(source = "won", target = "won")
    @Mapping(source = "moves", target = "moves")
    @Mapping(source = "remainingFlags", target = "remainingFlags")
    @Mapping(source = "timestamp", target = "timestamp")
    GameResultDto toDto(GameResult gameResult);

    List<GameResultDto> toDtoList(List<GameResult> gameResults);
}
