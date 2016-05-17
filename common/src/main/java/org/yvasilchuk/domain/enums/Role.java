package org.yvasilchuk.domain.enums;

import org.yvasilchuk.domain.model.security.RoleNames;

public enum Role {
    ROLE_USER(RoleNames.ROLE_USER),
    ROLE_ADMIN(RoleNames.ROLE_ADMIN);

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
