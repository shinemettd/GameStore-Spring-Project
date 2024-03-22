package kg.edu.alatoo.game_store.mapper;

import kg.edu.alatoo.game_store.entity.Game;
import kg.edu.alatoo.game_store.payload.game.GameRequest;
import kg.edu.alatoo.game_store.payload.game.GameResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    Game toEntity(GameRequest gameRequest);

    GameResponse toModel(Game game);
}
