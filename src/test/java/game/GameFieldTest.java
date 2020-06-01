package game;

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
public class GameFieldTest {
    @Autowired
    private GameFieldRepository repository;

    @Test
    public void ifAddEntityIntoRepositoryItWillHaveId() throws GameFieldException {
        String field = "New Field";
        GameFieldEntity gameField = new GameFieldEntity(field);

        GameFieldEntity saved = repository.save(gameField);

        assertThat(saved.id).isNotNull();
    }

    @Test
    public void ifSaveEntityIntoRepositoryItWillBeFind() throws GameFieldException {
        String field = "New Field";
        GameFieldEntity gameField = new GameFieldEntity(field);

        GameFieldEntity saved = repository.save(gameField);


        Optional<GameFieldEntity> reloaded = repository.findById(saved.id);
        assertThat(reloaded).isNotEmpty();
        assertThat(reloaded.get().field).isEqualTo(field);
    }

}
