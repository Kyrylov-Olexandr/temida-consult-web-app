package app.exception;

public class InvalidPasswordException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Password length must be more then 3";

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException() {
        super(DEFAULT_MESSAGE);
    }
}

