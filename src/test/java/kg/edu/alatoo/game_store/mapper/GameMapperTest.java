package kg.edu.alatoo.game_store.mapper;

import kg.edu.alatoo.game_store.entity.Game;
import kg.edu.alatoo.game_store.entity.User;
import kg.edu.alatoo.game_store.payload.game.GameRequest;
import kg.edu.alatoo.game_store.payload.game.GameResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameMapperTest {

    @Autowired
    GameMapper mapper;

    @Test
    void toEntityTest() {
        GameRequest request = new GameRequest("Dota 2 XD", 999.99);

        Game game = mapper.toEntity(request);

        assertNotNull(game);
        assertEquals("Dota 2 XD", game.getTitle());
        assertEquals(999.99, game.getPrice());
    }

    @Test
    void toModelTest() {
        Game game = new Game("CS2", 0.0, Set.of(new User()));

        GameResponse response = mapper.toModel(game);

        assertNotNull(response);
        assertEquals(response.title(), "CS2");
        assertEquals(response.price(), 0.0);
    }
}