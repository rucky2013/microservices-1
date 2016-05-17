package org.yvasilchuk.exceptions;

public class AuthenticationException extends FatException {
    private static final long serialVersionUID = -5399696662592961060L;

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }
}
