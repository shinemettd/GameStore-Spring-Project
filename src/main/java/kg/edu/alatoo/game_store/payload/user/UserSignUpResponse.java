package kg.edu.alatoo.game_store.payload.user;

import kg.edu.alatoo.game_store.entity.Game;

import java.util.Set;

public record UserSignUpResponse(
        Long id,
        String login,
        String nickname,
        Double balance,
        Set<Game> games
) {
}
