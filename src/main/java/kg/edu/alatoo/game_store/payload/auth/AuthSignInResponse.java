package kg.edu.alatoo.game_store.payload.auth;

public record AuthSignInResponse(
        String accessToken,
        String refreshToken
) {
}
