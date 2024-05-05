package kg.edu.alatoo.game_store.payload.user;

public record UserSignInRequest(
        String username,
        String password
) {
}
