package org.yvasilchuk.domain.requests;

import org.yvasilchuk.domain.messages.ValidationMessages;
import org.yvasilchuk.domain.model.enums.RegistrationType;
import org.yvasilchuk.domain.model.security.FacebookCredentials;
import org.yvasilchuk.domain.model.security.TwitterCredentials;
import org.yvasilchuk.domain.model.security.WebRegistrationDetails;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RegistrationRequest implements Serializable {
    private static final long serialVersionUID = -3790418381887908777L;

    @NotNull(message = ValidationMessages.PLATFORM_TYPE_REQUIRED)
    RegistrationType registrationType;

    WebRegistrationDetails webDetails;
    FacebookCredentials fbCredentials;
    TwitterCredentials twitterCredentials;

    public RegistrationRequest() {
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    public WebRegistrationDetails getWebDetails() {
        return webDetails;
    }

    public void setWebDetails(WebRegistrationDetails webDetails) {
        this.webDetails = webDetails;
    }

    public FacebookCredentials getFbCredentials() {
        return fbCredentials;
    }

    public void setFbCredentials(FacebookCredentials fbCredentials) {
        this.fbCredentials = fbCredentials;
    }

    public TwitterCredentials getTwitterCredentials() {
        return twitterCredentials;
    }

    public void setTwitterCredentials(TwitterCredentials twitterCredentials) {
        this.twitterCredentials = twitterCredentials;
    }
}
