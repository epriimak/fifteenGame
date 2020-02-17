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
        } catch (GameFieldException | SolverException e) {
            e.printStackTrace();
            writer.write(defaultSolution);
        }
    }

    public static void main(String[] args) throws IOException {
        run(args[0], args[1]);
    }
}
