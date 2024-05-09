package kg.edu.alatoo.game_store.payload.auth;

public record JwtTokenResponse(
        String accessToken,
        String token
) {
}
