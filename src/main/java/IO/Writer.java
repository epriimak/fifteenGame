package IO;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private String outputFileName;

    public Writer(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public void write(String value) throws IOException {
        if (value.isEmpty())
            throw new NullPointerException("String is empty");

        try (FileWriter fileWriter = new FileWriter(outputFileName)) {
            fileWriter.write(value);
            fileWriter.flush();
        }
    }
}
