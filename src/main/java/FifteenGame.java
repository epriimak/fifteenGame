import java.io.IOException;

public class FifteenGame {
    private static final String defaultSolution = "-1";

    private static void run(String inputFileName, String outputFileName) throws IOException {
        Reader reader = new Reader(inputFileName);
        Writer writer = new Writer(outputFileName);

        try {
            GameField gameField = new GameField(reader);
            Solver solver = new Solver(gameField);
            writer.write(solver.getSolutionAsString());
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (GameFieldException | SolverException e) {
            e.printStackTrace();
            writer.write(defaultSolution);
        }
    }

    public static void main(String[] args) throws IOException {
        run("src/test/resources/gameFieldWith1StepSolution", "solution");
    }
}
