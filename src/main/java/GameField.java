import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameField {
    public static final int FIELD_SIZE = 4;
    public static final int CELLS_COUNT = FIELD_SIZE * FIELD_SIZE;

    private List<Integer> field = new ArrayList<>();
    private int h;

    GameField(Reader reader) throws IOException, GameFieldException {
        List<List<Integer>> gameFieldAsListOfList = readGameFieldAsListOfList(reader);

        if (!fieldSizeIsCorrect(gameFieldAsListOfList)) {
            throw new GameFieldException("Field size is incorrect");
        } else if (!fieldHasNumbersFrom0To15(gameFieldAsListOfList)) {
            throw new GameFieldException("Field numbers are incorrect");
        } else if (!fieldIsSolvable(gameFieldAsListOfList)) {
            throw new GameFieldException("Game field is not solvable");
        } else {
            setField(gameFieldAsListOfList);
            setH();
        }
    }

    GameField(List<Integer> configuration) {
        this.field = configuration;
        setH();
    }

    private List<List<Integer>> readGameFieldAsListOfList(Reader reader) throws IOException {
        List<String> lines = reader.readDataLinesFromFile();

        return lines.stream()
                .map(line -> Arrays.asList(line.split("\\b[\\]\\[\\s]+")))
                .map(list -> list.stream()
                        .map(string -> string.replaceAll("\\D", ""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }

    private boolean fieldSizeIsCorrect(List<List<Integer>> gameField) {
        return gameField.size() == FIELD_SIZE &&
                gameField.stream()
                        .mapToInt(List::size)
                        .allMatch(num -> num == FIELD_SIZE);
    }

    private boolean fieldIsSolvable(List<List<Integer>> gameField) {
        List<Integer> gameFieldList = new ArrayList<>();
        gameField.forEach(gameFieldList::addAll);

        int countInversions = 0;
        for (int i = 0; i < GameField.CELLS_COUNT - 1; i++) {
            for (int j = 0; j < i; j++) {
                if (gameFieldList.get(j) > gameFieldList.get(i)) {
                    countInversions++;
                }
            }
        }
        return countInversions % 2 == 0;
    }

    private boolean fieldHasNumbersFrom0To15(List<List<Integer>> gameField) {
        List<Integer> numbers = new ArrayList<>();
        gameField.forEach(numbers::addAll);

        List<Integer> correctNumbers = Stream.iterate(0, i -> i + 1)
                .limit(FIELD_SIZE * FIELD_SIZE)
                .collect(Collectors.toList());

        return numbers.containsAll(correctNumbers);
    }

    public List<Integer> getGameField() {
        return field;
    }

    private void setField(List<List<Integer>> gameField) {
        gameField.forEach(line -> field.addAll(line));
    }

    private void setH() {
        for (int i = 0; i < CELLS_COUNT; i++)
            if (field.get(i) != i + 1 && field.get(i) != 0)
                h++;
    }

    public int getH() {
        return h;
    }

    public List<GameField> getNeighbors() {
        List<GameField> neighborsList = new ArrayList<>();

        neighborsList.add(GameFieldShifter.shift(this, Direction.UP));
        neighborsList.add(GameFieldShifter.shift(this, Direction.DOWN));
        neighborsList.add(GameFieldShifter.shift(this, Direction.LEFT));
        neighborsList.add(GameFieldShifter.shift(this, Direction.RIGHT));

        return neighborsList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String startLineRegex = "[ ";
        String endLineRegex = "]\n";
        String regex = " ";

        for (int i = 0; i < FIELD_SIZE; i++) {
            stringBuilder.append(startLineRegex);
            for (int j = 0; j < FIELD_SIZE; j++)
                stringBuilder.append(field.get(i * FIELD_SIZE + j) + regex);
            stringBuilder.append(endLineRegex);
        }

        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        GameField gameField = (GameField) o;

        for (int i = 0; i < CELLS_COUNT; i++) {
            if (!this.field.get(i).equals(gameField.field.get(i)))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }
}

class GameFieldException extends Exception {
    GameFieldException(String message) {
        super(message);
    }
}

