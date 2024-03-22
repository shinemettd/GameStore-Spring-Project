package kg.edu.alatoo.game_store.service.impl;

import kg.edu.alatoo.game_store.entity.Game;
import kg.edu.alatoo.game_store.repository.GameRepository;
import kg.edu.alatoo.game_store.service.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository repository;

    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Page<Game>> getAll(Pageable pageable) {
        Page<Game> gamesPage = repository.findAll(pageable);
        return ResponseEntity.ok(gamesPage);
    }

    @Override
    public ResponseEntity<Game> get(Long id) {
        Game game = findGameIfExist(id);
        return ResponseEntity.ok(game);
    }

    @Override
    public ResponseEntity<Game> create(Game game) {
        Game savedGame = repository.save(game);
        return ResponseEntity.ok(savedGame);
    }

    @Override
    public ResponseEntity<Game> update(Long id, Game game) {
        Game gameToUpdate = findGameIfExist(id);

        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setPrice(game.getPrice());
        gameToUpdate.setDiscount(game.getDiscount());

        Game updatedGame = repository.save(gameToUpdate);
        return ResponseEntity.ok(updatedGame);
    }

    @Override
    public ResponseEntity<Game> updateTitle(Long id, String newTitle) {
        Game gameToUpdate = findGameIfExist(id);

        gameToUpdate.setTitle(newTitle);
        Game updatedGame = repository.save(gameToUpdate);
        return ResponseEntity.ok(updatedGame);
    }

    @Override
    public ResponseEntity<Game> updatePrice(Long id, Double newPrice) {
        Game gameToUpdate = findGameIfExist(id);

        gameToUpdate.setPrice(newPrice);
        Game updatedGame = repository.save(gameToUpdate);
        return ResponseEntity.ok(updatedGame);
    }

    @Override
    public ResponseEntity<Game> updateDiscount(Long id, Integer newDiscount) {
        Game gameToUpdate = findGameIfExist(id);

        gameToUpdate.setDiscount(newDiscount);
        Game updatedGame = repository.save(gameToUpdate);
        return ResponseEntity.ok(updatedGame);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        Game game = findGameIfExist(id);
        repository.delete(game);
        return ResponseEntity.noContent().build();
    }

    private Game findGameIfExist(Long id) {
        Game game = repository.findById(id).orElseThrow();
        return game;
    }
}
