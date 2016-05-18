package org.yvasilchuk.domain.model.user;

import org.yvasilchuk.domain.entity.User;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 7703578114170677802L;

    private String email;
    private String username;
    private String facebookId;
    private String twitterId;

    public UserProfile() {
    }

    public UserProfile(User u) {
        this.email = u.getEmail();
        this.username = u.getUsername();
        this.twitterId = u.getTwitterId();
        this.facebookId = u.getFacebookId();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }
}
