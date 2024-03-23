package kg.edu.alatoo.game_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.edu.alatoo.game_store.payload.game.GameRequest;
import kg.edu.alatoo.game_store.payload.game.GameResponse;
import kg.edu.alatoo.game_store.service.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
@Tag(
        name = "Game Controller",
        description = "Controller for operations with games"
)
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @Operation(
            summary = "Getting all game with pageable"
    )
    @GetMapping
    public ResponseEntity<Page<GameResponse>> getAll(@PageableDefault(page = 0, size = 10, sort = "title", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(pageable);
    }

    @Operation(
            summary = "Getting a game entity by its id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @Operation(
            summary = "Creates a game entity"
    )
    @PostMapping
    public ResponseEntity<GameResponse> create(@RequestBody GameRequest gameRequest) {
        return service.create(gameRequest);
    }

    @Operation(
            summary = "Updating a game entity by its id"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<GameResponse> update(@PathVariable("id") Long id, @RequestBody GameRequest gameRequest) {
        return service.update(id, gameRequest);
    }

    @Operation(
            summary = "Updating a game title by its id"
    )
    @PatchMapping("/update/{id}/title")
    public ResponseEntity<GameResponse> updateTitle(@PathVariable("id") Long id, @RequestBody String title) {
        return service.updateTitle(id, title);
    }

    @Operation(
            summary = "Updating a game price by its id"
    )
    @PatchMapping("/update/{id}/price")
    public ResponseEntity<GameResponse> updatePrice(@PathVariable("id") Long id, @RequestBody Double newPrice) {
        return service.updatePrice(id, newPrice);
    }

    @Operation(
            summary = "Deletes a game entity by its id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
