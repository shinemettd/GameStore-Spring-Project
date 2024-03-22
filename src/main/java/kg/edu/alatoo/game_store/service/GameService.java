package kg.edu.alatoo.game_store.service;

import kg.edu.alatoo.game_store.payload.game.GameRequest;
import kg.edu.alatoo.game_store.payload.game.GameResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface GameService {
    ResponseEntity<Page<GameResponse>> getAll(Pageable pageable);

    ResponseEntity<GameResponse> get(Long id);

    ResponseEntity<GameResponse> create(GameRequest gameRequest);

    ResponseEntity<GameResponse> update(Long id, GameRequest gameRequest);

    ResponseEntity<GameResponse> updateTitle(Long id, String newTitle);

    ResponseEntity<GameResponse> updatePrice(Long id, Double newPrice);

    ResponseEntity<Void> delete(Long id);
}
