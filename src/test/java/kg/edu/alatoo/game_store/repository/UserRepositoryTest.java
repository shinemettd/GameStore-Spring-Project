package kg.edu.alatoo.game_store.repository;

import kg.edu.alatoo.game_store.entity.Game;
import kg.edu.alatoo.game_store.entity.User;
import kg.edu.alatoo.game_store.enums.Role;
import kg.edu.alatoo.game_store.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    Long exactId;

    @BeforeEach
    void setUp() {
        User user1 = new User("Login", "Nickname", "Password", 10.0, Set.of(), Role.USER);
        exactId = repository.save(user1).getId();

        User user2 = new User("Login2", "Nickname2", "Password2", 20.0, Set.of(), Role.ADMIN);
        repository.save(user2);

        User user3 = new User("Login3", "Nickname3", "Password3", 30.0, Set.of(), Role.USER);
        repository.save(user3);
    }

    @Test
    void findAllShouldFindNotEmptyList() {
        List<User> gamesList = repository.findAll();

        assertThat(gamesList).isNotEmpty();
    }

    @Test
    void findByIdShouldFindGame() {
        User user = repository.findById(exactId).orElseThrow(() -> new NotFoundException("User not found"));

        assertThat(user).hasFieldOrPropertyWithValue("id", user.getId());
        assertThat(user).hasFieldOrPropertyWithValue("login", user.getLogin());
        assertThat(user).hasFieldOrPropertyWithValue("password", user.getPassword());
        assertThat(user).hasFieldOrPropertyWithValue("balance", user.getBalance());
        assertThat(user).hasFieldOrPropertyWithValue("games", user.getGames());
        assertThat(user).hasFieldOrPropertyWithValue("role", user.getRole());
    }

    @Test
    void findByIdShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            repository.findById(Long.MAX_VALUE).orElseThrow(() -> new NotFoundException("User not found"));
        });
    }
}