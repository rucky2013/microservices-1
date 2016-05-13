package org.yvasilchuk.domain.model.security;

import org.yvasilchuk.domain.messages.ValidationMessages;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FacebookCredentials implements Serializable {
    private static final long serialVersionUID = 6644607697364633421L;

    @NotNull(message = ValidationMessages.FB_ACCESS_TOKEN_REQUIRED)
    private String accessToken;

    public FacebookCredentials() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
