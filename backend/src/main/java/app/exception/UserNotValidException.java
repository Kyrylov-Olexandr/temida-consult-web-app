package app.exception;

public class UserNotValidException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "User is not valid";

    public UserNotValidException(String message) {
        super(message);
    }

    public UserNotValidException() {
        super(DEFAULT_MESSAGE);
    }
}


