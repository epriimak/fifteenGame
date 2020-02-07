import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GameStepRunnerTest {
    @ParameterizedTest
    @EnumSource(
            value = Direction.class,
            names = {"UP", "DOWN", "RIGHT"})
    void ifGameStepDirectionIsPossibleThanNoExceptionThrows(Direction direction) throws IOException, GameFieldException {
        InputReader inputReader = new InputReader("src/test/resources/gameField");
        GameField gameField = new GameField(inputReader);

        assertNotNull(new GameFieldShifter().shift(gameField, direction));
    }

    @ParameterizedTest
    @EnumSource(
            value = Direction.class,
            names = {"LEFT"})
    void ifGameStepDirectionIsNotPossibleThanExceptionThrows(Direction direction) throws IOException, GameFieldException {
        InputReader inputReader = new InputReader("src/test/resources/gameField");
        GameField gameField = new GameField(inputReader);

        assertNull(new GameFieldShifter().shift(gameField, direction));
    }
}
