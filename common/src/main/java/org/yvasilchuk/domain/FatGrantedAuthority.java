package org.yvasilchuk.domain;

import org.springframework.security.core.GrantedAuthority;
import org.yvasilchuk.domain.enums.Role;

public class FatGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = -5497356860242900546L;

    private Role role;

    public FatGrantedAuthority() {
    }

    public FatGrantedAuthority(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}
