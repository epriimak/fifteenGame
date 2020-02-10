import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Reader {

    private String inputFileName;

    Reader(String fileName) {
        inputFileName = fileName;
    }

    public List<String> readDataLinesFromFile() throws IOException {
        try {
            return Files.readAllLines(Paths.get(inputFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
