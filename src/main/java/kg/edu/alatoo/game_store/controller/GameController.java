package kg.edu.alatoo.game_store.controller;

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
    name = "Controller for operations with games"
)
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<GameResponse>> getAll(@PageableDefault(page = 0, size = 10, sort = "title", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<GameResponse> create(@RequestBody GameRequest gameRequest) {
        return service.create(gameRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GameResponse> update(@PathVariable("id") Long id, @RequestBody GameRequest gameRequest) {
        return service.update(id, gameRequest);
    }

    @PatchMapping("/update/{id}/title")
    public ResponseEntity<GameResponse> updateTitle(@PathVariable("id") Long id, @RequestBody String title) {
        return service.updateTitle(id, title);
    }

    @PatchMapping("/update/{id}/price")
    public ResponseEntity<GameResponse> updatePrice(@PathVariable("id") Long id, @RequestBody Double newPrice) {
        return service.updatePrice(id, newPrice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
