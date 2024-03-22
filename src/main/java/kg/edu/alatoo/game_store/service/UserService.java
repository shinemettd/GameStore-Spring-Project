package kg.edu.alatoo.game_store.service;

import kg.edu.alatoo.game_store.payload.game.GameResponse;
import kg.edu.alatoo.game_store.payload.user.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Page<UserGetResponse>> getAll(Pageable pageable);

    ResponseEntity<UserGetResponse> get(Long id);

    ResponseEntity<UserSignUpResponse> create(UserSignUpRequest userSignUpRequest);

    ResponseEntity<UserUpdateResponse> update(Long id, UserUpdateRequest userUpdateRequest);

    ResponseEntity<UserGetResponse> updateNickname(Long id, String newNickname);

    ResponseEntity<Void> updatePassword(Long id, UserUpdatePasswordRequest userUpdatePasswordRequest);

    ResponseEntity<Page<GameResponse>> getUserGames(Long userId, Pageable pageable);

    ResponseEntity<GameResponse> addGameToUser(Long userId, Long gameId);

    ResponseEntity<Void> deleteGameFromUser(Long userId, Long gameId);

    ResponseEntity<UserBalanceResponse> getUserBalance(Long id);

    ResponseEntity<UserBalanceResponse> addUserBalance(Long id, Double amount);

    ResponseEntity<UserBalanceResponse> subtractUserBalance(Long id, Double amount);

}
