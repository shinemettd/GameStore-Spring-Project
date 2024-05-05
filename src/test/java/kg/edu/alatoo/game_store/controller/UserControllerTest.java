package kg.edu.alatoo.game_store.controller;

import kg.edu.alatoo.game_store.payload.game.GameResponse;
import kg.edu.alatoo.game_store.payload.user.*;
import kg.edu.alatoo.game_store.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    void getAllShouldReturnsAllUsers() {
        Page<UserGetResponse> mockResponse = mock(Page.class);
        Pageable mockPageable = mock(Pageable.class);

        ResponseEntity<Page<UserGetResponse>> expectedResponse = ResponseEntity.ok(mockResponse);
        when(userService.getAll(mockPageable)).thenReturn(expectedResponse);
        ResponseEntity<Page<UserGetResponse>> response = userController.getAll(mockPageable);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
        assertEquals(response.getBody(), expectedResponse.getBody());
    }

    @Test
    void getShouldReturnUserById() {
        Long userId = 1L;
        UserGetResponse mockResponse = mock(UserGetResponse.class);

        ResponseEntity<UserGetResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        when(userService.get(userId)).thenReturn(expectedResponse);
        ResponseEntity<UserGetResponse> response = userController.get(userId);

        assertNotNull(response);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

//    @Test
//    void createShouldReturnCreatedUser() {
//        UserSignUpRequest mockRequest = mock(UserSignUpRequest.class);
//        UserSignUpResponse mockResponse = mock(UserSignUpResponse.class);
//
//        ResponseEntity<UserSignUpResponse> expectedResponse = ResponseEntity.ok(mockResponse);
//        when(userService.create(mockRequest)).thenReturn(expectedResponse);
//        ResponseEntity<UserSignUpResponse> response = userController.create(mockRequest);
//
//        assertNotNull(response);
//        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
//        assertEquals(expectedResponse.getBody(), response.getBody());
//    }

    @Test
    void updateShouldReturnUpdatedUser() {
        Long userId = 1L;
        UserUpdateRequest mockRequest = mock(UserUpdateRequest.class);
        UserUpdateResponse mockResponse = mock(UserUpdateResponse.class);

        ResponseEntity<UserUpdateResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        when(userService.update(userId, mockRequest)).thenReturn(expectedResponse);
        ResponseEntity<UserUpdateResponse> response = userController.update(userId, mockRequest);

        assertNotNull(response);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void updateNicknameShouldReturnUpdatedUser() {
        Long id = 1L;
        String testNickname = "testNickname";
        UserGetResponse mockResponse = mock(UserGetResponse.class);

        ResponseEntity<UserGetResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        when(userService.updateNickname(id, testNickname)).thenReturn(expectedResponse);
        ResponseEntity<UserGetResponse> response = userController.updateNickname(id, testNickname);

        assertNotNull(response);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void updatePasswordShouldReturnNoContent() {
        Long id = 1L;
        UserUpdatePasswordRequest mockResponse = mock(UserUpdatePasswordRequest.class);

        ResponseEntity<Void> expectedResponse = ResponseEntity.noContent().build();
        when(userService.updatePassword(id, mockResponse)).thenReturn(expectedResponse);
        ResponseEntity<Void> response = userController.updatePassword(id, mockResponse);

        assertNotNull(response);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
    }

    @Test
    void getUserGamesShouldReturnSetOfGames() {
        Long id = 1L;
        Page<GameResponse> mockResponse = mock(Page.class);
        Pageable mockPageable = mock(Pageable.class);

        ResponseEntity<Page<GameResponse>> expectedResponse = ResponseEntity.ok(mockResponse);
        when(userService.getUserGames(id, mockPageable)).thenReturn(expectedResponse);
        ResponseEntity<Page<GameResponse>> response = userController.getUserGames(id, mockPageable);

        assertNotNull(response);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void addGameToUserShouldReturnAddedGame() {
        Long userId = 1L;
        Long gameId = 1L;
        GameResponse mockGameResponse = mock(GameResponse.class);

        ResponseEntity<GameResponse> expectedResponse = ResponseEntity.ok(mockGameResponse);
        when(userService.addGameToUser(userId, gameId)).thenReturn(expectedResponse);
        ResponseEntity<GameResponse> response = userController.addGameToUser(userId, gameId);

        assertNotNull(response);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void deleteGameFromUserShouldReturnNoContent() {
        Long userId = 1L;
        Long gameId = 1L;

        ResponseEntity<Void> expectedResponse = ResponseEntity.noContent().build();
        when(userService.deleteGameFromUser(userId, gameId)).thenReturn(expectedResponse);
        ResponseEntity<Void> response = userController.deleteGameFromUser(userId, gameId);

        assertNotNull(response);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
    }

    @Test
    void getUserBalanceShouldReturnUserBalance() {
        Long userId = 1L;

        UserBalanceResponse mockUserBalance = mock(UserBalanceResponse.class);

        ResponseEntity<UserBalanceResponse> expectedResponse = ResponseEntity.ok(mockUserBalance);
        when(userService.getUserBalance(userId)).thenReturn(expectedResponse);
        ResponseEntity<UserBalanceResponse> response = userController.getUserBalance(userId);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
    }

    @Test
    void addUserBalanceShouldReturnChangedUserBalance() {
        Long userId = 1L;

        UserBalanceResponse mockUserBalance = mock(UserBalanceResponse.class);

        ResponseEntity<UserBalanceResponse> expectedResponse = ResponseEntity.ok(mockUserBalance);
        when(userService.addUserBalance(userId, 13.37)).thenReturn(expectedResponse);
        ResponseEntity<UserBalanceResponse> response = userController.addUserBalance(userId, 13.37);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
    }

    @Test
    void subtractUserBalanceShouldChangedReturnUserBalance() {
        Long userId = 1L;

        UserBalanceResponse mockUserBalance = mock(UserBalanceResponse.class);

        ResponseEntity<UserBalanceResponse> expectedResponse = ResponseEntity.ok(mockUserBalance);
        when(userService.subtractUserBalance(userId, 10.02)).thenReturn(expectedResponse);
        ResponseEntity<UserBalanceResponse> response = userController.subtractUserBalance(userId, 10.02);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
    }
}