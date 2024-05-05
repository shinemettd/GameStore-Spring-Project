package kg.edu.alatoo.game_store.payload.auth;

public record AuthSignInRequest(
        String username,
        String password
) {
}
