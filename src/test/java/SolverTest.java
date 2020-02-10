import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class SolverTest {
    @Test
    void ifGameFieldWasSolvedItHasCorrectSolution() throws IOException, GameFieldException, SolverException {
        Reader reader1 = new Reader("src/test/resources/gameFieldWith1StepSolution");
        GameField gameField = new GameField(reader1);
        Reader reader2 = new Reader("src/test/resources/gameFieldSolution");
        GameField gameFieldSolution = new GameField(reader2);

        Solver solver = new Solver(gameField);
        List<GameField> gameFieldList = solver.getResultGameFieldSequence();
        GameField finalGameField = gameFieldList.get(gameFieldList.size() - 1);

        assertEquals(true, finalGameField.equals(gameFieldSolution), "The solution is not correct");
    }

    @Test
    void ifGameFieldSolutionHasMore1000IterationsThanExceptionThrows() throws IOException, GameFieldException {
        Reader reader = new Reader("src/test/resources/gameFieldIsSolvable");
        GameField gameField = new GameField(reader);

        assertThrows(SolverException.class, () -> new Solver(gameField));
    }

    @Test
    void ifGameFieldSolutionHasLess1000IterationsThanNoExceptionThrows() throws IOException, GameFieldException {
        Reader reader = new Reader("src/test/resources/gameFieldWith1StepSolution");
        GameField gameField = new GameField(reader);

        assertDoesNotThrow(() -> new Solver(gameField));
    }
}
