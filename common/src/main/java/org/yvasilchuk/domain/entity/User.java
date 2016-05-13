package org.yvasilchuk.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    @Column(name = "username", nullable = false, unique = true, length = 512)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 512)
    private String email;

    @Column(name = "password", nullable = false, unique = true, length = 1024)
    private String password;

    @Column(name = "facebook_id", length = 1024)
    private String facebookId;

    @Column(name = "twitter_id", length = 1024)
    private String twitterId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<CashOperationCategory> categories;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<CashAccount> accounts;

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<CashOperationCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<CashOperationCategory> categories) {
        this.categories = categories;
    }

    public List<CashAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<CashAccount> accounts) {
        this.accounts = accounts;
    }
}
