import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {
    private String outputFileName;
    private final String initialConfiguration = "Initial configuration:\n\n";
    private final String numberOfMovements = "Number of movements: ";
    private final String defaultOutputValue = "-1";

    Writer(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    private String getSolutionAsString(List<GameField> gameFieldList, List<Direction> directionList){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(initialConfiguration);
        stringBuilder.append(gameFieldList.get(0).toString());
        stringBuilder.append(numberOfMovements + directionList.size() + "\n\n");

        for (int i = 0; i < directionList.size(); i++) {
            stringBuilder.append(directionList.get(i).name() + "\n");
            stringBuilder.append(gameFieldList.get(i + 1).toString());
        }
        return stringBuilder.toString();
    }

    void writeSolution(Solver solver) throws IOException {
        List<GameField> gameFieldList = solver.getResultGameFieldSequence();
        List<Direction> directionList = solver.getResultDirectionSequence();

        try (FileWriter fileWriter = new FileWriter(outputFileName)) {
            String solution = getSolutionAsString(gameFieldList, directionList);
            fileWriter.write(solution);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    void writeDefaultValue() throws IOException {
        try(FileWriter fileWriter = new FileWriter(outputFileName)) {
            fileWriter.write(defaultOutputValue);
            fileWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
            throw e;
        }
    }
}
