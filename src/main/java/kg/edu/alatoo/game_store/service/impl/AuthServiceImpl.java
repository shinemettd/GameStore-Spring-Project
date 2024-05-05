package kg.edu.alatoo.game_store.service.impl;

import kg.edu.alatoo.game_store.entity.RefreshToken;
import kg.edu.alatoo.game_store.entity.User;
import kg.edu.alatoo.game_store.enums.Role;
import kg.edu.alatoo.game_store.exception.NotFoundException;
import kg.edu.alatoo.game_store.exception.NotValidException;
import kg.edu.alatoo.game_store.mapper.UserMapper;
import kg.edu.alatoo.game_store.payload.auth.AuthSignInRequest;
import kg.edu.alatoo.game_store.payload.auth.AuthSignInResponse;
import kg.edu.alatoo.game_store.payload.auth.AuthSignUpRequest;
import kg.edu.alatoo.game_store.payload.auth.AuthSignUpResponse;
import kg.edu.alatoo.game_store.repository.UserRepository;
import kg.edu.alatoo.game_store.service.AuthService;
import kg.edu.alatoo.game_store.service.JwtService;
import kg.edu.alatoo.game_store.service.RefreshTokenService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, JwtService jwtService, RefreshTokenService refreshTokenService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<AuthSignUpResponse> signUp(AuthSignUpRequest authSignUpRequest) {
        if (!authSignUpRequest.password().equals(authSignUpRequest.confirmPassword())) {
            throw new NotValidException("Passwords does not match");
        }

        User user = userMapper.signUpToEntity(authSignUpRequest);
        user.setRole(Role.USER);
        user.setBalance(0.0);
        user.setGames(Set.of());
        user.setPassword(passwordEncoder.encode(authSignUpRequest.password()));

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new NotValidException("Username is already taken");
        }

        AuthSignUpResponse response = userMapper.signUpToModel(userRepository.save(user));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<AuthSignInResponse> signIn(AuthSignInRequest authSignInRequest) {
        User user = userRepository.findByUsername(authSignInRequest.username())
                .orElseThrow(() -> new NotFoundException("User with that username not found"));

        if (!passwordEncoder.matches(authSignInRequest.password(), user.getPassword())) {
            throw new NotValidException("Incorrect password");
        }

//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authSignInRequest.username(),
//                        authSignInRequest.password()
//                )
//        );
        String jwtToken = jwtService.generateToken(user.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authSignInRequest.username());
        AuthSignInResponse response = new AuthSignInResponse(jwtToken, refreshToken.getToken());
        return ResponseEntity.ok(response);
    }
}
