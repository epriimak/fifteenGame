import java.util.*;

public class Solver {

    private class GameFieldItem {    // Need to remember previous positions
        private GameFieldItem prevGameField;
        private GameField gameField;

        private GameFieldItem(GameFieldItem prevGameField, GameField gameField) {
            this.prevGameField = prevGameField;
            this.gameField = gameField;
        }

        public GameField getGameField() {
            return gameField;
        }
    }

    List<GameField> run (GameField field) {
        List<GameField> result = new ArrayList<>();
        PriorityQueue<GameFieldItem> priorityQueue = new PriorityQueue<>(10,
                (o1, o2) -> new Integer(measure(o1)).compareTo(new Integer(measure(o2))));

        priorityQueue.add(new GameFieldItem(null, field));

        while (true) {
            GameFieldItem gameField = priorityQueue.poll();

            //save path if solution founded
            if (gameField.gameField.getH() == 0) {
                itemToList(new GameFieldItem(gameField, gameField.gameField), result);
                return result;
            }

            for (GameField field1 : gameField.gameField.getNeighbors()) {
                if (field1 != null && !wasInPath(gameField, field1))
                    priorityQueue.add(new GameFieldItem(gameField, field1));
            }
        }
    }

    private int measure(GameFieldItem item) {
        GameFieldItem item2 = item;
        int c = 0;
        int measure = item.getGameField().getH();
        while (true) {
            c++;
            item2 = item2.prevGameField;
            if (item2 == null) {
                return measure + c;
            }
        }
    }

    private void itemToList(GameFieldItem item, List<GameField> result) {
        GameFieldItem item2 = item;
        while (true) {
            item2 = item2.prevGameField;
            if (item2 == null) {
                Collections.reverse(result);
                return;
            }
            result.add(item2.gameField);
        }
    }

    private boolean wasInPath(GameFieldItem item, GameField board) {
        GameFieldItem item2 = item;
        while (true) {
            if (item2.gameField.equals(board)) return true;
            item2 = item2.prevGameField;
            if (item2 == null) return false;
        }
    }
}
