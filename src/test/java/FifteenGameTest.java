import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class FifteenGameTest {

    @ParameterizedTest
    @ValueSource(strings={"src/test/resources/gameFieldHas3Rows",
            "src/test/resources/gameFieldIsNotSolvable"})
    void ifAnyExceptionThrowsThanPrintedDefaultSolutionInFile(String inputFileName) throws IOException {
        String outputFileName = "src/test/resources/defaultGameSolution";
        FifteenGame.run(inputFileName, outputFileName);

        List<String> lines = Files.readAllLines(Paths.get(outputFileName));
        assertThat(lines).hasSize(1);

        String actualSolution = lines.get(0);
        assertThat(actualSolution).matches(FifteenGame.DEFAULT_SOLUTION);
    }

    @Test
    void ifThereIsNoExceptionsThanSolutionPrintedInFile() throws IOException {
        String inputFileName = "src/test/resources/gameFieldWith1StepSolution";
        String expectedSolutionFileName = "src/test/resources/idealSolutionForGameFieldWith1StepSolution";
        String actualSolutionFileName = "src/test/resources/solutionForGameFieldWith1StepSolution";

        FifteenGame.run(inputFileName, actualSolutionFileName);

        List<String> actualSolution = Files.readAllLines(Paths.get(actualSolutionFileName));
        List<String> expectedSolution = Files.readAllLines(Paths.get(expectedSolutionFileName));

        assertEquals(expectedSolution, actualSolution,
                "Solution is not correct");
    }
}
