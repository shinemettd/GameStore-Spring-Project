package kg.edu.alatoo.game_store.service;

import kg.edu.alatoo.game_store.payload.auth.AuthSignInRequest;
import kg.edu.alatoo.game_store.payload.auth.AuthSignInResponse;
import kg.edu.alatoo.game_store.payload.auth.AuthSignUpRequest;
import kg.edu.alatoo.game_store.payload.auth.AuthSignUpResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthSignInResponse> signIn(AuthSignInRequest authSignInRequest);

    ResponseEntity<AuthSignUpResponse> signUp(AuthSignUpRequest authSignUpRequest);
}
