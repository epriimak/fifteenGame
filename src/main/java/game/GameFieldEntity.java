package game;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(schema = "game", name = "game_field_entity")
public class GameFieldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "field", length = 200, nullable = false)
    String field;

    @Override
    public String toString() {
        return "GameFieldEntity{" +
                "id=" + id +
                "\nfield=\n" + field + '\'' +
                '}';
    }
}
