package kg.edu.alatoo.game_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.edu.alatoo.game_store.payload.game.GameResponse;
import kg.edu.alatoo.game_store.payload.user.*;
import kg.edu.alatoo.game_store.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(
        name = "User Controller",
        description = "Controller for operations with users"
)
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(
            summary = "Returning a page of users"
    )
    @GetMapping
    public ResponseEntity<Page<UserGetResponse>> getAll(@PageableDefault(page = 0, size = 10, sort = "login", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(pageable);
    }

    @Operation(
            summary = "Returning a user by its id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponse> get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @Operation(
            summary = "Creating a user"
    )
    @PostMapping
    public ResponseEntity<UserSignUpResponse> create(@RequestBody UserSignUpRequest userSignUpRequest) {
        return service.create(userSignUpRequest);
    }

    @Operation(
            summary = "Updates full field of user by its id"
    )
    @PostMapping("/{id}")
    public ResponseEntity<UserUpdateResponse> update(@PathVariable("id") Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        return service.update(id, userUpdateRequest);
    }

    @Operation(
            summary = "Updates users nickname by its id"
    )
    @PatchMapping("/nickname/{id}")
    public ResponseEntity<UserGetResponse> updateNickname(@PathVariable("id") Long id, @RequestBody String newNickname) {
        return service.updateNickname(id, newNickname);
    }

    @Operation(
            summary = "Updates a user password"
    )
    @PatchMapping("/password/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest) {
        return service.updatePassword(id, userUpdatePasswordRequest);
    }

    @Operation(
            summary = "Returns a page of user games"
    )
    @GetMapping("/{id}/games")
    public ResponseEntity<Page<GameResponse>> getUserGames(@PathVariable("id") Long userId,
                                                           @PageableDefault(page = 0,
                                                                   size = 10,
                                                                   sort = "title",
                                                                   direction = Sort.Direction.DESC)
                                                           Pageable pageable) {
        return service.getUserGames(userId, pageable);
    }

    @Operation(
            summary = "Adds a game to the user"
    )
    @PostMapping("/{user-id}/add/game/{game-id}")
    public ResponseEntity<GameResponse> addGameToUser(@PathVariable("user-id") Long userId, @PathVariable("game-id") Long gameId) {
        return service.addGameToUser(userId, gameId);
    }

    @Operation(
            summary = "Deletes a game from the user"
    )
    @PostMapping("/{user-id}/add/delete/{game-id}")
    public ResponseEntity<Void> deleteGameFromUser(@PathVariable("user-id") Long userId, @PathVariable("game-id") Long gameId) {
        return service.deleteGameFromUser(userId, gameId);
    }

    @Operation(
            summary = "Getting users balance by its id"
    )
    @GetMapping("/{id}/balance")
    public ResponseEntity<UserBalanceResponse> getUserBalance(@PathVariable("id") Long id) {
        return service.getUserBalance(id);
    }

    @Operation(
            summary = "Adding value to user balance by its id"
    )
    @PutMapping("/{id}/balance/add")
    public ResponseEntity<UserBalanceResponse> addUserBalance(@PathVariable("id") Long id, @RequestBody Double amount) {
        return service.addUserBalance(id, amount);
    }

    @Operation(
            summary = "Subtracting value from user balance by its id"
    )
    @PutMapping("/{id}/balance/subtract")
    public ResponseEntity<UserBalanceResponse> subtractUserBalance(@PathVariable("id") Long id, @RequestBody Double amount) {
        return service.subtractUserBalance(id, amount);
    }
}
