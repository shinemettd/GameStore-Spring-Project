package kg.edu.alatoo.game_store.entity;

import jakarta.persistence.Entity;
import kg.edu.alatoo.game_store.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    String nickname;

    String password;

    Double balance;

    List<Game> gamesList;

    Role role;
}
