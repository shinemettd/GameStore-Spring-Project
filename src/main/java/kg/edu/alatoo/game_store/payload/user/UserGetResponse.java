package kg.edu.alatoo.game_store.payload.user;

import kg.edu.alatoo.game_store.entity.Game;
import org.springframework.data.domain.Page;

public record UserGetResponse(
        String id,

        String nickname,

        Page<Game> gamesList
) {
}
