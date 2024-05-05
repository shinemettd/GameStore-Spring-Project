package kg.edu.alatoo.game_store.payload.auth;

import kg.edu.alatoo.game_store.entity.Game;

import java.util.Set;

public record AuthSignUpResponse(
        Long id,
        String login,
        String username,
        Double balance,
        Set<Game> games
) {
}
