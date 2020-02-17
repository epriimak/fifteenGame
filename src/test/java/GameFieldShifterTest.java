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
    void ifGameShiftIsPossibleThanReturnsNewField(Direction direction) throws IOException, GameFieldException {
        Reader reader = new Reader("src/test/resources/gameFieldIsSolvable");
        GameField gameField = new GameField(reader);

        assertNotNull(GameFieldShifter.shift(gameField, direction));
    }

    @ParameterizedTest
    @EnumSource(
            value = Direction.class,
            names = {"LEFT"})
    void ifGameShiftIsNotPossibleThanReturnNull(Direction direction) throws IOException, GameFieldException {
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
    void ifGameFieldsWereNotReceivedByShiftThanReturnsUndefinedDirection() throws IOException, GameFieldException {
        Reader reader1 = new Reader("src/test/resources/gameFieldIsSolvable");
        GameField gameField1 = new GameField(reader1);

        Reader reader2 = new Reader("src/test/resources/gameFieldWith1StepSolution");
        GameField gameField2 = new GameField(reader2);

        assertEquals(Direction.UNDEFINED, GameFieldShifter.getShiftDirection(gameField1, gameField2),
                "Shift is not undefined for non-shifted fields");
    }

    @Test
    void ifAnyOfGameFieldsForGettingShiftedDirectionIsNullThanReturnsUndefinedException() throws IOException, GameFieldException {
        Reader reader = new Reader("src/test/resources/gameFieldIsSolvable");
        GameField gameField1 = new GameField(reader);
        GameField gameField2 = null;

        assertEquals(Direction.UNDEFINED, GameFieldShifter.getShiftDirection(gameField1, gameField2),
                "Shift is not undefined for null GameField");
        assertEquals(Direction.UNDEFINED, GameFieldShifter.getShiftDirection(gameField2, gameField1),
                "Shift is not undefined for null GameField");
    }
}
