package GameConfig;

/**
 * Exception throws if Game Field is incorrect or not solvable
 */
public class GameFieldException extends Exception {
    GameFieldException(String message) {
        super(message);
    }
}
