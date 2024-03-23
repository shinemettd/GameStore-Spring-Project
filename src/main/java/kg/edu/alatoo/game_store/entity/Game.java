package kg.edu.alatoo.game_store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Game extends BaseEntity {

    @Column(length = 100, unique = true)
    String title;

    @Range(min = 0, max = 999)
    Double price;

    @ManyToMany(mappedBy = "games")
    Set<User> users;

}
