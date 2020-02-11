import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WriterTest {

    @ParameterizedTest
    @ValueSource(strings = {"-1", "Any text"})
    void ifStringIsNotEmptyThanNoExceptionThrows(String value){
        Writer writer = new Writer("src/test/resources/testNotNullString");

        assertDoesNotThrow(() -> writer.write(value));
    }


    @Test
    void ifStringIsEmptyThanExceptionThrows() {
        Writer writer = new Writer("src/test/resources/testEmptyString");
        String emptyString = "";

        assertThrows(NullPointerException.class, () -> writer.write(emptyString));
    }

    @Test
    void ifStringIsNullThanExceptionThrows() {
        Writer writer = new Writer("src/test/resources/testNullString");

        assertThrows(NullPointerException.class, () -> writer.write(null));
    }
}
