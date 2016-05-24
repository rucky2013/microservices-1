package org.yvasilchuk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.yvasilchuk.domain.FatGrantedAuthority;
import org.yvasilchuk.domain.enums.Role;
import org.yvasilchuk.domain.model.security.WebRegistrationDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User extends AbstractEntity implements UserDetails {
    private static final long serialVersionUID = -6017856258023877410L;

    @Column(name = "username", nullable = false, unique = true, length = 512)
    private String username;

    @ElementCollection
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "id"))
    private List<Role> roles;

    @Column(name = "email", nullable = false, unique = true, length = 512)
    private String email;

    @Column(name = "password", nullable = false, unique = true, length = 1024)
    private String password;

    @Column(name = "facebook_id", length = 1024)
    private String facebookId;

    @Column(name = "twitter_id", length = 1024)
    private String twitterId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<Category> categories;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<CashAccount> accounts;

    public User() {
    }

    public User(WebRegistrationDetails webDetails) {
        this.username = webDetails.getName();
        this.email = webDetails.getEmail();
        this.password = webDetails.getPassword();
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(Role.ROLE_USER);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles.isEmpty()) {
            return new ArrayList<FatGrantedAuthority>();
        }
        return roles
                .stream()
                .sorted((o1, o2) -> o1.name().compareTo(o2.name()))
                .map(FatGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<CashAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<CashAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", roles=" + roles +
                ", email='" + email + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", twitterId='" + twitterId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
