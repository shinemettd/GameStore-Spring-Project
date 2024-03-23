package kg.edu.alatoo.game_store.bootstrap;

import kg.edu.alatoo.game_store.entity.Game;
import kg.edu.alatoo.game_store.entity.User;
import kg.edu.alatoo.game_store.enums.Role;
import kg.edu.alatoo.game_store.repository.GameRepository;
import kg.edu.alatoo.game_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final UserRepository userRepository;

    private final GameRepository gameRepository;

    @Override
    public void run(String... args) {
        generateUsers();
        generateGames();
    }

    private void generateUsers() {
        User me = new User("shinemettd", "shinemetothedeath",
                "password", 1_000_000.0,
                Set.of(), Role.ADMIN);
        User user1 = new User("randomguy", "idkwhattowritehere",
                "passworddd", 0.0,
                Set.of(), Role.USER);
        User user2 = new User("newbie", "qwertyuser",
                "12345678", 0.0,
                Set.of(), Role.USER);

        userRepository.saveAll(List.of(me, user1, user2));
    }

    private void generateGames() {
        Game game1 = new Game("Assassin's Creed 2", 6.39, Set.of());
        Game game2 = new Game("God of War III", 29.9, Set.of());
        Game game3 = new Game("Counter-Strike 2", 0.0, Set.of());

        gameRepository.saveAll(List.of(game1, game2, game3));
    }
}
