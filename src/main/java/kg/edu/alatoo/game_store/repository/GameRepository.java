package kg.edu.alatoo.game_store.repository;

import kg.edu.alatoo.game_store.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Long, Game> {
}
