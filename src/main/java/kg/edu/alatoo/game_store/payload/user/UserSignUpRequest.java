package kg.edu.alatoo.game_store.payload.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSignUpRequest(
        @NotBlank(message = "Login can't be empty")
        @Size(min = 8, max = 100, message = "Login must contain from 8 to 100 symbols")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login might contain only letters and numbers ")
        String login,

        @NotBlank(message = "Nickname can't be empty")
        @Size(min = 1, max = 100, message = "Nickname must contain from 1 to 100 symbols")
        String nickname,

        @NotBlank(message = "Password can't be empty")
        @Size(min = 8, max = 100, message = "Password must contain from 8 to 100 symbols")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[A-Z]).*$", message = "Password must contain at least 1 uppercase letter, 1 number and 1 lowercase letter")
        String password,

        @JsonProperty("confirm_password")
        String confirmPassword
) {
}
