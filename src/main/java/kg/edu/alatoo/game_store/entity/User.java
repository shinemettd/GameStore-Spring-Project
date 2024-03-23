package kg.edu.alatoo.game_store.entity;

import jakarta.persistence.*;
import kg.edu.alatoo.game_store.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(length = 100, unique = true)
    String login;

    @Length(min = 1, max = 100)
    String nickname;

    String password;

    @Range(min = 0, max = 999_999_999)
    Double balance;

    @ManyToMany
    @JoinTable(
            name = "user_games",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<Game> games;

    Role role;
}
