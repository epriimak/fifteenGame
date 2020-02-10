import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WriterTest {

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/gameFieldHas3Rows",
            "src/test/resources/gameFieldContains99",
            "src/test/resources/gameFieldIsNotSolvable",
            "src/test/resources/gameFieldIsSolvable"
    })
    void ifGameFieldIsUnattainableThanPrintedDefaultValueInFile(String fileName) throws IOException, GameFieldException, SolverException {
        Reader reader = new Reader(fileName);
        GameField gameField = new GameField(reader);
        Solver solver = new Solver(gameField);



        assertDoesNotThrow(() -> new Solver(gameField));
    }


    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/gameFieldWith1StepSolution",
            "src/test/resources/gameFieldSolution"})
    void ifGameFieldIsSolvableThanSolutionPrintedInFile(String fileName) {

    }
}
