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
        Reader reader = new Reader(fileName);

        assertThrows(GameFieldException.class,
                () -> new GameField(reader));
    }

    @Test
    void ifGameFieldIsWellFormedAndSolvableThanNoExceptionThrows() {
        Reader reader = new Reader("src/test/resources/gameFieldIsSolvable");

        assertDoesNotThrow(() -> new GameField(reader));
    }

    @Test
    void ifGameFieldHasNoSolutionThanExceptionThrows() {
        Reader reader = new Reader("src/test/resources/gameFieldIsNotSolvable");

        assertThrows(GameFieldException.class, () -> new GameField(reader));
    }

}
