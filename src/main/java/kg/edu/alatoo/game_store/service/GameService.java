package kg.edu.alatoo.game_store.service;

import kg.edu.alatoo.game_store.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

public interface GameService {
    ResponseEntity<Page<Game>> getAll(Pageable pageable);

    ResponseEntity<Game> get(Long id);

    ResponseEntity<Game> create(Game game);

    ResponseEntity<Game> update(Long id, Game game);

    ResponseEntity<Game> updateTitle(Long id, String newTitle);

    ResponseEntity<Game> updatePrice(Long id, Double newPrice);

    ResponseEntity<Game> updateDiscount(Long id, Integer newDiscount);

    ResponseEntity<Void> delete(Long id);
}
