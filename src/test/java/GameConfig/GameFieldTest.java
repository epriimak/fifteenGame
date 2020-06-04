package GameConfig;

import IO.Reader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameFieldTest {

    @Test
    void ifGameFieldHasInvalidSizeThanExceptionThrows() {
        Reader reader = new Reader("src/test/resources/gameFieldHas3Rows");

        assertThrows(GameFieldException.class,
                () -> new GameField(reader));
    }

    @Test
    void ifGameFieldHasIncorrectNumbersThanExceptionThrows() {
        Reader reader = new Reader("src/test/resources/gameFieldContains99");

        assertThrows(GameFieldException.class,
                () -> new GameField(reader));
    }

    @Test
    void ifGameFieldHasNoSolutionThanExceptionThrows() {
        Reader reader = new Reader("src/test/resources/gameFieldIsNotSolvable");

        assertThrows(GameFieldException.class,
                () -> new GameField(reader));
    }

    @Test
    void ifGameFieldIsWellFormedAndSolvableThanNoExceptionThrows() {
        Reader reader = new Reader("src/test/resources/gameFieldIsSolvable");

        assertDoesNotThrow(() -> new GameField(reader));
    }

}
