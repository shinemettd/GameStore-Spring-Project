package kg.edu.alatoo.game_store.service.impl;

import kg.edu.alatoo.game_store.entity.Game;
import kg.edu.alatoo.game_store.entity.User;
import kg.edu.alatoo.game_store.enums.Role;
import kg.edu.alatoo.game_store.mapper.GameMapper;
import kg.edu.alatoo.game_store.mapper.UserMapper;
import kg.edu.alatoo.game_store.payload.game.GameResponse;
import kg.edu.alatoo.game_store.payload.user.*;
import kg.edu.alatoo.game_store.repository.GameRepository;
import kg.edu.alatoo.game_store.repository.UserRepository;
import kg.edu.alatoo.game_store.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final GameRepository gameRepository;

    private final UserMapper userMapper;

    private final GameMapper gameMapper;

    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository, UserMapper userMapper, GameMapper gameMapper) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.userMapper = userMapper;
        this.gameMapper = gameMapper;
    }

    @Override
    public ResponseEntity<Page<UserGetResponse>> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        System.out.println(users);
        Page<UserGetResponse> usersResponse = users.map(userMapper::toModel);
        System.out.println(usersResponse);
        return ResponseEntity.ok(usersResponse);
    }

    @Override
    public ResponseEntity<UserGetResponse> get(Long id) {
        User user = findUserIfExists(id);
        return ResponseEntity.ok(userMapper.toModel(user));
    }

    @Override
    public ResponseEntity<UserSignUpResponse> create(UserSignUpRequest userSignUpRequest) {
        User user = userMapper.signUpToEntity(userSignUpRequest);
        user.setRole(Role.USER);
        user.setBalance(0.0);
        UserSignUpResponse response = userMapper.signUpToModel(userRepository.save(user));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UserUpdateResponse> update(Long id, UserUpdateRequest userUpdateRequest) {
        User user = findUserIfExists(id);
        if (!userUpdateRequest.password().equals(userUpdateRequest.confirmationPassword())) {
            throw new RuntimeException("Passwords does not match");
        }
        user.setNickname(userUpdateRequest.nickname());
        user.setPassword(userUpdateRequest.password());
        UserUpdateResponse response = userMapper.updateToModel(userRepository.save(user));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UserGetResponse> updateNickname(Long id, String newNickname) {
        User user = findUserIfExists(id);
        user.setNickname(newNickname);
        return ResponseEntity.ok(userMapper.toModel(userRepository.save(user)));
    }

    @Override
    public ResponseEntity<Void> updatePassword(Long id, UserUpdatePasswordRequest userUpdatePasswordRequest) {
        User user = findUserIfExists(id);
        if (!(userUpdatePasswordRequest.oldPassword().equals(user.getPassword()))) {
            throw new RuntimeException("Incorrect current password");
        }
        if (!(userUpdatePasswordRequest.newPassword().equals(userUpdatePasswordRequest.confirmationNewPassword()))) {
            throw new RuntimeException("Passwords does not match");
        }
        user.setPassword(userUpdatePasswordRequest.newPassword());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<GameResponse>> getUserGames(Long userId, Pageable pageable) {
        User user = findUserIfExists(userId);
        Page<Game> gamesPage = gameRepository.findByUsersContaining(user, pageable);
        return ResponseEntity.ok(gamesPage.map(gameMapper::toModel));
    }

    @Override
    public ResponseEntity<GameResponse> addGameToUser(Long userId, Long gameId) {
        User user = findUserIfExists(userId);
        Game game = gameRepository.findById(gameId).orElseThrow();

        user.getGames().add(game);
        userRepository.save(user);

        return ResponseEntity.ok(gameMapper.toModel(game));
    }

    @Override
    public ResponseEntity<Void> deleteGameFromUser(Long userId, Long gameId) {
        User user = findUserIfExists(userId);
        Game game = gameRepository.findById(gameId).orElseThrow();

        if (!user.getGames().contains(game)) {
            throw new RuntimeException("User " + user.getNickname() + "does not have " + game.getTitle());
        }

        user.getGames().remove(game);

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserBalanceResponse> getUserBalance(Long id) {
        User user = findUserIfExists(id);
        return ResponseEntity.ok(userMapper.balanceToModel(user));
    }

    @Override
    public ResponseEntity<UserBalanceResponse> addUserBalance(Long id, Double amount) {
        User user = findUserIfExists(id);
        BigDecimal currentUserBalance = BigDecimal.valueOf(user.getBalance());
        BigDecimal newUserBalance = currentUserBalance.add(BigDecimal.valueOf(amount));
        user.setBalance(newUserBalance.doubleValue());
        return ResponseEntity.ok(userMapper.balanceToModel(userRepository.save(user)));
    }

    @Override
    public ResponseEntity<UserBalanceResponse> subtractUserBalance(Long id, Double amount) {
        User user = findUserIfExists(id);
        BigDecimal currentUserBalance = BigDecimal.valueOf(user.getBalance());
        BigDecimal newUserBalance = currentUserBalance.subtract(BigDecimal.valueOf(amount));
        user.setBalance(newUserBalance.doubleValue());
        return ResponseEntity.ok(userMapper.balanceToModel(userRepository.save(user)));
    }

    private User findUserIfExists(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user;
    }
}
