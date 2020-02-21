package game;

import game.GameFieldEntity;
import game.GameFieldRepository;
import game.local.GameFieldException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class GameFieldSolutionStepTest {
    @Autowired
    private GameFieldSolutionStepRepository repository;

    @Test
    public void ifAddEntityIntoRepositoryItWillHaveId() throws GameFieldException {
        String field = "New Field";
        String direction = "New direction";

        GameFieldSolutionStepEntity gameFieldStep = new GameFieldSolutionStepEntity(field, direction);

        GameFieldSolutionStepEntity saved = repository.save(gameFieldStep);

        assertThat(saved.id).isNotNull();
    }

    @Test
    public void ifSaveEntityIntoRepositoryItWillBeFind() throws GameFieldException {
        String field = "New Field";
        String direction = "New direction";

        GameFieldSolutionStepEntity gameFieldStep = new GameFieldSolutionStepEntity(field, direction);

        GameFieldSolutionStepEntity saved = repository.save(gameFieldStep);

        Optional<GameFieldSolutionStepEntity> reloaded = repository.findById(saved.id);
        assertThat(reloaded).isNotEmpty();
        assertThat(reloaded.get().field).isEqualTo(field);
    }
}