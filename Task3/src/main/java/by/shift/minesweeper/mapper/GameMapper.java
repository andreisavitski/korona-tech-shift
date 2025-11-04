package by.shift.minesweeper.mapper;

import by.shift.minesweeper.dto.GameDto;
import by.shift.minesweeper.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GameMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rows", target = "rows")
    @Mapping(source = "cols", target = "cols")
    @Mapping(source = "minesCount", target = "minesCount")
    @Mapping(source = "flagCount", target = "flagCount")
    @Mapping(source = "flaggedMines", target = "flaggedMines")
    @Mapping(source = "board", target = "board")
    @Mapping(source = "gameOver", target = "gameOver")
    GameDto toGameDto(Game game);
}
