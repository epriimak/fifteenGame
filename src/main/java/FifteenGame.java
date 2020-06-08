import GameConfig.GameField;
import GameConfig.GameFieldException;
import IO.Reader;
import IO.Writer;
import Solution.Solver;
import Solution.SolverException;

import java.io.IOException;

/**
 * Main game class
 */
public class FifteenGame {
    public static final String DEFAULT_SOLUTION = "-1";

    /**
     * Running game solution
     * @param inputFileName input game field configuration
     * @param outputFileName output file name for writing solution
     * @throws IOException
     */
    public static void run(String inputFileName, String outputFileName) throws IOException {
        Reader reader = new Reader(inputFileName);
        Writer writer = new Writer(outputFileName);

        try {
            GameField gameField = new GameField(reader);
            Solver solver = new Solver(gameField);
            writer.write(solver.getSolutionAsString());
        } catch (GameFieldException | SolverException e) {
            e.printStackTrace();
            writer.write(DEFAULT_SOLUTION);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            run(args[0], args[1]);
        } else {
            throw new IllegalArgumentException("Incorrect number of command arguments");
        }
    }
}
