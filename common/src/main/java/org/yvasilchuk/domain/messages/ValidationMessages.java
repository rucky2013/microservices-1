package org.yvasilchuk.domain.messages;

public interface ValidationMessages {
    String LOGIN_REQUIRED = "Login is required.";
    String PASSWORD_REQUIRED = "Password is required.";
    String PLATFORM_INFO_REQUIRED = "Platform info is required.";
    String PLATFORM_TYPE_REQUIRED = "Platform type is required.";
    String FB_ACCESS_TOKEN_REQUIRED = "FaceBook access token is required.";
    String TW_ACCESS_KEY_REQUIRED = "Twitter access key is required.";
    String TW_ACCESS_SECRET_REQUIRED = "Twitter access secret is required.";
    String EMAIL_REQUIRED = "Email is required.";
    String NAME_REQUIRED = "Name is required.";

    String INVALID_EMAIL_LENGTH = "Email length must be between 6 and 512 characters.";
    String INVALID_NAME_LENGTH = "Name length must be lower than 512 characters.";
    String INVALID_PASSWORD_LENGTH = "Email length must be between 6 and 32 characters.";
    String INVALID_EMAIL_FORMAT = "Provided email has invalid format.";

    String CASH_ACCOUNT_OWNER_ID_REQUIRED = "";
    String CASH_ACCOUNT_CURRENCY_REQUIRED = "";
    String CASH_ACCOUNT_NAME_REQUIRED = "";
    String CASH_ACCOUNT_BALANCE_REQUIRED = "";
    String CASH_ACCOUNT_ID_REQUIRED = "";

    String CATEGORY_OWNER_ID_REQUIRED = "";
    String CATEGORY_NAME_REQUIRED = "";
    String CATEGORY_TYPE_REQUIRED = "";
    String CATEGORY_ID_REQUIRED = "";

    String OPERATION_SENDER_ACCOUNT_ID_REQUIRED = "";
    String OPERATION_TYPE_REQUIRED = "";
    String OPERATION_CATEGORY_ID_REQUIRED = "";
    String OPERATION_AMOUNT_REQUIRED = "";
    String OPERATION_RECIPIENT_ACCOUNT_ID_REQUIRED = "";
    String OPERATION_ID_REQUIRED = "";
}
