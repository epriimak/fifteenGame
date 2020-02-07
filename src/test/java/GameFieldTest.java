import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameFieldTest {

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/gameFieldHas3Rows",
            "src/test/resources/gameFieldContains99"})
    void ifGameFieldHasInvalidFormatThanExceptionThrows(String fileName) {
        InputReader inputReader = new InputReader(fileName);

        assertThrows(GameFieldException.class,
                () -> new GameField(inputReader));
    }

    @Test
    void ifGameFieldIsWellFormedAndSolvableThanNoExceptionThrows() {
        InputReader inputReader = new InputReader("src/test/resources/gameField");

        assertDoesNotThrow(() -> new GameField(inputReader));
    }

    @Test
    void ifGameFieldHasSolutionThanNoExceptionThrows(){
        InputReader inputReader = new InputReader("src/test/resources/gameField");

        assertDoesNotThrow(() -> new GameField(inputReader));
    }

    @Test
    void ifGameFieldHasNoSolutionThanExceptionThrows() {
        InputReader inputReader = new InputReader("src/test/resources/gameFieldHasNoSolution");

        assertThrows(GameFieldException.class, () -> new GameField(inputReader));
    }

}
