package kg.edu.alatoo.game_store.payload.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @NotBlank(message = "Nickname can't be empty")
        @Size(min = 8, max = 100, message = "Nickname must contain from 8 to 100 symbols")
        String username,
        @NotBlank(message = "Password can't be empty")
        @Size(min = 8, max = 100, message = "Password must contain from 8 to 100 symbols")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[A-Z]).*$", message = "Password must contain at least 1 uppercase letter, 1 number and 1 lowercase letter")
        String password,
        @JsonProperty("confirmation_password")
        String confirmationPassword
) {
}
