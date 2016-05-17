package org.yvasilchuk.exceptions;

public class InvalidRequestException extends FatException {
    private static final long serialVersionUID = 2509736689537633401L;

    public InvalidRequestException() {
    }

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestException(Throwable cause) {
        super(cause);
    }
}
