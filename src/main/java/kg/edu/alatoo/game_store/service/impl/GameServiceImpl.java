package kg.edu.alatoo.game_store.service.impl;

import kg.edu.alatoo.game_store.exception.NotFoundException;
import kg.edu.alatoo.game_store.exception.NotValidException;
import kg.edu.alatoo.game_store.mapper.GameMapper;
import kg.edu.alatoo.game_store.entity.Game;
import kg.edu.alatoo.game_store.payload.game.GameRequest;
import kg.edu.alatoo.game_store.payload.game.GameResponse;
import kg.edu.alatoo.game_store.repository.GameRepository;
import kg.edu.alatoo.game_store.service.GameService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository repository;

    private final GameMapper mapper;

    public GameServiceImpl(GameRepository repository, GameMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Page<GameResponse>> getAll(Pageable pageable) {
        Page<Game> gamesPage = repository.findAll(pageable);
        Page<GameResponse> gameResponsesList = gamesPage.map(mapper::toModel);

        return ResponseEntity.ok(gameResponsesList);
    }

    @Override
    public ResponseEntity<GameResponse> get(Long id) {
        Game game = findGameIfExist(id);
        return ResponseEntity.ok(mapper.toModel(game));
    }

    @Override
    public ResponseEntity<GameResponse> create(GameRequest gameRequest) {
        Game game = mapper.toEntity(gameRequest);
        Game savedGame;

        try {
            savedGame = repository.save(game);
        } catch (DataIntegrityViolationException e) {
            throw new NotValidException("Game with this title already exists");
        }

        return ResponseEntity.ok(mapper.toModel(savedGame));
    }

    @Override
    public ResponseEntity<GameResponse> update(Long id, GameRequest gameRequest) {
        Game gameToUpdate = findGameIfExist(id);

        gameToUpdate.setTitle(gameRequest.title());
        gameToUpdate.setPrice(gameRequest.price());

        Game updatedGame = repository.save(gameToUpdate);

        return ResponseEntity.ok(mapper.toModel(updatedGame));
    }

    @Override
    public ResponseEntity<GameResponse> updateTitle(Long id, String newTitle) {
        Game gameToUpdate = findGameIfExist(id);

        gameToUpdate.setTitle(newTitle);
        Game updatedGame = repository.save(gameToUpdate);
        return ResponseEntity.ok(mapper.toModel(updatedGame));
    }

    @Override
    public ResponseEntity<GameResponse> updatePrice(Long id, Double newPrice) {
        Game gameToUpdate = findGameIfExist(id);

        gameToUpdate.setPrice(newPrice);
        Game updatedGame = repository.save(gameToUpdate);
        return ResponseEntity.ok(mapper.toModel(updatedGame));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        Game game = findGameIfExist(id);
        repository.delete(game);
        return ResponseEntity.noContent().build();
    }

    private Game findGameIfExist(Long id) {
        Game game = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Game with id " + id + "does not exits"));
        return game;
    }
}
