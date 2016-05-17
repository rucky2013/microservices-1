package org.yvasilchuk.domain.requests;

import org.yvasilchuk.domain.messages.ValidationMessages;
import org.yvasilchuk.domain.model.security.FacebookCredentials;
import org.yvasilchuk.domain.model.security.TwitterCredentials;
import org.yvasilchuk.domain.model.security.WebCredentials;
import org.yvasilchuk.domain.model.util.PlatformInfo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SigninRequest implements Serializable {
    private static final long serialVersionUID = 4897920045509683722L;

    @NotNull(message = ValidationMessages.PLATFORM_INFO_REQUIRED)
    private PlatformInfo platformInfo;

    private FacebookCredentials facebookCredentials;
    private TwitterCredentials twitterCredentials;
    private WebCredentials webCredentials;

    public SigninRequest() {
    }

    public PlatformInfo getPlatformInfo() {
        return platformInfo;
    }

    public void setPlatformInfo(PlatformInfo platformInfo) {
        this.platformInfo = platformInfo;
    }

    public FacebookCredentials getFacebookCredentials() {
        return facebookCredentials;
    }

    public void setFacebookCredentials(FacebookCredentials facebookCredentials) {
        this.facebookCredentials = facebookCredentials;
    }

    public TwitterCredentials getTwitterCredentials() {
        return twitterCredentials;
    }

    public void setTwitterCredentials(TwitterCredentials twitterCredentials) {
        this.twitterCredentials = twitterCredentials;
    }

    public WebCredentials getWebCredentials() {
        return webCredentials;
    }

    public void setWebCredentials(WebCredentials webCredentials) {
        this.webCredentials = webCredentials;
    }
}
