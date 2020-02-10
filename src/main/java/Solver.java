import java.util.*;

public class Solver {

    private List<GameField> resultGameFieldSequence = new ArrayList<>();
    private final int MAX_COUNT_ITERATIONS = 1000;

    private class GameFieldItem {
        private GameFieldItem prevGameField;
        private GameField gameField;

        private GameFieldItem(GameFieldItem prevGameField, GameField gameField) {
            this.prevGameField = prevGameField;
            this.gameField = gameField;
        }
    }

    Solver(GameField field) throws SolverException {
        PriorityQueue<GameFieldItem> priorityQueue = new PriorityQueue<>(10,
                (o1, o2) -> new Integer(measure(o1)).compareTo(measure(o2)));

        priorityQueue.add(new GameFieldItem(null, field));

        int iterationsCount = 0;

        while (iterationsCount++ < MAX_COUNT_ITERATIONS) {
            GameFieldItem gameFieldItem = priorityQueue.poll();

            if (isSolved(gameFieldItem)) {
                createSolutionGameFieldSequence(new GameFieldItem(gameFieldItem, gameFieldItem.gameField));
                return;
            }

            for (GameField field1 : gameFieldItem.gameField.getNeighbors()) {
                if (field1 != null && !itemWasInPath(gameFieldItem, field1))
                    priorityQueue.add(new GameFieldItem(gameFieldItem, field1));
            }
        }

        throw new SolverException("Solution can't be found");
    }

    private boolean isSolved(GameFieldItem gameFieldItem) {
        return gameFieldItem.gameField.getH() == 0;
    }

    public List<GameField> getResultGameFieldSequence() {
        return resultGameFieldSequence;
    }

    public List<Direction> getResultDirectionSequence() {
        List<Direction> resultDirectionSequence = new ArrayList<>();

        for (int i = 0; i < resultGameFieldSequence.size() - 1; i++)
            resultDirectionSequence.add(GameFieldShifter.getShiftDirection(
                    resultGameFieldSequence.get(i), resultGameFieldSequence.get(i + 1)));

        return resultDirectionSequence;
    }

    private int measure(GameFieldItem item) {
        GameFieldItem gameFieldItem = item;
        int c = 0;
        int measure = item.gameField.getH();
        while (gameFieldItem != null) {
            c++;
            gameFieldItem = gameFieldItem.prevGameField;
        }

        return measure + c;
    }

    private void createSolutionGameFieldSequence(GameFieldItem item) {
        GameFieldItem gameFieldItem = item;
        while (true) {
            gameFieldItem = gameFieldItem.prevGameField;
            if (gameFieldItem == null) {
                Collections.reverse(resultGameFieldSequence);
                return;
            }
            resultGameFieldSequence.add(gameFieldItem.gameField);
        }
    }

    private boolean itemWasInPath(GameFieldItem item, GameField gameField) {
        GameFieldItem gameFieldItem = item;
        while (gameFieldItem != null) {
            if (gameFieldItem.gameField.equals(gameField))
                return true;
            gameFieldItem = gameFieldItem.prevGameField;
        }
        return false;
    }
}

class SolverException extends Exception {
    SolverException(String message) {
        super(message);
    }
}