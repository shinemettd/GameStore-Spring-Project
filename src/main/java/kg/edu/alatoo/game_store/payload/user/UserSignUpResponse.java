package kg.edu.alatoo.game_store.payload.user;

public record UserSignUpResponse(
        Long id,
        String login,
        String nickname
) {
}
