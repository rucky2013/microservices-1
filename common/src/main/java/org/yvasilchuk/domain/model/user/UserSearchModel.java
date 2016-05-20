package org.yvasilchuk.domain.model.user;

public class UserSearchModel {
    private String username;
    private String email;

    public UserSearchModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmpty() {
        return (username == null || username.isEmpty()) && (email == null || email.isEmpty());
    }
}
