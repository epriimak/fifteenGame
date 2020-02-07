import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputReaderTest {

    @Test
    void ifFileNotFoundThenExceptionThrows() {
        InputReader inputReader = new InputReader("src/test/resources/notExistsFile");

        assertThrows(IOException.class,
                () -> inputReader.readDataLinesFromFile());
    }

    @Test
    void ifFileIsEmptyThenReturnsEmptyList() throws IOException {
        InputReader inputReader = new InputReader("src/test/resources/emptyFile");

        List<String> result = inputReader.readDataLinesFromFile();

        assertThat(result).isEmpty();
    }

    @Test
    void ifFileIsNotEmptyThenReturnsListOfLines() throws IOException {
        InputReader inputReader = new InputReader("src/test/resources/gameField");

        List<String> result = inputReader.readDataLinesFromFile();

        assertThat(result).hasSize(GameField.FIELD_SIZE);
    }
}

