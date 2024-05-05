package kg.edu.alatoo.game_store.bootstrap;

import kg.edu.alatoo.game_store.entity.Game;
import kg.edu.alatoo.game_store.entity.User;
import kg.edu.alatoo.game_store.enums.Role;
import kg.edu.alatoo.game_store.payload.auth.AuthSignUpRequest;
import kg.edu.alatoo.game_store.repository.GameRepository;
import kg.edu.alatoo.game_store.repository.UserRepository;
import kg.edu.alatoo.game_store.service.AuthService;
import kg.edu.alatoo.game_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final AuthService authService;

    private final GameRepository gameRepository;

    @Override
    public void run(String... args) {
        generateUsers();
        generateGames();
    }

    private void generateUsers() {
        AuthSignUpRequest me = new AuthSignUpRequest("shinemettd", "shinemetothedeath",
                "password", "password");
        AuthSignUpRequest user1 = new AuthSignUpRequest("randomguy", "idkwhattowritehere",
                "passworddd", "passworddd");
        AuthSignUpRequest user2 = new AuthSignUpRequest("newbie", "qwertyuser",
                "12345678", "12345678");

        authService.signUp(me);
        authService.signUp(user1);
        authService.signUp(user2);
    }

    private void generateGames() {
        Game game1 = new Game("Assassin's Creed 2", 6.39, Set.of());
        Game game2 = new Game("God of War III", 29.9, Set.of());
        Game game3 = new Game("Counter-Strike 2", 0.0, Set.of());

        gameRepository.saveAll(List.of(game1, game2, game3));
    }
}
