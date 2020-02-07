import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameField {
    public static final int FIELD_SIZE = 3;
    public static final int CELLS_COUNT = FIELD_SIZE * FIELD_SIZE;

    private List<Integer> field = new ArrayList<>();
    private int h;

    GameField(InputReader inputReader) throws IOException, GameFieldException {
        List<List<Integer>> gameFieldAsListOfList = readGameFieldAsListOfList(inputReader);

        if (!fieldSizeIsCorrect(gameFieldAsListOfList))
            throw new GameFieldException("Field size is incorrect");
        else if (!fieldHasNumbersFrom0To15(gameFieldAsListOfList))
            throw new GameFieldException("Field numbers are incorrect");
        else if (!fieldIsSolvable(gameFieldAsListOfList))
            throw new GameFieldException("Game field is not solvable");
        else {
            setField(gameFieldAsListOfList);
            setH();
        }
    }

    GameField(List<Integer> configuration) {
        this.field = configuration;
        setH();
    }

    private List<List<Integer>> readGameFieldAsListOfList(InputReader inputReader) throws IOException {
        List<String> lines = inputReader.readDataLinesFromFile();

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
                gameField.stream().mapToInt(list -> list.size()).allMatch(num -> num == FIELD_SIZE);
    }

    private boolean fieldIsSolvable(List<List<Integer>> gameField) {
        List<Integer> gameFieldList = new ArrayList<>();
        gameField.forEach((list) -> gameFieldList.addAll(list));

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
        gameField.forEach((list) -> numbers.addAll(list));

        List<Integer> correctNumbers = Stream.iterate(0, i -> i + 1).
                limit(FIELD_SIZE * FIELD_SIZE).collect(Collectors.toList());

        return numbers.containsAll(correctNumbers);

    }

    public List<Integer> getGameField() {
        return field;
    }

    private void setField(List<List<Integer>> gameField) {
        gameField.forEach((line) -> field.addAll(line));
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

        neighborsList.add(new GameFieldShifter().shift(this, Direction.UP));
        neighborsList.add(new GameFieldShifter().shift(this, Direction.DOWN));
        neighborsList.add(new GameFieldShifter().shift(this, Direction.LEFT));
        neighborsList.add(new GameFieldShifter().shift(this, Direction.RIGHT));

        return neighborsList;
    }

    public void print() {
        String startLineRegex = "[ ";
        String endLineRegex = "]\n";
        String regex = " ";

        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print(startLineRegex);
            for (int j = 0; j < FIELD_SIZE; j++)
                System.out.print(field.get(i * FIELD_SIZE + j) + regex);
            System.out.print(endLineRegex);
        }
        System.out.println("\n");
    }
}

class GameFieldException extends Exception {
    GameFieldException(String message) {
        super(message);
    }
}

