package org.polik.votingsystem.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Polik on 4/6/2022
 */
public class AuthorizedUser implements UserDetails {
    private final Integer id;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String password;
    private final String username;
    private final String email;

    public AuthorizedUser(Integer id, Collection<? extends GrantedAuthority> authorities, String password, String username, String email) {
        this.id = id;
        this.authorities = authorities;
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
