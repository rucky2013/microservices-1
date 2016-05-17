package org.yvasilchuk.exceptions;

public class DatabaseException extends FatException {
    private static final long serialVersionUID = -1482854238828372023L;

    public DatabaseException() {
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
