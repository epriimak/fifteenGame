package GameConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Class provides new GameField
 * that was created by shifting "zero" cell by such direction
 */
public class GameFieldShifter {

    private GameFieldShifter(){}

    /**
     * Change elements in pos1 and pos2 in List
     * @return new list woth changed elements
     */
    private static List<Integer> listOfChangedElements(List<Integer> list, int pos1, int pos2) {
        List<Integer> newList = new ArrayList<>(list);

        Integer cell1 = newList.get(pos1);
        Integer cell2 = newList.get(pos2);

        newList.set(pos1, cell2);
        newList.set(pos2, cell1);

        return newList;
    }

    /**
     * Get new index for "zero" cell due to direction
     */
    private static int getNextIndex(int row, int column, int zeroIndex, Direction direction){
        switch (direction) {
            case DOWN:
                if (row != GameField.FIELD_SIZE - 1)
                    return zeroIndex + GameField.FIELD_SIZE;
                break;

            case UP:
                if (row != 0)
                    return zeroIndex - GameField.FIELD_SIZE;
                break;

            case RIGHT:
                if (column != GameField.FIELD_SIZE - 1)
                    return ++zeroIndex;
                break;

            case LEFT:
                if (column != 0)
                    return --zeroIndex;
                break;

            default:
                break;
        }
        return zeroIndex;
    }

    /**
     * Get new GameField due to moving "zero" cell in such direction
     */
    public static GameField shift(GameField gameField, Direction direction) {
        List<Integer> field = gameField.getGameField();
        int zeroCellIndex = 0;
        int futureCellIndex = 0;

        for (int i = 0; i < GameField.FIELD_SIZE; i++) {
            for (int j = 0; j < GameField.FIELD_SIZE; j++) {
                if (field.get(i * GameField.FIELD_SIZE + j) == 0) {
                    zeroCellIndex = i * GameField.FIELD_SIZE + j;
                    futureCellIndex = getNextIndex(i, j, zeroCellIndex, direction);
                }
            }
        }

        if (zeroCellIndex != futureCellIndex) {
            return new GameField(listOfChangedElements(field, zeroCellIndex, futureCellIndex));
        } else {
            return null;
        }
    }

    /**
     * Find direction that gameField was created from prevGameField
     */
    public static Direction getShiftDirection(GameField prevGameField, GameField gameField) {
        if (prevGameField == null || gameField == null)
            return Direction.UNDEFINED;

        for (Direction direction : Direction.values()) {
            GameField shiftedGameField = shift(prevGameField, direction);
            if (shiftedGameField != null && shiftedGameField.equals(gameField))
                return direction;
        }
        return Direction.UNDEFINED;
    }
}
