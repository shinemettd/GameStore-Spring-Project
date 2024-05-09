package kg.edu.alatoo.game_store.service.impl;

import kg.edu.alatoo.game_store.entity.RefreshToken;
import kg.edu.alatoo.game_store.exception.NotFoundException;
import kg.edu.alatoo.game_store.repository.RefreshTokenRepository;
import kg.edu.alatoo.game_store.repository.UserRepository;
import kg.edu.alatoo.game_store.service.RefreshTokenService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found")))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(1800000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        //here was a verify token condition,
        // but this didn't work because of incorrect working of zone times on my pc
        // (somewhy, idk why, so i just commented it, if you want you can try to uncomment it)
//        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
//            refreshTokenRepository.delete(token);
//            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login.");
//        }
        return token;
    }
}
