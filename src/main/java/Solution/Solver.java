package Solution;

import GameConfig.Direction;
import GameConfig.GameField;
import GameConfig.GameFieldShifter;

import java.util.*;

public class Solver {

    private List<GameField> resultGameFieldSequence = new ArrayList<>();
    private static final int MAX_COUNT_ITERATIONS = 1000;

    private class GameFieldItem {
        private GameFieldItem prevGameField;
        private GameField gameField;

        private GameFieldItem(GameFieldItem prevGameField, GameField gameField) {
            this.prevGameField = prevGameField;
            this.gameField = gameField;
        }
    }

    public Solver(GameField field) throws SolverException {
        PriorityQueue<GameFieldItem> priorityQueue = new PriorityQueue<>(10,
                Comparator.comparingInt(this::measure));

        priorityQueue.add(new GameFieldItem(null, field));

        int iterationsCount = 0;

        while (iterationsCount++ < MAX_COUNT_ITERATIONS) {
            GameFieldItem gameFieldItem = priorityQueue.poll();

            if (gameFieldItem == null)
                throw new SolverException("Head of solver queue is empty");

            if (isSolved(gameFieldItem)) {
                createSolutionGameFieldSequence(new GameFieldItem(gameFieldItem, gameFieldItem.gameField));
                return;
            }

            for (GameField neighbor : gameFieldItem.gameField.getNeighbors()) {
                if (neighbor != null && !itemWasInPath(gameFieldItem, neighbor))
                    priorityQueue.add(new GameFieldItem(gameFieldItem, neighbor));
            }
        }

        throw new SolverException("Solution can't be found");
    }

    private boolean isSolved(GameFieldItem gameFieldItem) {
        return gameFieldItem.gameField.getH() == 0;
    }

    private List<Direction> getResultDirectionSequence() {
        List<Direction> resultDirectionSequence = new ArrayList<>();

        for (int i = 0; i < resultGameFieldSequence.size() - 1; i++)
            resultDirectionSequence.add(
                    GameFieldShifter.getShiftDirection(
                            resultGameFieldSequence.get(i), resultGameFieldSequence.get(i + 1)));

        return resultDirectionSequence;
    }

    public List<GameField> getResultGameFieldSequence() {
        return resultGameFieldSequence;
    }

    public String getSolutionAsString() {
        String initialConfiguration = "Initial configuration:\n\n";
        String numberOfMovements = "Number of movements: ";
        List<Direction> directionList = getResultDirectionSequence();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(initialConfiguration);
        stringBuilder.append(resultGameFieldSequence.get(0).toString());
        stringBuilder.append(numberOfMovements)
                .append(directionList.size())
                .append("\n\n");

        for (int i = 0; i < directionList.size(); i++) {
            stringBuilder.append(directionList.get(i).name()).append("\n");
            stringBuilder.append(resultGameFieldSequence.get(i + 1).toString());
        }
        return stringBuilder.toString();
    }

    private int measure(GameFieldItem item) {
        GameFieldItem gameFieldItem = item;
        int countNotEmptyElements = 0;
        int measure = item.gameField.getH();

        while (gameFieldItem != null) {
            countNotEmptyElements++;
            gameFieldItem = gameFieldItem.prevGameField;
        }

        return measure + countNotEmptyElements;
    }

    private void createSolutionGameFieldSequence(GameFieldItem item) {
        GameFieldItem gameFieldItem = item;

        do {
            gameFieldItem = gameFieldItem.prevGameField;
            resultGameFieldSequence.add(gameFieldItem.gameField);
        } while (gameFieldItem.prevGameField != null);

        Collections.reverse(resultGameFieldSequence);
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