package kg.edu.alatoo.game_store.payload.user;

public record UserUpdatePasswordRequest(
        String oldPassword,
        String newPassword,
        String confirmationNewPassword
) {
}
