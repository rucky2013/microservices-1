package org.yvasilchuk.domain.model.security;

import org.hibernate.validator.constraints.Email;
import org.yvasilchuk.domain.messages.ValidationMessages;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class WebRegistrationDetails implements Serializable {
    private static final long serialVersionUID = 2716528471025184505L;

    @NotNull(message = ValidationMessages.EMAIL_REQUIRED)
    @Size(min = 6, max = 512, message = ValidationMessages.INVALID_EMAIL_LENGTH)
    @Email(message = ValidationMessages.INVALID_EMAIL_FORMAT)
    private String email;
    @NotNull(message = ValidationMessages.NAME_REQUIRED)
    @Size(min = 6, max = 512, message = ValidationMessages.INVALID_NAME_LENGTH)
    private String name;
    @NotNull(message = ValidationMessages.PASSWORD_REQUIRED)
    @Size(min = 6, max = 32, message = ValidationMessages.INVALID_PASSWORD_LENGTH)
    private String password;

    public WebRegistrationDetails() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
