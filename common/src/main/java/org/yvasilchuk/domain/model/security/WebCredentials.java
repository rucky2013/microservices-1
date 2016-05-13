package org.yvasilchuk.domain.model.security;

import org.yvasilchuk.domain.messages.ValidationMessages;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class WebCredentials implements Serializable {
    private static final long serialVersionUID = -4271354488671807915L;

    @NotNull(message = ValidationMessages.LOGIN_REQUIRED)
    private String email;
    @NotNull(message = ValidationMessages.PASSWORD_REQUIRED)
    private String password;

    public WebCredentials() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
