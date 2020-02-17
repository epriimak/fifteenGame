import java.io.IOException;

public class FifteenGame {
    public static final String defaultSolution = "-1";

    public static void run(String inputFileName, String outputFileName) throws IOException {
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
        if(args.length == 2) {
            run(args[0], args[1]);
        } else{
            throw new IllegalArgumentException("Incorrect number of command arguments");
        }
    }
}
