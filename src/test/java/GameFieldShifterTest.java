import com.sun.org.glassfish.gmbal.ParameterNames;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GameFieldShifterTest {
    @ParameterizedTest
    @EnumSource(
            value = Direction.class,
            names = {"UP", "DOWN", "RIGHT"})
    void ifGameStepDirectionIsPossibleThanNoExceptionThrows(Direction direction) throws IOException, GameFieldException {
        Reader reader = new Reader("src/test/resources/gameFieldIsSolvable");
        GameField gameField = new GameField(reader);

        assertNotNull(GameFieldShifter.shift(gameField, direction));
    }

    @ParameterizedTest
    @EnumSource(
            value = Direction.class,
            names = {"LEFT"})
    void ifGameStepDirectionIsNotPossibleThanExceptionThrows(Direction direction) throws IOException, GameFieldException {
        Reader reader = new Reader("src/test/resources/gameFieldIsSolvable");
        GameField gameField = new GameField(reader);

        assertNull(GameFieldShifter.shift(gameField, direction));
    }

    @ParameterizedTest
    @EnumSource(
            value = Direction.class,
            names = {"UP", "DOWN", "RIGHT"})
    void ifGameFieldWereShiftedThanReturnsDirection(Direction direction) throws IOException, GameFieldException {
        Reader reader = new Reader("src/test/resources/gameFieldIsSolvable");
        GameField gameField = new GameField(reader);

        GameField gameFieldAfterRightShift = GameFieldShifter.shift(gameField, direction);

        assertEquals(direction, GameFieldShifter.getShiftDirection(gameField, gameFieldAfterRightShift),
                "Shifted direction is not found");
    }

    @Test
    void ifAnyOfGameFieldsForGettingShiftedDirectionIsNullThanReturnsNull() throws IOException, GameFieldException {
        Reader reader = new Reader("src/test/resources/gameFieldIsSolvable");
        GameField gameField1 = new GameField(reader);
        GameField gameField2 = null;

        assertEquals(null, GameFieldShifter.getShiftDirection(gameField1, gameField2),
                "Shift is unreachable for null GameField");
        assertEquals(null, GameFieldShifter.getShiftDirection(gameField2, gameField1),
                "Shift is unreachable for null GameField");
    }

    @Test
    void ifGameFieldsWereNotReceivedByShiftThanReturnsNull() throws IOException, GameFieldException {
        Reader reader1 = new Reader("src/test/resources/gameFieldIsSolvable");
        GameField gameField1 = new GameField(reader1);

        Reader reader2 = new Reader("src/test/resources/gameFieldWith1StepSolution");
        GameField gameField2 = new GameField(reader2);

        assertEquals(null, GameFieldShifter.getShiftDirection(gameField1, gameField2),
                "Shift is unreachable");
    }
}
