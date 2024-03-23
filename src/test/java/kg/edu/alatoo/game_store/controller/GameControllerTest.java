package kg.edu.alatoo.game_store.controller;

import kg.edu.alatoo.game_store.payload.game.GameRequest;
import kg.edu.alatoo.game_store.payload.game.GameResponse;
import kg.edu.alatoo.game_store.service.GameService;
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
class GameControllerTest {

    @InjectMocks
    GameController gameController;

    @Mock
    GameService gameService;

    @Test
    void getAllShouldReturnAllGames() {
        Page<GameResponse> mockResponse = mock(Page.class);
        Pageable mockPageable = mock(Pageable.class);
        ResponseEntity<Page<GameResponse>> expectedResponse = ResponseEntity.ok(mockResponse);
        when(gameService.getAll(mockPageable)).thenReturn(expectedResponse);
        ResponseEntity<Page<GameResponse>> response = gameController.getAll(mockPageable);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void getShouldReturnExactGame() {
        GameResponse mockResponse = mock(GameResponse.class);
        Long id = 1L;

        ResponseEntity<GameResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        when(gameService.get(id)).thenReturn(ResponseEntity.ok(mockResponse));
        ResponseEntity<GameResponse> response = gameController.get(id);

        assertNotNull(response);
        assertEquals(response, expectedResponse);
        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void createShouldCreatedGame() {
        GameResponse mockResponse = mock(GameResponse.class);
        GameRequest mockRequest = mock(GameRequest.class);

        ResponseEntity<GameResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        when(gameService.create(mockRequest)).thenReturn(expectedResponse);
        ResponseEntity<GameResponse> response = gameController.create(mockRequest);

        assertNotNull(response);
        assertEquals(response, expectedResponse);
        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void updateShouldReturnUpdatedGame() {
        GameResponse mockResponse = mock(GameResponse.class);
        Long id = 1L;
        GameRequest mockRequest = mock(GameRequest.class);

        ResponseEntity<GameResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        when(gameService.update(id, mockRequest)).thenReturn(expectedResponse);
        ResponseEntity<GameResponse> response = gameController.update(id, mockRequest);

        assertNotNull(response);
        assertEquals(response, expectedResponse);
        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void updateTitleShouldReturnUpdatedTitleName() {
        GameResponse mockResponse = mock(GameResponse.class);
        Long id = 1L;
        String title = "newTitle";

        ResponseEntity<GameResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        when(gameService.updateTitle(id, title)).thenReturn(expectedResponse);
        ResponseEntity<GameResponse> response = gameController.updateTitle(id, title);

        assertNotNull(response);
        assertEquals(response, expectedResponse);
        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }

    @Test
    void updateTitleShouldReturnUpdatedPriceName() {
        GameResponse mockResponse = mock(GameResponse.class);
        Long id = 1L;
        Double price = 322.00;

        ResponseEntity<GameResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        when(gameService.updatePrice(id, price)).thenReturn(expectedResponse);
        ResponseEntity<GameResponse> response = gameController.updatePrice(id, price);

        assertNotNull(response);
        assertEquals(response, expectedResponse);
        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }


    @Test
    void deleteShouldReturnNoContent() {
        Long id = 1L;

        ResponseEntity<Void> expectedResponse = ResponseEntity.noContent().build();
        when(gameService.delete(id)).thenReturn(expectedResponse);
        ResponseEntity<Void> response = gameController.delete(id);

        assertNotNull(response);
        assertEquals(response, expectedResponse);
        assertEquals(response.getStatusCode(), expectedResponse.getStatusCode());
    }
}