package kg.edu.alatoo.game_store.controller;

import kg.edu.alatoo.game_store.payload.user.*;
import kg.edu.alatoo.game_store.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void createShouldReturnCreatedUser() {
        UserSignUpRequest mockRequest = mock(UserSignUpRequest.class);
        UserSignUpResponse mockResponse = mock(UserSignUpResponse.class);

        ResponseEntity<UserSignUpResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        when(userService.create(mockRequest)).thenReturn(expectedResponse);
        ResponseEntity<UserSignUpResponse> response = userController.create(mockRequest);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void updateShouldReturnUpdatedUser() {
        Long userId = 1L;
        UserUpdateRequest mockRequest = mock(UserUpdateRequest.class);
        UserUpdateResponse mockResponse = mock(UserUpdateResponse.class);

        ResponseEntity<UserUpdateResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        when(userService.update(userId, mockRequest)).thenReturn(expectedResponse);
        ResponseEntity<UserUpdateResponse> response = userController.update(userId, mockRequest);

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

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void getUserGamesShouldReturnSetOfGames() {
    }

    @Test
    void addGameToUserShouldReturnAddedGame() {
    }

    @Test
    void deleteGameFromUserShouldReturnDeletedGame() {
    }

    @Test
    void getUserBalanceShouldReturnUserBalance() {
    }

    @Test
    void addUserBalanceShouldReturnChangedUserBalance() {
    }

    @Test
    void subtractUserBalanceShouldChangedReturnUserBalance() {
    }
}