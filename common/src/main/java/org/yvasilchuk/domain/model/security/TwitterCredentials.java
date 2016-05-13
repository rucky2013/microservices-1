package org.yvasilchuk.domain.model.security;

import org.yvasilchuk.domain.messages.ValidationMessages;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TwitterCredentials implements Serializable {
    private static final long serialVersionUID = 6943144759055576152L;

    @NotNull(message = ValidationMessages.TW_ACCESS_KEY_REQUIRED)
    private String twitterKey;

    @NotNull(message = ValidationMessages.TW_ACCESS_SECRET_REQUIRED)
    private String twitterSecret;

    public TwitterCredentials() {
    }

    public String getTwitterSecret() {
        return twitterSecret;
    }

    public void setTwitterSecret(String twitterSecret) {
        this.twitterSecret = twitterSecret;
    }

    public String getTwitterKey() {
        return twitterKey;
    }

    public void setTwitterKey(String twitterKey) {
        this.twitterKey = twitterKey;
    }
}
