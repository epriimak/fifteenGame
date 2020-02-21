package game;

import javax.persistence.*;

@Entity
@Table(schema = "game", name = "game_field_entity")
public class GameFieldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "field", length = 200, nullable = false)
    String field;

    public GameFieldEntity(String field) {
        this.field = field;
    }

    public GameFieldEntity() {
    }

    @Override
    public String toString() {
        return "GameFieldEntity{" +
                "id=" + id +
                "\nfield=\n" + field + '\'' +
                '}';
    }
}
