package kg.edu.alatoo.game_store.entity;

import jakarta.persistence.*;
import kg.edu.alatoo.game_store.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    String login;

    String nickname;

    String password;

    Double balance;

    @ManyToMany
    Set<Game> games;

    Role role;
}
