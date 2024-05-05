package kg.edu.alatoo.game_store.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.edu.alatoo.game_store.payload.auth.AuthSignInRequest;
import kg.edu.alatoo.game_store.payload.auth.AuthSignInResponse;
import kg.edu.alatoo.game_store.payload.auth.AuthSignUpRequest;
import kg.edu.alatoo.game_store.payload.auth.AuthSignUpResponse;
import kg.edu.alatoo.game_store.service.AuthService;
import kg.edu.alatoo.game_store.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Auth Controller",
        description = "Controller for operations with auth"
)
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @Operation(
            summary = "Registration"
    )
    @PostMapping("/register")
    public ResponseEntity<AuthSignUpResponse> signUp(@Valid @RequestBody AuthSignUpRequest authSignUpRequest) {
        return service.signUp(authSignUpRequest);
    }

    @Operation(
            summary = "Logging in user session"
    )
    @PostMapping("/login")
    public ResponseEntity<AuthSignInResponse> signIn(@RequestBody AuthSignInRequest authSignInRequest) {
        return service.signIn(authSignInRequest);
    }
}

