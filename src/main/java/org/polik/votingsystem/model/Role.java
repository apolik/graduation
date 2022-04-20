package org.polik.votingsystem.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Polik on 2/1/2022
 */
public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
