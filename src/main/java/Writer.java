import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private String outputFileName;

    Writer(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    void write(String value) throws IOException {
        if (value.isEmpty())
            throw new NullPointerException("String is empty");

        try (FileWriter fileWriter = new FileWriter(outputFileName)) {
            fileWriter.write(value);
            fileWriter.flush();
        }
    }
}
