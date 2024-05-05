package kg.edu.alatoo.game_store.repository;

import kg.edu.alatoo.game_store.entity.Game;
import kg.edu.alatoo.game_store.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GameRepositoryTest {

    @Autowired
    GameRepository repository;

    Long exactId;

    @BeforeEach
    void setUp() {
        Game game1 = new Game("Test Game 1", 9.99, Set.of());
        exactId = (repository.save(game1)).getId();

        Game game2 = new Game("Test Game 2", 19.99, Set.of());
        repository.save(game2);
    }

    @Test
    void findAllShouldFindNotEmptyList() {
        List<Game> gamesList = repository.findAll();

        assertThat(gamesList).isNotEmpty();
    }

    @Test
    void findByIdShouldFindGame() {
        Game game = repository.findById(exactId).orElseThrow(() -> new NotFoundException("Game not found"));

        assertThat(game).hasFieldOrPropertyWithValue("id", game.getId());
        assertThat(game).hasFieldOrPropertyWithValue("title", game.getTitle());
        assertThat(game).hasFieldOrPropertyWithValue("price", game.getPrice());
        assertThat(game).hasFieldOrPropertyWithValue("users", game.getUsers());
    }

    @Test
    void findByIdShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            repository.findById(Long.MAX_VALUE).orElseThrow(() -> new NotFoundException("Game not found"));
        });
    }
}