package org.yvasilchuk.domain.messages;

public interface ErrorMessages {
    String TOO_MUCH_COINS = "Too much coins";
    String AUTHENTICATION_EXCEPTION = "Unable to authenticate user.";
    String LOGOUT_EXCEPTION = "Unable to logout user.";
    String NOT_AUTHORIZED = "User not authorized";
    String USERNAME_NOT_FOUND = "User with provided username not found.";
    String INVALID_CREDENTIALS = "Invalid credentials.";
    String FB_NOT_AUTHORIZED = "User is not authorized on facebook.";
    String FB_CANT_PARSE_ID = "Can not retrieve FB id.";
    String TW_NOT_AUTHORIZED = "User is not authorized in twitter.";
    String SYSTEM_FATAL = "SYSTEM FATAL ERROR.";
    String DB_INTERNAL_ERROR = "Something bad happens on server side.";
    String SIGNUP_INVALID_REGISTRATION_TYPE = "Unsupported registration type.";
    String INVALID_REQUEST = "Invalid request data.";
    String UNKNOWN_PLATFORM_TYPE = "Unknown platform type.";
    String UNSUPPORTED_CASH_OPERATION = "Cash operation type not supported.";
    String ACCESS_DENIED = "Access denied.";
    String USER_NOT_FOUND = "User not found.";
}
