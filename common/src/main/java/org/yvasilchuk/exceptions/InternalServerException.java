package org.yvasilchuk.exceptions;

public class InternalServerException extends FatException {
    private static final long serialVersionUID = -2336099384058225618L;

    public InternalServerException() {
    }

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(Throwable cause) {
        super(cause);
    }
}
