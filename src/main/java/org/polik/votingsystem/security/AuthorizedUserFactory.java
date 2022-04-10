package org.polik.votingsystem.security;

import org.polik.votingsystem.model.Role;
import org.polik.votingsystem.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Polik on 4/6/2022
 */
public class AuthorizedUserFactory {
    public static AuthorizedUser create(User user) {
        return new AuthorizedUser(
                user.getId(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getPassword(),
                user.getUsername(),
                user.getEmail());
    }

    // fixme:
    public static User getUser(AuthorizedUser user) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToRoles(user.getAuthorities())
        );
    }

    public static List<GrantedAuthority> mapToGrantedAuthorities(Collection<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.name())
                ).collect(Collectors.toList());
    }

    public static Set<Role> mapToRoles(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(auth -> Enum.valueOf(Role.class, auth.getAuthority()))
                .collect(Collectors.toSet());
    }
}
