package org.yvasilchuk.exceptions;

public class EntityNotFoundException extends FatException {
    private static final long serialVersionUID = 4069266225466867255L;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
