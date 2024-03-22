package kg.edu.alatoo.game_store.entity;

import jakarta.persistence.Entity;
import kg.edu.alatoo.game_store.enums.Role;

import java.util.List;

@Entity
public class User extends BaseEntity {

    String nickname;

    String password;

    Double balance;

    List<Game> gamesList;

    Role role;
}
