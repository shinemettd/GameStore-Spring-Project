package kg.edu.alatoo.game_store.service;

import kg.edu.alatoo.game_store.payload.auth.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    ResponseEntity<AuthSignInResponse> signIn(AuthSignInRequest authSignInRequest);

    ResponseEntity<AuthSignUpResponse> signUp(AuthSignUpRequest authSignUpRequest);

    ResponseEntity<JwtTokenResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
