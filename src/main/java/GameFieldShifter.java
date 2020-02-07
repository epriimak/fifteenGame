import java.util.ArrayList;
import java.util.List;

public class GameFieldShifter {

    private List<Integer> listOfChangedElements(List<Integer> list, int pos1, int pos2) {
        List<Integer> newList = new ArrayList<>(list);

        Integer cell1 = newList.get(pos1);
        Integer cell2 = newList.get(pos2);

        newList.set(pos1, cell2);
        newList.set(pos2, cell1);

        return newList;
    }

    public GameField shift(GameField gameField, Direction direction){
        List<Integer> field = gameField.getGameField();
        int zeroCellIndex = 0, futureCellIndex = 0;

        for (int i = 0; i < GameField.FIELD_SIZE; i++) {
            for (int j = 0; j < GameField.FIELD_SIZE; j++) {
                if (field.get(i * GameField.FIELD_SIZE + j) == 0) {
                    zeroCellIndex = i * GameField.FIELD_SIZE + j;
                    futureCellIndex = zeroCellIndex;
                    switch (direction) {
                        case DOWN:
                            if (i != GameField.FIELD_SIZE - 1) futureCellIndex += GameField.FIELD_SIZE;
                            break;

                        case UP:
                            if (i != 0) futureCellIndex -= GameField.FIELD_SIZE;
                            break;

                        case RIGHT:
                            if (j != GameField.FIELD_SIZE - 1) ++futureCellIndex;
                            break;

                        case LEFT:
                            if (j != 0) --futureCellIndex;
                            break;

                        default:
                            break;
                    }
                }
            }
        }
        if (zeroCellIndex != futureCellIndex) {
            return new GameField(listOfChangedElements(field, zeroCellIndex, futureCellIndex));
        } else
            return null;
    }
}
