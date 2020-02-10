import java.io.IOException;
import java.util.List;

public class FifteenGame {
    private static void run(String inputFileName, String outputFileName) throws IOException {
        Reader reader = new Reader(inputFileName);
        Writer writer = new Writer(outputFileName);

        try {
            GameField gameField = new GameField(reader);
            Solver solver = new Solver(gameField);
            writer.writeSolution(solver);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (GameFieldException|SolverException e) {
            e.printStackTrace();
            writer.writeDefaultValue();
        }
    }

    public static void main(String[] args) throws IOException{
        run("src/test/resources/gameFieldWith1StepSolution", "solution");
    }
}
