package IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class provides reading list of string from input file
 */
public class Reader {
    private String inputFileName;

    public Reader(String fileName) {
        inputFileName = fileName;
    }

    public List<String> readDataLinesFromFile() throws IOException {
        return Files.readAllLines(Paths.get(inputFileName));
    }
}
