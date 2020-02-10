import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReaderTest {

    @Test
    void ifFileNotFoundThenExceptionThrows() {
        Reader reader = new Reader("src/test/resources/notExistsFile");

        assertThrows(IOException.class,
                () -> reader.readDataLinesFromFile());
    }

    @Test
    void ifFileIsEmptyThenReturnsEmptyList() throws IOException {
        Reader reader = new Reader("src/test/resources/emptyFile");

        List<String> result = reader.readDataLinesFromFile();

        assertThat(result).isEmpty();
    }

    @Test
    void ifFileIsNotEmptyThenReturnsListOfLines() throws IOException {
        Reader reader = new Reader("src/test/resources/gameFieldIsSolvable");

        List<String> result = reader.readDataLinesFromFile();

        assertThat(result).hasSize(GameField.FIELD_SIZE);
    }
}

