package kg.edu.alatoo.game_store.mapper;

import kg.edu.alatoo.game_store.entity.Game;
import kg.edu.alatoo.game_store.entity.User;
import kg.edu.alatoo.game_store.enums.Role;
import kg.edu.alatoo.game_store.payload.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper mapper;

    @Test
    void toModelTest() {
        Game game = new Game("testGameTitle", 0.0, Set.of());
        User user = new User("testLogin", "testNickname", "testPassword",
                1.19, Set.of(game),
                Role.USER);

        UserGetResponse response = mapper.toModel(user);

        assertNotNull(response);
        assertEquals("testNickname", response.nickname());
        assertEquals(Set.of(game), response.games());
    }

    @Test
    void signUpToModelTest() {
        Game game1 = new Game("testGameTitle", 0.0, Set.of());
        Game game2 = new Game("testGameTitle2", 1.19, Set.of());
        User user = new User("testLogin", "testNickname", "testPassword",
                1.19, Set.of(game1, game2),
                Role.USER);

        UserSignUpResponse response = mapper.signUpToModel(user);

        assertNotNull(response);
        assertEquals(1.19, response.balance());
        assertEquals("testLogin", response.login());
        assertEquals("testNickname", response.nickname());
        assertEquals(Set.of(game1, game2), response.games());
    }

    @Test
    void signUpToEntityTest() {
        UserSignUpRequest request = new UserSignUpRequest("testLogin", "testNickname",
                "testPassword", "testPassword");

        User user = mapper.signUpToEntity(request);

        assertNotNull(user);
        assertEquals("testLogin", user.getLogin());
        assertEquals("testNickname", user.getNickname());
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    void updateToModelTest() {
        User user = new User("testLogin", "testNickname", "testPassword",
                0.0, Set.of(),
                Role.USER);

        UserUpdateResponse response = mapper.updateToModel(user);

        assertNotNull(response);
        assertEquals("testNickname", response.nickname());
    }

    @Test
    void balanceToModelTest() {
        User user = new User("testLogin", "testNickname", "testPassword",
                52.52, Set.of(),
                Role.ADMIN);

        UserBalanceResponse response = mapper.balanceToModel(user);

        assertNotNull(user.getLogin(), response.login());
        assertNotNull(user.getBalance(), String.valueOf(response.balance()));
    }
}