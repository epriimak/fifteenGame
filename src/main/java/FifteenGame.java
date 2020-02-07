import java.io.IOException;
import java.util.List;

public class FifteenGame {
    public static void main(String[] args) throws IOException, GameFieldException{
        InputReader inputReader = new InputReader("src/test/resources/3x3field");
        GameField gameField = new GameField(inputReader);
        System.out.println("Game Started");
        List<GameField> solutionGameFieldSequence = new Solver().run(gameField);
        for(GameField field : solutionGameFieldSequence)
            field.print();
    }
}
