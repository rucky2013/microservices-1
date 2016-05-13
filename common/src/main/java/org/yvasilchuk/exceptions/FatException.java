package org.yvasilchuk.exceptions;

public class FatException extends RuntimeException {
    private static final long serialVersionUID = 766481503376454498L;

    public FatException() {
    }

    public FatException(String message) {
        super(message);
    }

    public FatException(String message, Throwable cause) {
        super(message, cause);
    }

    public FatException(Throwable cause) {
        super(cause);
    }
}
