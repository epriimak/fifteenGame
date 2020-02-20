package game;

import javax.persistence.*;

@Entity
@Table(schema = "game", name = "game_field_solution_step_entity")
public class GameFieldSolutionStepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "field", length = 200, nullable = false)
    String field;

    @Column(name = "direction", length = 20, nullable = false)
    String direction;

    public GameFieldSolutionStepEntity(String field, String direction) {
        this.field = field;
        this.direction = direction;
    }

    public GameFieldSolutionStepEntity() {
    }

    @Override
    public String toString() {
        return "GameFieldSolutionStepEntity{" +
                "id=" + id +
                "\nfield=\n" + field +
                "direction=\n'" + direction + '\'' +
                '}';
    }
}